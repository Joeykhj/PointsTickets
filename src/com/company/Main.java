package com.company;

public class Main {

    public static void main(String[] args) {

        GenerateSeedData generateSeedData =  new GenerateSeedData();
        Events events =  generateSeedData.generateSeedData();

        NearestEventSearch nearestEventSearch = new NearestEventSearch(events);
        nearestEventSearch.nearestEvents();
        //nearestEventSearch.closestEvents(3,2); //un-comment this line and comment the above if you would like to key in the coordinates in the brackets and not via the console

        //generateSeedData.listAllEvents(events);
        // to view all available events and compare the closest event, un-comment the line above, this will display a list of all events generated in the grid

    }
}
