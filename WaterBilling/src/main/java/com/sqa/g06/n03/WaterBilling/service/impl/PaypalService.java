package com.sqa.g06.n03.WaterBilling.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sqa.g06.n03.WaterBilling.config.paypal.PaypalPaymentIntent;
import com.sqa.g06.n03.WaterBilling.config.paypal.PaypalPaymentMethod;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.model.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;


@Service
public class PaypalService {
    @Autowired
    private APIContext apiContext;

    public Payment createPayment(
            Double total,
            String currency,
            PaypalPaymentMethod method,
            PaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl,
            PaymentDTO paymentDTO) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);


        // =============================
        transaction.setCustom(paymentDTO.getClientId() + "," + paymentDTO.getUsername() + "," +
                paymentDTO.getMonth() + "," + paymentDTO.getYear() + "," +
                paymentDTO.getTotalAmount());
        // =============================

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());
        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        apiContext.setMaskRequestId(true);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecute);
    }
}
