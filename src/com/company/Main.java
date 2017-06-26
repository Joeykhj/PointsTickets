package com.company;

public class Main {

    public static void main(String[] args) {

        GenerateSeedData generateSeedData =  new GenerateSeedData();
        Events events =  generateSeedData.generateSeedData();
        NearestEventSearch nearestEventSearch = new NearestEventSearch(events);
        nearestEventSearch.nearestEvents(4,2);
        generateSeedData.listAllEvents(events);
        
    }
}
