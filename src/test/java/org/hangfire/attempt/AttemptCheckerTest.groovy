package org.hangfire.attempt
import org.hangfire.RunAwayRobotTest

class AttemptCheckerTest extends RunAwayRobotTest {

    private static final int LEVEL = 8

    def "test"() {
        given:"A problem"
            def problem = PROBLEM_FACTORY.fetchProblemForLevel(LEVEL)
        when:"I check the result is correct"
            def result = AttemptChecker.check(problem, attempt)
        then:
            result == expectedResult
        where:
            attempt                                         |   expectedResult
            AttemptFactory.defaultAttemptOfSize(LEVEL-1)    |   false
    }
}
