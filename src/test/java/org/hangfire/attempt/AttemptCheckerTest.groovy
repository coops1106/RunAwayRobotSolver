package org.hangfire.attempt
import org.hangfire.RunAwayRobotTest

class AttemptCheckerTest extends RunAwayRobotTest {

    private static final int LEVEL = 8

    def AttemptChecker attemptChecker = new AttemptChecker()

    def "test"() {
        given:"A problem"
            def problem = PROBLEM_FACTORY.fetchProblemForLevel(LEVEL)
        when:"I check the result is correct"
            def result = attemptChecker.check(problem, attempt)
        then:
            result == expectedResult
        where:
            attempt                                         |   expectedResult
            AttemptFactory.defaultAttemptOfSize(LEVEL-1)    |   false
    }
}
