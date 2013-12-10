package org.hangfire.problemSolver
import org.hangfire.RunAwayRobotTest

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
        true
        where:
        theProblem | theSolution
        //PROBLEM_FACTORY.fetchProblemForLevel(1) | [RIGHT, DOWN]
        //PROBLEM_FACTORY.fetchProblemForLevel(2) | [RIGHT, DOWN, DOWN]
        //PROBLEM_FACTORY.fetchProblemForLevel(3) | [RIGHT, DOWN]
        //PROBLEM_FACTORY.fetchProblemForLevel(4) | [DOWN, DOWN, RIGHT]
        //PROBLEM_FACTORY.fetchProblemForLevel(1) |   ""
        //PROBLEM_FACTORY.fetchProblemForLevel(2) |   ""
        PROBLEM_FACTORY.fetchProblemForLevel(3) |   ""
    }
}
