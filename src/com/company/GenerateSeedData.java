package com.company;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Joey on 23/06/2017.
 */
public class GenerateSeedData {

    public Events generateSeedData(){
        Events events =  new Events();
        events.generateEvents();
        return events;
    }

    public void listAllEvents(Events events){

        Map<Integer, Point> eventIDMap = events.getEventIDMap();
        Map<Integer, double[]> eventTicketPrices = events.getEventTicketPrices();
        Map<Integer, Integer> numberOfTickets = events.getNumberOfTickets();
        Map<Double, Integer> numberOfTicketsPerPrice = events.getNumberOfTicketsPerPrice();

        for (int i = 1; i < eventIDMap.size(); i++) {

            DecimalFormat df = new DecimalFormat("0.00");
            Point point = eventIDMap.get(i);

            int totalTicketsLeft = numberOfTickets.get(i);
            double[] ticketPrices = eventTicketPrices.get(i);

            double standingTickets = ticketPrices[0];
            String standingFormatted = df.format(standingTickets);
            int standingTicketsLeft = numberOfTicketsPerPrice.get(standingTickets);

            double seatedTickets = ticketPrices[1];
            String seatedFormatted = df.format(seatedTickets);
            int seatedTicketsLeft = numberOfTicketsPerPrice.get(seatedTickets);

            double vipTickets = ticketPrices[2];
            String vipFormatted = df.format(vipTickets);
            int vipTicketsLeft = numberOfTicketsPerPrice.get(vipTickets);

            System.out.println("Event: " + i + ", Location: (" + point.getX() + "," + point.getY() + "), Tickets Left: " + totalTicketsLeft + "\n" +
                    "Ticket Prices: \n" +
                    "VIP, $" + vipFormatted + ", " + vipTicketsLeft + " left" +
                    "\nSeated, $" + seatedFormatted + ", " + seatedTicketsLeft + " left" +
                    "\nStanding, $" + standingFormatted + ", " + standingTicketsLeft + " left\n");

        }
    }
}
