package org.hangfire.attempt;

import org.hangfire.problem.PuzzleMap;

import java.awt.*;

public class AttemptChecker {
    public Attempt check(final PuzzleMap puzzleMap, final Point point, final Attempt attempt) {
        Point checkPoint = new Point(point);
        int instruction = attempt.getSize() -1;

        if (puzzleMap.isBoomAt(checkPoint)) {
            //System.out.println("Boom at " + checkPoint.x + "," + checkPoint.y);
            attempt.setBoomPoint(attempt.getSize());
            attempt.setAttemptOutcome(AttemptOutcome.INSTANT_BOOM);
        } else {
            for (int i=attempt.getSize(); i>0 ; i--) {
                movePoint(checkPoint, attempt, i);
                if (isPointHome(checkPoint)) {
                    attempt.setAttemptOutcome(AttemptOutcome.SOLVED);
                    break;
                }
                if (isPointOutOfBounds(checkPoint)) {
                    attempt.setBoomPoint(1);
                    attempt.setAttemptOutcome(AttemptOutcome.OUT_OF_BOUNDS);
                    break;
                }
                if (puzzleMap.isBoomAt(checkPoint)) {
                    //System.out.println("Boom at " + checkPoint.x + "," + checkPoint.y + " by instruction " + i);
                    attempt.setBoomPoint(i);
                    attempt.setAttemptOutcome(AttemptOutcome.UNABLE_TO_SOLVE);
                    break;
                }
            }
        }
        return attempt;
    }

    private boolean isPointHome(final Point checkPoint) {
        return checkPoint.x == 0 && checkPoint.y == 0;
    }

    private boolean isPointOutOfBounds(final Point point) {
        return point.x < 0 || point.y < 0;
    }

    private void movePoint(final Point point, final Attempt attempt, final int instruction) {
        if (attempt.instructionAt(instruction-1) == Instruction.UP) {
            point.setLocation(point.x, point.y-1);
        } else if(attempt.instructionAt(instruction-1) == Instruction.LEFT) {
            point.setLocation(point.x-1, point.y);
        }
    }
}
