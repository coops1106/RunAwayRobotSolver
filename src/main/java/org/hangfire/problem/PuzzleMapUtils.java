package org.hangfire.problem;

import java.awt.*;

public class PuzzleMapUtils {

    public static PuzzleMap condensePuzzleMap(final PuzzleMap puzzleMap, final Point point) {
        boolean[][] condensedMap = new boolean[point.x + 1][point.y + 1];
        int multiple = 0;

        int mapSize = puzzleMap.getSize();
        int iterationsX = (mapSize/ (point.x+1)) +1;
        int iterationsY = (mapSize/ (point.y+1)) +1;
        int iterations = iterationsX > iterationsY? iterationsX : iterationsY;
        Point fullMapPoint = new Point();

        for (int i=0; i<=iterations; i++) {
            for (int x=0; x<=point.x; x++) {
                for(int y=0; y<=point.y; y++) {
                    fullMapPoint.x = x+(multiple*point.x);
                    fullMapPoint.y = y+(multiple*point.y);
                    if (fullMapPoint.x < mapSize && fullMapPoint.y < mapSize) {
                        condensedMap[x][y] = puzzleMap.isBoomAt(fullMapPoint.x, fullMapPoint.y) || condensedMap[x][y];
                    }
                }
            }
            multiple++;
        }

        return new PuzzleMap(condensedMap);
    }

}
