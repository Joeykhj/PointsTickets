package com.company;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Joey on 23/06/2017.
 */
public class GenerateSeedData {

    Map<Point, Boolean> eventExistMap = new HashMap<>();
    Map<Integer, Point> eventIDMap = new HashMap<>();
    Map<Integer, double[]> eventTicketPrices = new HashMap<>();
    Map<Integer, Map<Double, Integer>> numberOfTickets =  new HashMap<>();
    Map<Double, Integer> numberOfTicketsPerPrice =  new HashMap<>();

    Set<Integer> uniqueEventIDs =  new HashSet<>();

    public void randomEventsGenerator(){

        Random rn = new Random();
        int numberOfEvents = 0;

        //this generates events
        for (int x = -10; x < 11; x++){
            for (int y = -10; y < 11; y++){

                Point point =  new Point(x,y);
                int randomTrueOrFalse =  rn.nextInt(2);
                if (randomTrueOrFalse == 1){
                    eventExistMap.put(point,true);
                    numberOfEvents++;
                }else{
                    eventExistMap.put(point,false);
                }
            }
        }

        //this gives the events a random ID
        for (int x = -10; x < 11; x++){
            for (int y = -10; y < 11; y++){

                Point point = new Point(x,y);
                if (eventExistMap.get(point)){
                    int randomUniqueID =  rn.nextInt(numberOfEvents) + 1;
                    while(uniqueEventIDs.contains(randomUniqueID)){
                        randomUniqueID =  rn.nextInt(numberOfEvents) + 1;
                    }
                    eventIDMap.put(randomUniqueID, point);
                    uniqueEventIDs.add(randomUniqueID);
                }
            }
        }

        //this generates a random number of tickets for each event and ticket prices
        for (int i = 1; i < numberOfEvents + 1; i++){

            //for ease of calculation, lets assume that each event has 3 ticket prices, max price of 1000, max 1000 tickets per price
            double[] ticketPrices = new double[3];

            for (int j = 0; j < 3; j++){
                ticketPrices[j] = (double) (rn.nextInt(100000) + 1)/100;
                numberOfTicketsPerPrice.put(ticketPrices[j], rn.nextInt(1000));
            }

            //this arranges the tickets from cheapest to most expensive
            Arrays.sort(ticketPrices);

            eventTicketPrices.put(i, ticketPrices);
            numberOfTickets.put(i, numberOfTicketsPerPrice);
        }

    }

    public void listAllEvents(){

        randomEventsGenerator();

        for (int i = 1; i < eventIDMap.size(); i++) {

            DecimalFormat df = new DecimalFormat("0.00");
            Point point = eventIDMap.get(i);
            if (eventExistMap.get(point)) {
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
                System.out.println("Event: " + i + ", Location: (" + point.getX() + "," + point.getY() + ")\n" +
                        "Ticket Prices: \n" +
                        "VIP, $" + vipFormatted + ", " + vipTicketsLeft + " left" +
                        "\nSeated, $" + seatedFormatted + ", " + seatedTicketsLeft + " left" +
                        "\nStanding, $" + standingFormatted + ", " + standingTicketsLeft + " left\n");
            }

        }
    }
}
