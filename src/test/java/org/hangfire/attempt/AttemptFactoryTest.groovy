package org.hangfire.attempt
import org.hangfire.RunAwayRobotTest
import spock.lang.Ignore

class AttemptFactoryTest extends RunAwayRobotTest {

    def "defaultAttemptAreAllUps"() {
        when: "I need a default attempt of size ?"
        def attempt = AttemptFactory.defaultAttemptOfSize(8)
        then: "then I get an attempt with correct instructions"
        attempt.instructions.containsAll(Instruction.UP)
        attempt.size == 8
    }

    def "First Right Pre BoomPoint"() {
        given: "An Attempt"
            def Attempt attempt = new Attempt()
        and: "instructions"
            attempt.instructions = instructions
        and: "A boom point"
            attempt.boomPoint = boomPoint
        when:
            def result = AttemptFactory.getFirstUpPreBoomPoint(attempt)
        then:
        result == expectedResult
        where:
        instructions    | boomPoint | expectedResult
        [UP, UP]        | 2         | 1
        [UP, LEF]       | 4         | 0
        [LEFT, UP]      | 1         | -1
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
        currentInstruction                                       | boomPoint | expectedInstruction                                     | valid
        [RIGHT, RIGHT]                                           | 3         | [RIGHT, DOWN]                                            | true
        /*[RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | 5         | [RIGHT, RIGHT, RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, RIGHT, RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT]  | 4         | [RIGHT, RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, RIGHT, RIGHT, DOWN, DOWN, RIGHT, RIGHT, RIGHT]   | 3         | [RIGHT, RIGHT, DOWN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, RIGHT, DOWN, DOWN, DOWN, RIGHT, RIGHT, RIGHT]    | 3         | [RIGHT, DOWN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [RIGHT, DOWN, DOWN, DOWN, DOWN, RIGHT, RIGHT, RIGHT]     | 2         | [DOWN, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT] | true
        [DOWN, RIGHT, DOWN, DOWN, DOWN, RIGHT, RIGHT, RIGHT]     | 1         | null                                                    | false*/
    }
}
