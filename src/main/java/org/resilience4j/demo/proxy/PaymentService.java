package org.resilience4j.demo.proxy;

import java.util.concurrent.TimeUnit;

import org.resilience4j.demo.dto.PaymentInfo;
import org.resilience4j.demo.exception.ExternalServiceUnavailableException;
import org.resilience4j.demo.exception.InsufficientCreditException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("PaymentService")
public class PaymentService {

    Logger LOG = LoggerFactory.getLogger("PaymentService");

    public void doPayment(PaymentInfo paymentInfo)
            throws InsufficientCreditException, ExternalServiceUnavailableException {

        LOG.info("Invoking Payment Service!!");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            LOG.error("InterruptedException in doPayment", e);
        }
        // throw new ExternalServiceUnavailableException();

        // throw new InsufficientCreditException();

    }
}
