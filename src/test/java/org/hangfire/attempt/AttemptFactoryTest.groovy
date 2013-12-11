package org.hangfire.attempt

import org.hangfire.RunAwayRobotTest
import spock.lang.Ignore
import static org.hangfire.attempt.Instruction.*

class AttemptFactoryTest extends RunAwayRobotTest {

    def "defaultAttemptAreAllUps"() {
        when: "I need a default attempt of size ?"
        def attempt = AttemptFactory.defaultAttemptOfSize(8)
        then: "then I get an attempt with correct instructions"
        attempt.instructions.containsAll(Instruction.UP)
        attempt.size == 8
    }

    def "Alternative attempts"() {
        given: "An Attempt"
        def Attempt attempt = new Attempt()
        and: "instructions"
        attempt.instructions = instructions
        and: "A boom point"
        attempt.boomPoint = boomPoint
        when:
        def result = RunAwayRobotTest.ATTEMPT_FACTORY.createAlternativeAttempt(attempt)
        then:
        result.instructions == expectedResult
        result.valid == expectedValid
        where:
        instructions             | boomPoint | expectedResult             | expectedValid
        [UP, UP, UP, UP, UP]     | 5         | [UP, UP, UP, UP, LEFT]     | true
        [UP, UP, UP, UP, UP]     | 4         | [UP, UP, UP, LEFT, UP]     | true
        [UP, UP, UP, UP, UP]     | 3         | [UP, UP, LEFT, UP, UP]     | true
        [UP, UP, UP, UP, UP]     | 2         | [UP, LEFT, UP, UP, UP]     | true
        [UP, UP, UP, UP, UP]     | 1         | [LEFT, UP, UP, UP, UP]     | true

        [UP, UP, UP, UP, LEFT]   | 5         | null                       | false
        [UP, UP, UP, UP, LEFT]   | 4         | [UP, UP, UP, LEFT, LEFT]   | true
        [UP, UP, UP, UP, LEFT]   | 3         | [UP, UP, LEFT, UP, LEFT]   | true
        [UP, UP, UP, UP, LEFT]   | 2         | [UP, LEFT, UP, UP, LEFT]   | true
        [UP, UP, UP, UP, LEFT]   | 1         | [LEFT, UP, UP, UP, LEFT]   | true

        [UP, UP, UP, LEFT, UP]   | 5         | [UP, UP, UP, UP, LEFT]     | true
        [UP, UP, UP, LEFT, UP]   | 4         | [UP, UP, UP, UP, LEFT]     | true
        [UP, UP, UP, LEFT, UP]   | 3         | [UP, UP, LEFT, LEFT, UP]   | true
        [UP, UP, UP, LEFT, UP]   | 2         | [UP, LEFT, UP, LEFT, UP]   | true
        [UP, UP, UP, LEFT, UP]   | 1         | [LEFT, UP, UP, LEFT, UP]   | true

        [UP, UP, LEFT, UP, UP]   | 5         | [UP, UP, UP, UP, LEFT]     | true
        [UP, UP, LEFT, UP, UP]   | 4         | [UP, UP, UP, LEFT, UP]     | true
        [UP, UP, LEFT, UP, UP]   | 3         | [UP, UP, UP, LEFT, UP]     | true
        [UP, UP, LEFT, UP, UP]   | 2         | [UP, LEFT, LEFT, UP, UP]   | true
        [UP, UP, LEFT, UP, UP]   | 1         | [LEFT, UP, LEFT, UP, UP]   | true

        [UP, LEFT, UP, UP, UP]   | 5         | [UP, UP, UP, UP, LEFT]     | true
        [UP, LEFT, UP, UP, UP]   | 4         | [UP, UP, UP, LEFT, UP]     | true
        [UP, LEFT, UP, UP, UP]   | 3         | [UP, UP, LEFT, UP, UP]     | true
        [UP, LEFT, UP, UP, UP]   | 2         | [UP, UP, LEFT, UP, UP]     | true
        [UP, LEFT, UP, UP, UP]   | 1         | [LEFT, LEFT, UP, UP, UP]   | true

        [LEFT, UP, UP, UP, UP]   | 5         | [UP, UP, UP, UP, LEFT]     | true
        [LEFT, UP, UP, UP, UP]   | 4         | [UP, UP, UP, LEFT, UP]     | true
        [LEFT, UP, UP, UP, UP]   | 3         | [UP, UP, LEFT, UP, UP]     | true
        [LEFT, UP, UP, UP, UP]   | 2         | [UP, LEFT, UP, UP, UP]     | true
        [LEFT, UP, UP, UP, UP]   | 1         | [UP, LEFT, UP, UP, UP]     | true

        [UP, UP, UP, LEFT, LEFT] | 5         | null                       | false
        [UP, UP, UP, LEFT, LEFT] | 4         | null                       | false
        [UP, UP, UP, LEFT, LEFT] | 3         | [UP, UP, LEFT, LEFT, LEFT] | true
        [UP, UP, UP, LEFT, LEFT] | 2         | [UP, LEFT, UP, LEFT, LEFT] | true
        [UP, UP, UP, LEFT, LEFT] | 1         | [LEFT, UP, UP, LEFT, LEFT] | true
    }

    @Ignore
    def "test2"() {
        given: "A attempt with variable instructions and a boom point"
        def Attempt attempt = new Attempt()
        attempt.instructions = currentInstruction
        attempt.boomPoint = boomPoint

        when: "I create an alternative attempt"
        def Attempt alternativeAttempt = AttemptFactory.createAlternativeAttempt(attempt)
        then: "it is correctly formed and validity is correct"
        alternativeAttempt.instructions == expectedInstruction
        alternativeAttempt.valid == valid

        where:
        currentInstruction | boomPoint | expectedInstruction | valid
        [RIGHT, RIGHT]     | 3         | [RIGHT, DOWN]       | true
        /*[RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | 5         | [RIGHT, RIGHT, RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, RIGHT, RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT]  | 4         | [RIGHT, RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, RIGHT, RIGHT, DOWN, DOWN, RIGHT, RIGHT, RIGHT]   | 3         | [RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, RIGHT, DOWN, DOWN, DOWN, RIGHT, RIGHT, RIGHT]    | 3         | [RIGHT, DOWN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, DOWN, DOWN, DOWN, DOWN, RIGHT, RIGHT, RIGHT]     | 2         | [DOWN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [DOWN, RIGHT, DOWN, DOWN, DOWN, RIGHT, RIGHT, RIGHT]     | 1         | null                                                    | false*/
    }
}
