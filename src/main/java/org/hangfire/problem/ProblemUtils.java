package org.hangfire.problem;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemUtils {

    public static Map<Integer, List<Point>> fetchStartingPoints(final Problem problem) {
        Map<Integer, List<Point>> startingPoints = new HashMap<Integer, List<Point>>();
        for (int i=problem.getMinInstructions(); i<=problem.getMaxInstructions(); i++) {
            System.out.println("Starting points for instructions of length " + i);
            List<Point> points = new ArrayList<Point>();
            for (int j=0; j<=i; j++) {
                Point point = new Point(i-j, j);
                points.add(point);
            }
            startingPoints.put(i, points);
        }
        return startingPoints;
    }
}
