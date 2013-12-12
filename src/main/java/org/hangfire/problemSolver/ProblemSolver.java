package org.hangfire.problemSolver;

import org.hangfire.attempt.*;
import org.hangfire.problem.Problem;
import org.hangfire.problem.ProblemUtils;
import org.hangfire.problem.PuzzleMap;
import org.hangfire.problem.PuzzleMapUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hangfire.attempt.Instruction.LEFT;
import static org.hangfire.attempt.Instruction.UP;

public class ProblemSolver {
	
    private PuzzleMap puzzleMap;
    private static final AttemptFactory ATTEMPT_FACTORY = new AttemptFactory();
    private static final AttemptChecker ATTEMPT_CHECKER = new AttemptChecker();

    public Attempt solve(final Problem problem) {

        Attempt solution = null;
        Map<Integer, List<Point>> startingPointsByInstructionLength = ProblemUtils.fetchStartingPoints(problem);

        for (Integer instructionLength : startingPointsByInstructionLength.keySet()) {
            for (Point point : startingPointsByInstructionLength.get(instructionLength)) {
                //System.out.println("\nStarting point:" + point);
                PuzzleMap condensedPuzzleMap = PuzzleMapUtils.condensePuzzleMap(problem.getPuzzleMap(), point);
                Attempt attempt = ATTEMPT_FACTORY.defaultAttemptOfSize(instructionLength);
                while (AttemptOutcome.SOLVED != attempt.getAttemptOutcome() && attempt.isValid() && attempt.getAttemptOutcome() != AttemptOutcome.INSTANT_BOOM ) {
                    attempt = ATTEMPT_CHECKER.check(condensedPuzzleMap, point, attempt);
                    if (AttemptOutcome.SOLVED == attempt.getAttemptOutcome()) {
                        //System.out.println("Condensed map:" + condensedPuzzleMap);
                        //System.out.println("Attempt outcome:" + attempt);
                        return attempt;
                    }
                    attempt = ATTEMPT_FACTORY.createAlternativeAttempt(attempt);
                }
            }
            //System.out.println("No solutions found for instruction length:" + instructionLength);
        }
        return solution;
    }
}