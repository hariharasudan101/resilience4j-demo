package org.resilience4j.demo.command;

import org.resilience4j.demo.dto.BookingInfo;
import org.resilience4j.demo.dto.PaymentInfo;
import org.resilience4j.demo.exception.ExternalServiceUnavailableException;
import org.resilience4j.demo.exception.InsufficientCreditException;
import org.resilience4j.demo.proxy.PaymentService;
import org.resilience4j.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.resilience4j.retry.annotation.Retry;

@Component
public class MakePaymentCommand {


    Logger LOG = LoggerFactory.getLogger("MakePaymentCommand");
    @Autowired
    PaymentService paymentService;


    @Retry(name = Constants.PAYMENT_CALL) //, fallbackMethod = "fallback"
    public void execute(BookingInfo bookingInfo) throws ExternalServiceUnavailableException, InsufficientCreditException {
        PaymentInfo payment = new PaymentInfo();
        paymentService.doPayment(payment);
    }

    /*public void fallback(BookingInfo bookingInfo, Exception e){
        LOG.info("Payment failed, Fallback method called!!");
    }*/

}
