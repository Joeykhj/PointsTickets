package com.company;

import java.awt.*;
import java.util.*;

/**
 * Created by Joey on 25/06/2017.
 */
public class Events {

    private Map<Point, Boolean> eventExistMap = new HashMap<>();
    private Map<Integer, Point> eventIDMap = new HashMap<>();
    private Map<Integer, double[]> eventTicketPrices = new HashMap<>();
    private Map<Integer, Integer> numberOfTickets =  new HashMap<>();
    private Map<Double, Integer> numberOfTicketsPerPrice =  new HashMap<>();

    private Set<Integer> uniqueEventIDs =  new HashSet<>();

    private Random rn = new Random();

    public void generateEvents(){
        setEventExistMap();
        setEventIDMap();
        setEventTicketPrices();
    }

    public Map<Point, Boolean> getEventExistMap(){
        return eventExistMap;
    }

    public Map<Integer, Point> getEventIDMap(){
        return eventIDMap;
    }

    public Map<Integer, double[]> getEventTicketPrices(){
        return eventTicketPrices;
    }

    public Map<Integer, Integer> getNumberOfTickets(){
        return numberOfTickets;
    }

    public Map<Double, Integer> getNumberOfTicketsPerPrice(){
        return numberOfTicketsPerPrice;
    }

    public Set<Integer> getUniqueEventIDs(){
        return uniqueEventIDs;
    }

    private void setEventExistMap(){

        //this generates events
        for (int x = -10; x < 11; x++){
            for (int y = -10; y < 11; y++){

                Point point =  new Point(x,y);
                int randomTrueOrFalse =  rn.nextInt(2);
                if (randomTrueOrFalse == 1){
                    eventExistMap.put(point,true);
                }else{
                    eventExistMap.put(point,false);
                }
            }
        }
    }

    private int getNumberOfEvents(){

        int numberOfEvents = 0;
        for (boolean i:eventExistMap.values()){
            if (i){
                numberOfEvents++;
            }
        }
        return numberOfEvents;
    }

    private void setEventIDMap(){

        int numberOfEvents = getNumberOfEvents();

        for (int x = -10; x < 11; x++){
            for (int y = -10; y < 11; y++){

                Point point = new Point(x,y);
                if (eventExistMap.get(point)){
                    int randomUniqueID =  rn.nextInt(numberOfEvents) + 1;
                    while(uniqueEventIDs.contains(randomUniqueID)){
                        randomUniqueID =  rn.nextInt(numberOfEvents) + 1;
                    }
                    uniqueEventIDs.add(randomUniqueID);
                    eventIDMap.put(randomUniqueID, point);
                }
            }
        }
    }

    private void setEventTicketPrices(){

        for (int i = 1; i < getNumberOfEvents() + 1; i++){

            //for ease of calculation, lets assume that each event has 3 ticket prices, max price of 1000, max 1000 tickets per price
            double[] ticketPrices = new double[3];

            for (int j = 0; j < 3; j++){
                ticketPrices[j] = (double) (rn.nextInt(100000) + 1)/100;
                setNumberOfTicketsPerPrice(ticketPrices[j]);
            }

            //this arranges the tickets from cheapest to most expensive
            Arrays.sort(ticketPrices);

            eventTicketPrices.put(i, ticketPrices);
            setNumberOfTickets(i);
        }
    }

    private void setNumberOfTickets(int eventID){

        int totalTickets = 0;

        double[] ticketPrices = eventTicketPrices.get(eventID);

        for (double i:ticketPrices){
         totalTickets += numberOfTicketsPerPrice.get(i);
        }
        numberOfTickets.put(eventID, totalTickets);
    }

    private void setNumberOfTicketsPerPrice(double ticketPrice){
        numberOfTicketsPerPrice.put(ticketPrice, rn.nextInt(1000));
    }

}
