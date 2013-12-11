package org.hangfire.attempt;

import org.hangfire.problem.PuzzleMap;

import java.awt.*;

public class AttemptChecker {
    public Attempt check(final PuzzleMap puzzleMap, final Point point, final Attempt attempt) {
        System.out.println("Checking attempt :" + attempt);

        int instruction = attempt.getSize() -1;

        if (puzzleMap.isBoomAt(point)) {
            System.out.println("Boom at " + point.x + "," + point.y);
            attempt.setBoomPoint(attempt.getSize());
            attempt.setAttemptOutcome(AttemptOutcome.INSTANT_BOOM);
        } else {
            for (int i=attempt.getSize(); i>0 ; i--) {
                movePoint(point, attempt, instruction);
                if (isPointOutOfBounds(point, attempt)) {
                    attempt.setAttemptOutcome(AttemptOutcome.OUT_OF_BOUNDS);
                    break;
                }
                if (puzzleMap.isBoomAt(point)) {
                    System.out.println("Boom at " + point.x + "," + point.y + " by instruction " + i);
                    attempt.setBoomPoint(i);
                    attempt.setAttemptOutcome(AttemptOutcome.UNABLE_TO_SOLVE);
                    break;
                }
            }
        }
        return attempt;
    }

    private boolean isPointOutOfBounds(final Point point, final Attempt attempt) {
        return point.x < 0 || point.y < 0;
    }

    private void movePoint(final Point point, final Attempt attempt, final int instruction) {
        if (attempt.instructionAt(instruction) == Instruction.UP) {
            point.setLocation(point.x, point.y-1);
        } else if(attempt.instructionAt(instruction) == Instruction.LEFT) {
            point.setLocation(point.x-1, point.y);
        }
    }
}
