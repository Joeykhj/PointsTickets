package com.company;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * Created by Joey on 23/06/2017.
 */
public class NearestEventSearch {

    private Events events =  new Events();

    public NearestEventSearch(Events events){
        this.events = events;
    }

    public void nearestEvents(int x, int y){

        Point origin = new Point(x,y);
        Map<Integer, Point> eventIDMap = events.getEventIDMap();
        Map<Integer, Integer> eventDistanceMap = new HashMap<>();
        int[] distances = new int[eventIDMap.size()];
        int i = 0;

        for (Map.Entry<Integer, Point> entry : eventIDMap.entrySet()){
            int distance = manhattanDistanceCalculator(origin, entry.getValue());
            eventDistanceMap.put(entry.getKey(),distance);
            distances[i] = distance;
            i++;
        }

        Arrays.sort(distances);

        List<Integer> closestDistances =  new ArrayList<>();

        if (distances.length > 4){
            for (int j = 0; j < 5; j++){
                closestDistances.add(distances[j]);
            }
        }else {
            for (int j:distances){
                closestDistances.add(j);
            }
        }

        List<Integer> closestEventsIDs = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : eventDistanceMap.entrySet()){
            if(closestDistances.contains(entry.getValue()) && closestEventsIDs.size() < 5){
                closestEventsIDs.add(entry.getKey());
            }
        }

        printClosestEvents(closestEventsIDs, eventIDMap, eventDistanceMap, events);
    }

    private void printClosestEvents(List<Integer> closestEventsIDs, Map<Integer, Point> eventIDMap, Map<Integer, Integer> eventDistanceMap, Events events){

        for (int i :closestEventsIDs) {

            DecimalFormat df = new DecimalFormat("0.00");
            Point point = eventIDMap.get(i);
            int distance = eventDistanceMap.get(i);

            double[] ticketPrices = events.getEventTicketPrices().get(i);

            double standingTickets = ticketPrices[0];
            String standingFormatted = df.format(standingTickets);
            int standingTicketsLeft = events.getNumberOfTicketsPerPrice().get(standingTickets);

            System.out.println("Event: " + i + ", Location: (" + point.getX() + "," + point.getY() + "), Distance: " + distance + "\nTickets from: $" + standingFormatted + ", " + standingTicketsLeft + " left\n");

        }
    }

    private int manhattanDistanceCalculator(Point A, Point B){
        Double distance = (Math.abs(A.getX() - B.getX()) + Math.abs(A.getY() - B.getY()));
        return distance.intValue();
    }
}
