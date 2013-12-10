package org.hangfire.problem

import org.hangfire.RunAwayRobotTest

import java.awt.Point

class PuzzleMapUtilsTest extends RunAwayRobotTest {

    def "condenseMap"() {
        given: "A puzzleMap"
        def problem = RunAwayRobotTest.PROBLEM_FACTORY.fetchProblemForLevel(level)
        println problem
        def puzzleMap = problem.puzzleMap
        println puzzleMap
        when: "I condense"
        def point = new Point()
        point.y = yValue
        point.x = problem.maxInstructions - point.y
        println("Max instructions:" + problem.maxInstructions)
        def PuzzleMap output = PuzzleMapUtils.condensePuzzleMap(puzzleMap, point)
        then: "output is correct"
        output.map == expectedMap
        where:
        level | yValue | expectedMap
        1     | 0      | [[true], [false], [true]]
        1     | 1      | [[false, false], [false, false]]
        1     | 2      | [[true, false, true]]
        2     | 0      | [[false], [false], [true], [false]]
        2     | 1      | [[false, true], [false, true], [true, false]]
        2     | 2      | [[false, true, false], [false, false, false]]
        2     | 3      | [[false, true, false, false]]
        3     | 0      | [[true], [false], [true], [true]]
        3     | 1      | [[false, false], [false, false], [true, false]]
        3     | 2      | [[true, true, false], [false, false, true]]
        3     | 3      | [[true, false, false, true]]
        4     | 0      | [[false],[true],[true],[false]]
        4     | 1      | [[false, true],[true, true],[true, false]]
        4     | 2      | [[false, false, false],[true, true, false]]
        4     | 3      | [[false, true, false, false]]
    }
}
