package org.hangfire.problem

import org.hangfire.RunAwayRobotTest

import java.awt.*

class ProblemUtilsTest extends RunAwayRobotTest {

    def "Starting points"() {
        given: "A problem"
        def problem = RunAwayRobotTest.PROBLEM_FACTORY.fetchProblemForLevel(level)
        when: "I fetch starting points"
        def output = [:]
        output = ProblemUtils.fetchStartingPoints(problem)
        then: "I get a map of starting points"
        output.size() == expectedSize
        output[expectedKey].size == expectedValueSize
        output[expectedKey] == points
        where:
        level | expectedSize | expectedKey | expectedValueSize | points
        1     | 1            | 2           | 3                 | [new Point(2,0), new Point(1,1), new Point(0,2)]
        2     | 2            | 2           | 3                 | [new Point(2,0), new Point(1,1), new Point(0,2)]
        2     | 2            | 3           | 4                 | [new Point(3,0), new Point(2,1), new Point(1,2), new Point(0,3)]
        3     | 2            | 2           | 3                 | [new Point(2,0), new Point(1,1), new Point(0,2)]
        3     | 2            | 3           | 4                 | [new Point(3,0), new Point(2,1), new Point(1,2), new Point(0,3)]
        4     | 2            | 2           | 3                 | [new Point(2,0), new Point(1,1), new Point(0,2)]
        4     | 2            | 3           | 4                 | [new Point(3,0), new Point(2,1), new Point(1,2), new Point(0,3)]
        5     | 2            | 3           | 4                 | [new Point(3,0), new Point(2,1), new Point(1,2), new Point(0,3)]
        6     | 2            | 4           | 5                 | [new Point(4,0), new Point(3,1), new Point(2,2), new Point(1,3), new Point(0,4)]
        7     | 2            | 3           | 4                 | [new Point(3,0), new Point(2,1), new Point(1,2), new Point(0,3)]
        8     | 2            | 4           | 5                 | [new Point(4,0), new Point(3,1), new Point(2,2), new Point(1,3), new Point(0,4)]
    }
}
