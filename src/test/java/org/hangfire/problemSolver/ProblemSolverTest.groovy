package org.hangfire.problemSolver

import org.hangfire.RunAwayRobotTest

import static org.hangfire.attempt.Instruction.LEFT
import static org.hangfire.attempt.Instruction.UP

class ProblemSolverTest extends RunAwayRobotTest {

    def ProblemSolver uut

    def setup() {
        uut = new ProblemSolver()
    }

    def test() {
        given: "Problems"
        def problem = theProblem
        println problem
        when: "I solve"
        def attempt = uut.solve(problem)
        println attempt
        then:
        attempt.instructions == theSolution
        where:
        theProblem                              | theSolution
        PROBLEM_FACTORY.fetchProblemForLevel(1) | [LEFT, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(2) | [LEFT, UP, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(3) | [LEFT, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(4) | [UP, UP, LEFT]
        PROBLEM_FACTORY.fetchProblemForLevel(5) | [LEFT, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(6) | [LEFT, UP, UP]
        PROBLEM_FACTORY.fetchProblemForLevel(7) | [UP, UP, UP, LEFT]
        PROBLEM_FACTORY.fetchProblemForLevel(8) | [UP, LEFT, LEFT]

    }
}
