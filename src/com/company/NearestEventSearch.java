package com.company;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class NearestEventSearch {

    private Events events =  new Events();


    public NearestEventSearch(Events events){
        this.events = events;
    }

    public void nearestEvents(){

        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();
        findEvents(scanner);
    }

    public void closestEvents(int x, int y){

        Scanner scanner = new Scanner("(" + x + "," + y + ")");
        findEvents(scanner);

    }

    private void findEvents(Scanner scanner) {
        try{

            //this section takes the user input and converts it from string to int
            String coordinate = scanner.nextLine();
            String[] parts = coordinate.split(",");

            //allows coordinates to be passed with spaces, e.g. accepts (x, y) or ( x , y )
            String xString = parts[0].trim().substring(1).trim();
            String yString = parts[1].trim().substring(0, parts[1].trim().length() - 1).trim();

            int x = Integer.parseInt(xString);
            int y = Integer.parseInt(yString);

            //ensures that the coordinates are within the grid
            if(x > 10 || x < -10 || y > 10 || y < -10){
                System.out.println("Invalid entry, please try again");
                return ;
            }

            //calculates the distances between points
            Point origin = new Point(x, y);
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

            //rearrange the distances from closest to furthest in the array
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

            //stores the closest event's IDs
            List<Integer> closestEventsIDs = new ArrayList<>();

            for(Map.Entry<Integer, Integer> entry : eventDistanceMap.entrySet()){
                if(closestDistances.contains(entry.getValue()) && closestEventsIDs.size() < 5){
                    closestEventsIDs.add(entry.getKey());
                }
            }

            //displays a list of closest events
            printClosestEvents(closestEventsIDs, eventIDMap, eventDistanceMap, events);

        }catch (Exception e){   //this catch ensures that the input is in the format of (x,y)

            System.out.println("Invalid entry, please try again");
        }
    }

    private void printWelcomeMessage() {
        System.out.println( " _______________________\n" +
                            "|                       |\n" +
                            "|  Welcome to Viagogo!  |\n" +
                            "|_______________________|\n" +
                            "\n" +
                            "To find closest events, search a coordinate from (-10,-10) to (10,10)\n" +
                            "e.g. (4,2) \n");
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
