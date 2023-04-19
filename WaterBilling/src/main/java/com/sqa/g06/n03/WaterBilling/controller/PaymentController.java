package com.sqa.g06.n03.WaterBilling.controller;
import com.sqa.g06.n03.WaterBilling.config.Utils;
import com.sqa.g06.n03.WaterBilling.config.paypal.PaypalPaymentIntent;
import com.sqa.g06.n03.WaterBilling.config.paypal.PaypalPaymentMethod;
import com.sqa.g06.n03.WaterBilling.entity.Bill;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.handler.ResponseObject;
import com.sqa.g06.n03.WaterBilling.model.PaymentDTO;
import com.sqa.g06.n03.WaterBilling.service.AuthService;
import com.sqa.g06.n03.WaterBilling.service.WaterService;
import com.sqa.g06.n03.WaterBilling.service.impl.PaypalService;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.execution.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    public static final String URL_PAYPAL_SUCCESS = "api/v1/payment/success";
    public static final String URL_PAYPAL_CANCEL = "api/v1/payment/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService paypalService;

    @Autowired
    private AuthService authService;

    @Autowired
    WaterService waterService;


    @GetMapping("cancel")
    public ResponseEntity<ResponseObject> cancelPay(){
        return ResponseEntity.status(400).body(new ResponseObject("Failed", "Payment not success", "cancel"));
    }
    @GetMapping("success")
    public ResponseEntity<ResponseObject> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                String s = payment.getTransactions().get(0).getCustom();
                System.out.println(s);
                String[] arrStr = s.split(",");
                for(String i: arrStr){
                    System.out.println(i);
                }

                Bill b = waterService.findOneBill(Integer.parseInt(arrStr[2]), Integer.parseInt(arrStr[3]), arrStr[0]);


                b.setStatus(true); // doi trang thai thanh toan thanh cong
                waterService.save(b);

                com.sqa.g06.n03.WaterBilling.entity.Payment p = waterService.save(new com.sqa.g06.n03.WaterBilling.entity.Payment(new Date(), Double.parseDouble(arrStr[4]), b));
                System.out.println(b);
                System.out.println(p);

                return ResponseEntity.status(200).body(new ResponseObject("Ok", "Payment success", "success"));
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        throw new AppError("Error", 500);
    }

    @PostMapping("/pay")
    public ResponseEntity<ResponseObject> pay(HttpServletRequest request, @RequestBody PaymentDTO paymentDTO){
        User user = authService.verifyToken(request);

        if(!user.getUsername().equals(paymentDTO.getUsername())){
            throw new AppError("Forbbiden", 403);
        }

        paymentDTO.setPaymentDate(new Date());

        double _amount = Utils.roundDouble(paymentDTO.getTotalAmount() / 23.504);
        System.out.println(paymentDTO);

        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        try {
            Payment payment = paypalService.createPayment(
                    _amount,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl,
                    paymentDTO);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return ResponseEntity.status(200).body(new ResponseObject("Ok", "Success", links.getHref()));
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        throw new AppError("Error", 500);
    }
}