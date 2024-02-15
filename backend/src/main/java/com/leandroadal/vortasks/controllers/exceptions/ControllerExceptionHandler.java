package com.leandroadal.vortasks.controllers.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.leandroadal.vortasks.services.backup.exceptions.BackupCreationException;
import com.leandroadal.vortasks.services.backup.exceptions.BackupNotModifiedException;
import com.leandroadal.vortasks.services.exception.DatabaseException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.exception.ValidateException;
import com.leandroadal.vortasks.services.shop.payments.exceptions.PaymentException;
import com.leandroadal.vortasks.services.shop.payments.exceptions.PaymentMismatchException;
import com.leandroadal.vortasks.services.shop.purchase.exceptions.InsufficientFundsException;
import com.leandroadal.vortasks.services.shop.purchase.exceptions.TransactionFinishException;
import com.leandroadal.vortasks.services.social.friendship.exceptions.FriendException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        StandardError err = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<StandardError> validate(ValidateException e, HttpServletRequest request) {
        String error = "Erro ao validar";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BackupCreationException.class)
    public ResponseEntity<StandardError> backupCreate(BackupCreationException e, HttpServletRequest request) {
        String error = "Erro no backup";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BackupNotModifiedException.class)
    public ResponseEntity<StandardError> backupNotModified(BackupNotModifiedException e, HttpServletRequest request) {
        String error = "Erro ao recuperar backup";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FriendException.class)
    public ResponseEntity<StandardError> friendReceiverMismatch(FriendException e, HttpServletRequest request) {
        String error = "Erro ao recuperar amigo";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
        String error = "Erro no banco de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PaymentMismatchException.class)
    public ResponseEntity<StandardError> paymentMismatch(PaymentMismatchException e, HttpServletRequest request) {
        String error = "Erro no pagamento";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<StandardError> payment(PaymentException e, HttpServletRequest request) {
        String error = "Erro no pagamento";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TransactionFinishException.class)
    public ResponseEntity<StandardError> transactionFinish(TransactionFinishException e, HttpServletRequest request) {
        String error = "Erro na transação";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<StandardError> insufficientFunds(InsufficientFundsException e, HttpServletRequest request) {
        String error = "Saldo insuficiente";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
