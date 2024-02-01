package com.leandroadal.vortasks.services.shop;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leandroadal.vortasks.dto.shop.CompletePurchaseRequestDTO;
import com.leandroadal.vortasks.dto.shop.GemsTransactionDTO;
import com.leandroadal.vortasks.dto.shop.StartPurchaseRequestDTO;
import com.leandroadal.vortasks.entities.shop.GemsPackage;
import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.entities.shop.enumerators.PaymentStatus;
import com.leandroadal.vortasks.entities.shop.transaction.GemsTransaction;
import com.leandroadal.vortasks.entities.shop.transaction.ProductTransaction;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.UserRepository;
import com.leandroadal.vortasks.repositories.shop.GemsPackageRepository;
import com.leandroadal.vortasks.repositories.shop.GemsTransactionRepository;
import com.leandroadal.vortasks.repositories.shop.ProductRepository;
import com.leandroadal.vortasks.repositories.shop.ProductTransactionRepository;
import com.leandroadal.vortasks.services.auth.exceptions.UserNotFoundException;
import com.leandroadal.vortasks.services.shop.exceptions.GemsPackageNotFoundException;
import com.leandroadal.vortasks.services.shop.exceptions.GemsTransactionNotFoundException;
import com.leandroadal.vortasks.services.shop.exceptions.PaymentException;
import com.leandroadal.vortasks.services.shop.exceptions.PaymentMismatchException;
import com.leandroadal.vortasks.services.shop.exceptions.ProductNotFoundException;

@Service
public class PurchaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GemsPackageRepository gemsPackageRepository;

    @Autowired
    private ProductTransactionRepository prodTransactionRepository;

    @Autowired
    private GemsTransactionRepository gemsTransactionRepository;

    @Autowired
    private PaymentService paymentService;

    public void productPurchase(Long userId, StartPurchaseRequestDTO request) throws UserNotFoundException, ProductNotFoundException {
        User user = getUserById(userId);
        Product product = getProductById(request.productOrGemsId());

        if (hasEnoughCoinsAndGems(user, product)) {
            updateUserDataAfterPurchase(user, product);
            createProductTransaction(user, product);
        }
    }

    private User getUserById(Long userId) throws UserNotFoundException {
        try {
            return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    private Product getProductById(Long productId) throws ProductNotFoundException {
        try {
            return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Usuário não encontrado"));
        } catch (ProductNotFoundException e) {
            throw e;
        }
    }

    private boolean hasEnoughCoinsAndGems(User user, Product product) {
        return user.getProgressData().getCoins() >= product.getCoins() && user.getProgressData().getGems() >= product.getGems();
    }

    private void updateUserDataAfterPurchase(User user, Product product) {
        user.getProgressData().setCoins(user.getProgressData().getCoins() - product.getCoins());
        user.getProgressData().setGems(user.getProgressData().getGems() - product.getGems());
        userRepository.save(user);
    }

    private void createProductTransaction(User user, Product product) {
        ProductTransaction transaction = new ProductTransaction();
        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setPurchaseDate(LocalDateTime.now());
        prodTransactionRepository.save(transaction);
    }


    
    public GemsTransactionDTO startGemsPurchase(Long userId, Long gemsPackageId) throws UserNotFoundException, GemsPackageNotFoundException {
        User user = getUserById(userId);
        GemsPackage gemsPackage = getGemsPackageById(gemsPackageId);

        GemsTransaction transaction = createGemsTransaction(user, gemsPackage);
        return new GemsTransactionDTO(transaction);
    }

    private GemsPackage getGemsPackageById(Long gemsPackageId) throws GemsPackageNotFoundException {
        try {
            return gemsPackageRepository.findById(gemsPackageId).orElseThrow(() -> new GemsPackageNotFoundException("Pacote de gemas não encontrado"));
        } catch (GemsPackageNotFoundException e) {
            throw e;
        }
    }

    private GemsTransaction createGemsTransaction(User user, GemsPackage gemsPackage) {
        GemsTransaction transaction = new GemsTransaction();
        transaction.setUser(user);
        transaction.setGemsPackage(gemsPackage);
        transaction.setPurchaseDate(LocalDateTime.now());
        transaction.setStatus(PaymentStatus.PENDING);
        transaction.setPrice(gemsPackage.getMoney());
        gemsTransactionRepository.save(transaction);
        return transaction;
    }

    public void cancelGemsPurchase(Long userId) throws GemsTransactionNotFoundException {
        GemsTransaction gemsTransaction = getGemsTransactionById(userId);
        gemsTransaction.setStatus(PaymentStatus.CANCELLED);
        gemsTransactionRepository.save(gemsTransaction);
    }

    private GemsTransaction getGemsTransactionById(Long gemsTransactionId) throws GemsTransactionNotFoundException {
        try {
            return gemsTransactionRepository.findById(gemsTransactionId).orElseThrow(() -> new GemsTransactionNotFoundException("Transação não encontrada"));
        } catch (GemsTransactionNotFoundException e) {
            throw e;
        }
    }

    public void completeGemsPurchase(Long userId, CompletePurchaseRequestDTO request) throws PaymentException, UserNotFoundException, GemsTransactionNotFoundException{
        User user = getUserById(userId);
        GemsTransaction transaction = getGemsTransactionById(request.gemsTransactionId());

        try {
            processGemsTransaction(user, transaction, request.paymentToken());
        } catch (PaymentMismatchException e) {
            handleDeclinedPayment(transaction, e.getMessage());
            throw e;
        } catch (PaymentException e) {
            handlePaymentException(transaction, e.getMessage());
            throw e;   
        } 
    }

    private void processGemsTransaction(User user, GemsTransaction transaction, BigDecimal paymentToken) throws PaymentException {
        PaymentResult paymentResult = paymentService.processPayment(paymentToken, transaction.getPrice());

        if (paymentResult.isSuccess()) {
            transaction.setStatus(PaymentStatus.APPROVED);
            transaction.setErrorMessage("");
            gemsTransactionRepository.save(transaction);
            updateUserGems(user, transaction.getGemsPackage());
        } 
    }

    private void updateUserGems(User user, GemsPackage gemsPackage) {
        int gems = user.getProgressData().getGems();
        user.getProgressData().setGems(gems + gemsPackage.getGems());
        userRepository.save(user);
    }

    private void handleDeclinedPayment(GemsTransaction transaction, String errorMessage) {
        transaction.setStatus(PaymentStatus.DECLINED);
        transaction.setErrorMessage(errorMessage);
        gemsTransactionRepository.save(transaction);
    }

    private void handlePaymentException(GemsTransaction transaction, String errorMessage) {
        transaction.setStatus(PaymentStatus.ERROR);
        transaction.setErrorMessage(errorMessage);
        gemsTransactionRepository.save(transaction);
    }
}


