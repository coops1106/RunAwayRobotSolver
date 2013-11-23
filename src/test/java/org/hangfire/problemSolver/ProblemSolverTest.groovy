package org.hangfire.problemSolver
import org.hangfire.RunAwayRobotTest

class ProblemSolverTest extends RunAwayRobotTest {

    def ProblemSolver uut

    def setup() {
        uut = new ProblemSolver()
    }

    def test () {
        given: "A simple problem"
            def problem = PROBLEM_FACTORY.fetchProblemForLevel(2)
        when: "I solve"
            def attempt = uut.solve(problem)
        then:
            attempt == null
    }

}
