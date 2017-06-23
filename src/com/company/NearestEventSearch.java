package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Joey on 23/06/2017.
 */
public class NearestEventSearch {

    GenerateSeedData generateSeedData = new GenerateSeedData();
    Set<Point> points = new HashSet<>();

    public void nearestEvents(int x, int y){

        Point origin = new Point(x,y);

        generateSeedData.randomEventsGenerator();



    }

    private int manhattanDistanceCalculator(Point A, Point B){
        Double distance = (Math.abs(A.getX() - B.getX()) + Math.abs(A.getY() - B.getY()));
        return distance.intValue();
    }

    private List<Point> pointsInSearchRadius(Point A, int distance){

        List<Point> points = new ArrayList<>();
        int xb;
        int yb;

        for (int i = 0; i < distance + 1; i++){

            Double sumOfPoints = A.getX() + A.getX() - distance;
            for(xb = -distance; xb <= distance; xb++){
                yb = sumOfPoints.intValue() - xb;
                points.add(new Point(xb,yb));
            }
        }
        return points;
    }
}
