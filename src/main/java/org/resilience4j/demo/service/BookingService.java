package org.resilience4j.demo.service;

import org.resilience4j.demo.command.MakePaymentCommand;
import org.resilience4j.demo.command.ReserveTicketCommand;
import org.resilience4j.demo.dto.BookingInfo;
import org.resilience4j.demo.dto.Ticket;
import org.resilience4j.demo.exception.ExternalServiceUnavailableException;
import org.resilience4j.demo.exception.InsufficientCreditException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    ReserveTicketCommand reserveTicket;

    @Autowired
    MakePaymentCommand makePayment;


    public Ticket bookTicket(BookingInfo bookingInfo) throws ExternalServiceUnavailableException, InsufficientCreditException {
        Ticket ticket = new Ticket();

        reserveTicket.execute(bookingInfo);
        makePayment.execute(bookingInfo);

        ticket.setMovie(bookingInfo.getMovie());
        ticket.setTheatre(bookingInfo.getTheatre());
        ticket.setShowTime(bookingInfo.getShowTime());
        ticket.setSeats(bookingInfo.getSeats());
        ticket.setStatus("CONFIRMED");


        return ticket;

    }
}
