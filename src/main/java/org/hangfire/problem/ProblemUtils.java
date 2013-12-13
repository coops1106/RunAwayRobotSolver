package org.hangfire.problem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProblemUtils {

    public static Map<Integer, List<Point>> fetchStartingPoints(final Problem problem) {
        Map<Integer, List<Point>> startingPoints = new TreeMap<Integer, List<Point>>();
        for (int i=problem.getMinInstructions(); i<=problem.getMaxInstructions(); i++) {
            List<Point> points = new ArrayList<Point>();
            for (int j=1; j<=i; j++) {
                Point point = new Point(i-j, j);
                points.add(point);
            }
            startingPoints.put(i, points);
        }
        return startingPoints;
    }
}
