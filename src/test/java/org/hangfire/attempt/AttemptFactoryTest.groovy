package org.hangfire.attempt
import org.hangfire.RunAwayRobotTest

class AttemptFactoryTest extends RunAwayRobotTest {

    def "test"() {
        when: "I need a default attempt of size ?"
        def attempt = AttemptFactory.defaultAttemptOfSize(8)
        then: "then I get an attempt with correct instructions"
        attempt.instructions.containsAll(Instruction.RIGHT)
    }

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
        currentInstruction                                                                                                                                       | boomPoint | expectedInstruction                                                                                                                                     | valid
        [Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT] | 5         | [Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT] | true
        [Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT]  | 4         | [Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT] | true
        [Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.DOWN, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT]   | 3         | [Instruction.RIGHT, Instruction.RIGHT, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT] | true
        [Instruction.RIGHT, Instruction.RIGHT, Instruction.DOWN, Instruction.DOWN, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT]    | 3         | [Instruction.RIGHT, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT] | true
        [Instruction.RIGHT, Instruction.DOWN, Instruction.DOWN, Instruction.DOWN, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT]     | 2         | [Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT] | true
        [Instruction.DOWN, Instruction.RIGHT, Instruction.DOWN, Instruction.DOWN, Instruction.DOWN, Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT]     | 1         | null                                                                                                                                                    | false
    }
}
