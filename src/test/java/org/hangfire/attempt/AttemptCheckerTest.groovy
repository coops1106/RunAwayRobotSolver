package org.hangfire.attempt

import org.hangfire.RunAwayRobotTest
import org.hangfire.problem.PuzzleMapUtils

import java.awt.*

class AttemptCheckerTest extends RunAwayRobotTest {

    def AttemptChecker uut = new AttemptChecker()

    def "test with default attempts"() {
        given: "A condensed puzzleMap"
        println "\n"
        def problem = RunAwayRobotTest.PROBLEM_FACTORY.fetchProblemForLevel(level)
        println problem
        def puzzleMap = PuzzleMapUtils.condensePuzzleMap(problem.puzzleMap, startPoint)
        println puzzleMap
        and: "an attempt"
        def attempt = RunAwayRobotTest.ATTEMPT_FACTORY.defaultAttemptOfSize(problem.maxInstructions)
        when: "I check attempt"
        attempt = uut.check(puzzleMap, startPoint, attempt)
        then:
        attempt.attemptOutcome == expectedOutcome
        and:
        expectedBoomPoint == attempt.boomPoint
        where:
        level | startPoint      | expectedOutcome                | expectedBoomPoint
        1     | new Point(2, 0) | AttemptOutcome.INSTANT_BOOM    | 2
        1     | new Point(1, 1) | AttemptOutcome.OUT_OF_BOUNDS   | 1
        1     | new Point(0, 2) | AttemptOutcome.INSTANT_BOOM    | 2
        2     | new Point(3, 0) | AttemptOutcome.OUT_OF_BOUNDS   | 1
        2     | new Point(2, 1) | AttemptOutcome.UNABLE_TO_SOLVE | 3
        2     | new Point(1, 2) | AttemptOutcome.OUT_OF_BOUNDS   | 1
        2     | new Point(0, 3) | AttemptOutcome.UNABLE_TO_SOLVE | 2
        3     | new Point(3, 0) | AttemptOutcome.INSTANT_BOOM    | 3
        3     | new Point(2, 1) | AttemptOutcome.UNABLE_TO_SOLVE | 3
        3     | new Point(1, 2) | AttemptOutcome.INSTANT_BOOM    | 3
        3     | new Point(0, 3) | AttemptOutcome.INSTANT_BOOM    | 3


    }

}
