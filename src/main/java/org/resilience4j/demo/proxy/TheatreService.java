package org.resilience4j.demo.proxy;

import java.util.concurrent.TimeUnit;

import org.resilience4j.demo.dto.BookingInfo;
import org.resilience4j.demo.exception.ExternalServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("TheatreService")
public class TheatreService {

   private final Logger LOG = LoggerFactory.getLogger("TheatreService");

    public String bookTicket(BookingInfo bookingInfo) throws ExternalServiceUnavailableException {


        try {
            LOG.info("Calling External service -- Theatre Service ");
            TimeUnit.SECONDS.sleep(1);
//            throw new ExternalServiceUnavailableException();
            return "CONFIRMED";
        } catch (InterruptedException e) {
           LOG.error("InterruptedException in TheatreService");
        }
        return "FAILED";
    }
}
