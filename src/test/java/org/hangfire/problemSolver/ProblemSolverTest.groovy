package org.hangfire.problemSolver
import org.hangfire.RunAwayRobotTest
import static org.hangfire.attempt.Instruction.*

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
        println attempt
        then:
        attempt.instructions
        where:
        theProblem | theSolution
        PROBLEM_FACTORY.fetchProblemForLevel(1) |   [LEFT, UP]
        //PROBLEM_FACTORY.fetchProblemForLevel(2) | [LEFT, UP]
    }
}
