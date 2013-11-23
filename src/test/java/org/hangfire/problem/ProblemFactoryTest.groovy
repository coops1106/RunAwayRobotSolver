package org.hangfire.problem
import org.hangfire.RunAwayRobotTest
import org.hangfire.source.HttpSource
import org.hangfire.source.StubSource

public class ProblemFactoryTest extends RunAwayRobotTest {
	
	def ProblemFactory uut

    def "test with stub source"() {
        given:"A ProblemRepo with a source"
            uut = new ProblemFactory(new StubSource())
        when:"I fetch current problem"
            Problem problem = uut.fetchProblemForLevel(level);
        then:
            null != problem;
            problem.level == level
            problem.mapSize == expectedMapSize
            problem.maxInstructions == expectedMaxInstructions
            problem.minInstructions == expectedMinInstructions
            problem.puzzleMap == expectedPuzzleMap
        where:
            level   | expectedMapSize   | expectedMaxInstructions   |   expectedMinInstructions |   expectedPuzzleMap
            1       | 3                 | 2                         |   2                       |   new PuzzleMap("..X...X..", 3)
            2       | 4                 | 3                         |   2                       |   new PuzzleMap("..X.X......X.X..", 4)
            3       | 4                 | 3                         |   2                       |   new PuzzleMap("..XX.....X..XX..", 4)
            4       | 5                 | 3                         |   2                       |   new PuzzleMap(".XX...X.....X.....X.X....", 5)
            5       | 5                 | 3                         |   2                       |   new PuzzleMap("...X.X..X..X..........X..", 5)
            6       | 6                 | 4                         |   3                       |   new PuzzleMap("..XX..X..XX.X...X.......X.......X...", 6)
            7       | 6                 | 4                         |   3                       |   new PuzzleMap("..X.....X....X..........X....X....XX", 6)
            8       | 7                 | 4                         |   3                       |   new PuzzleMap(".X.X...............X.X...........X....X...X......", 7)

    }

    def "test with httpsource"() {
        given:
            uut = new ProblemFactory(new HttpSource())
        when:
            Problem problem = uut.fetchProblemForLevel(level)
        then:
            null != problem;
            problem.level == level
            problem.mapSize == expectedMapSize
            problem.maxInstructions == expectedMaxInstructions
            problem.minInstructions == expectedMinInstructions
        where:
            level   | expectedMapSize   | expectedMaxInstructions   |   expectedMinInstructions
            1       | 3                 | 2                         |   2
            2       | 4                 | 3                         |   2
            3       | 4                 | 3                         |   2
            4       | 5                 | 3                         |   2
            5       | 5                 | 3                         |   2
            6       | 6                 | 4                         |   3
            7       | 6                 | 4                         |   3
            8       | 7                 | 4                         |   3
    }
}
