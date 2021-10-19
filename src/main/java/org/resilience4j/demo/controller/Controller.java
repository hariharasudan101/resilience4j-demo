package org.resilience4j.demo.controller;

import org.resilience4j.demo.dto.BookingInfo;
import org.resilience4j.demo.dto.Ticket;
import org.resilience4j.demo.exception.BookingFailedException;
import org.resilience4j.demo.exception.ExternalServiceUnavailableException;
import org.resilience4j.demo.exception.InsufficientCreditException;
import org.resilience4j.demo.service.BookingService;
import org.resilience4j.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping(value = "resilience/demo/v1/")
public class Controller {

    Logger LOG = LoggerFactory.getLogger("Controller");
    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "bookmyshow", method = RequestMethod.POST)
    @RateLimiter(name = Constants.BOOKING_CALL)
    public Ticket bookMyShow(@RequestBody BookingInfo bookingInfo) throws BookingFailedException, InsufficientCreditException {

        try {
            return bookingService.bookTicket(bookingInfo);
        } catch (ExternalServiceUnavailableException e) {
            LOG.error("ExternalServiceUnavailableException during bookMyShow call", e);
            throw new BookingFailedException();
        }

    }
}
