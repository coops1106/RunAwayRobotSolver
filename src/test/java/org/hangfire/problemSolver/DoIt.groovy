package org.hangfire.problemSolver

import org.hangfire.RunAwayRobotTest
import org.hangfire.attempt.AttemptUtils
import org.hangfire.problem.ProblemFactory
import org.hangfire.source.HttpSource

class DoIt extends RunAwayRobotTest{

    def DoItNow () {
        given:
        def int level = 98
        def liveProblemFactory = new ProblemFactory(new HttpSource())
        def ProblemSolver problemSolver = new ProblemSolver()
        when:
        while (true) {
            def problem = liveProblemFactory.fetchProblemForLevel(level)
            def attempt = problemSolver.solve(problem)
            println HttpSource.sendSolution(AttemptUtils.parseAttempt(attempt))
            level ++
        }
        then:
        true
    }

}
