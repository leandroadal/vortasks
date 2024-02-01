package com.leandroadal.vortasks.services.shop;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.services.shop.exceptions.PaymentException;
import com.leandroadal.vortasks.services.shop.exceptions.PaymentMismatchException;

@Service
public class PaymentService {

    public PaymentResult processPayment(BigDecimal paymentToken, BigDecimal amount) {
        try {
            // Para simplificar o token esta vindo com o valor pago pelo cliente
            if (paymentToken.equals(amount)) {
                return new PaymentResult();
            } else {
                throw new PaymentMismatchException("Compra Negada: O valor enviado e o valor do produto não são equivalentes.");
            }
        } catch (NullPointerException e) {
            throw new PaymentException("Falha ao processar o pagamento. Token inválido.");
        }
    }

}
