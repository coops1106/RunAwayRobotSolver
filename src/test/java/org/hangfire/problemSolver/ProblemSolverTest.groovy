package org.hangfire.problemSolver

import org.hangfire.RunAwayRobotTest
import org.hangfire.attempt.Attempt
import org.hangfire.attempt.AttemptOutcome
import org.hangfire.problem.Problem

import static org.hangfire.attempt.Instruction.DOWN
import static org.hangfire.attempt.Instruction.RIGHT

class ProblemSolverTest extends RunAwayRobotTest {

    def ProblemSolver uut

    def setup() {
        uut = new ProblemSolver()
    }

    def test() {
        given: "Problems"
        def problem = theProblem
        when: "I solve"
        def attempt = uut.solve(problem)
        then:
        attempt.attemptOutcome == AttemptOutcome.SOLVED
        attempt.instructions == theSolution
        where:
        theProblem | theSolution
        //PROBLEM_FACTORY.fetchProblemForLevel(1) | [RIGHT, DOWN]
        //PROBLEM_FACTORY.fetchProblemForLevel(2) | [RIGHT, DOWN, DOWN]
        //PROBLEM_FACTORY.fetchProblemForLevel(3) | [RIGHT, DOWN]
        //PROBLEM_FACTORY.fetchProblemForLevel(4) | [DOWN, DOWN, RIGHT]
        PROBLEM_FACTORY.fetchProblemForLevel(5) | [DOWN, DOWN, RIGHT]
    }

    def "test checkAttempt"() {
        given:
        def Attempt attempt = new Attempt()
        attempt.instructions = theInstructions
        and:
        def Problem problem = theProblem
        when:
        def result = uut.checkAttempt(attempt, problem.puzzleMap)
        then:
        result.boomPoint == expectedBoomPoint
        where:
        theInstructions      | theProblem                              | expectedBoomPoint | expectedOutcome
        [RIGHT, DOWN]        | PROBLEM_FACTORY.fetchProblemForLevel(1) | 0                 | AttemptOutcome.SOLVED
        [RIGHT, DOWN]        | PROBLEM_FACTORY.fetchProblemForLevel(2) | 5                 | AttemptOutcome.UNABLE_TO_SOLVE
        [RIGHT, DOWN, RIGHT] | PROBLEM_FACTORY.fetchProblemForLevel(2) | 5                 | AttemptOutcome.UNABLE_TO_SOLVE
        [RIGHT, RIGHT]       | PROBLEM_FACTORY.fetchProblemForLevel(5) | 3                 | AttemptOutcome.UNABLE_TO_SOLVE
        [DOWN, RIGHT]        | PROBLEM_FACTORY.fetchProblemForLevel(5) | 1                 | AttemptOutcome.UNABLE_TO_SOLVE
        [RIGHT, RIGHT, DOWN] | PROBLEM_FACTORY.fetchProblemForLevel(5) | 4                 | AttemptOutcome.UNABLE_TO_SOLVE
    }


}
