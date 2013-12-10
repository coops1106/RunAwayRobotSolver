package org.hangfire.problem;

import java.awt.*;

public class PuzzleMapUtils {

    public static PuzzleMap condensePuzzleMap(final PuzzleMap puzzleMap, final Point point) {
        boolean[][] condensedMap = new boolean[point.x + 1][point.y + 1];

        int multiple = 0;
        while(true) {
            try {
                for (int x=0; x<=point.x; x++) {
                    for(int y=0; y<=point.y; y++) {
                        condensedMap[x][y] = puzzleMap.isBoomAt(x+(multiple*point.x), y+(multiple*point.y)) || condensedMap[x][y];
                    }
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                break;
            }
            multiple++;
        }

        return new PuzzleMap(condensedMap);
    }

}
