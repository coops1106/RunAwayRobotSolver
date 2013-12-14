package org.hangfire.problemSolver;

import org.hangfire.attempt.Attempt;
import org.hangfire.attempt.AttemptChecker;
import org.hangfire.attempt.AttemptFactory;
import org.hangfire.attempt.AttemptOutcome;
import org.hangfire.problem.Problem;
import org.hangfire.problem.ProblemUtils;
import org.hangfire.problem.PuzzleMap;
import org.hangfire.problem.PuzzleMapUtils;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProblemSolver {
	
    private PuzzleMap puzzleMap;
    private static final AttemptFactory ATTEMPT_FACTORY = new AttemptFactory();
    private static final AttemptChecker ATTEMPT_CHECKER = new AttemptChecker();
    private Set<Attempt> attempts;

    public Attempt solve(final Problem problem) {

        Attempt solution = null;
        Map<Integer, List<Point>> startingPointsByInstructionLength = ProblemUtils.fetchStartingPoints(problem);

        for (Integer instructionLength : startingPointsByInstructionLength.keySet()) {
            //System.out.println("Next instruction length:" + instructionLength);
            Long startTime;
            Long endTime;
            for (Point point : startingPointsByInstructionLength.get(instructionLength)) {
                //System.out.println("Next point:" + point);
                PuzzleMap condensedPuzzleMap = PuzzleMapUtils.condensePuzzleMap(problem.getPuzzleMap(), point);
                Attempt attempt = ATTEMPT_FACTORY.defaultAttemptOfSize(instructionLength);
                attempts  = new HashSet<Attempt>();
                //startTime = System.currentTimeMillis();
                int count = 0;
                while (AttemptOutcome.SOLVED != attempt.getAttemptOutcome() && attempt.isValid() && attempt.getAttemptOutcome() != AttemptOutcome.INSTANT_BOOM ) {
                    attempt = ATTEMPT_CHECKER.check(condensedPuzzleMap, point, attempt);
                    if (AttemptOutcome.SOLVED == attempt.getAttemptOutcome()) {
                        return attempt;
                    }
                    attempt = ATTEMPT_FACTORY.createAlternativeAttempt(attempt);
                    count++;
                }
                //endTime =System.currentTimeMillis();
                //System.out.println("" + (endTime - startTime) + ":" + count + ":" + point.x + "," + point.y + ":" + instructionLength);
                //System.out.println("Number of attempts:" + count);
            }
        }
        return solution;
    }
}