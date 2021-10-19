package org.resilience4j.demo.command;

import org.resilience4j.demo.dto.BookingInfo;
import org.resilience4j.demo.exception.ExternalServiceUnavailableException;
import org.resilience4j.demo.proxy.TheatreService;
import org.resilience4j.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class ReserveTicketCommand {

    Logger LOG = LoggerFactory.getLogger("ReserveTicket");


    @Autowired
    TheatreService theatreService;

    @CircuitBreaker(name = Constants.THEATRE_CALL)
    @Bulkhead(name = Constants.THEATRE_CALL)

    public void execute(BookingInfo bookingInfo) throws ExternalServiceUnavailableException {
        theatreService.bookTicket(bookingInfo);
    }

    /*private void fallback(Exception e) {
        LOG.info("Theatre Service call failing, fallback getting executed");
    }*/
}
