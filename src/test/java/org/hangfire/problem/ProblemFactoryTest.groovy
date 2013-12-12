package org.hangfire.problem
import org.hangfire.RunAwayRobotTest
import org.hangfire.source.HttpSource
import org.hangfire.source.StubSource
import spock.lang.Ignore

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
            problem.puzzleMap.equals(expectedPuzzleMap)
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
            96      | 51                | 30                        |   18                      |   new PuzzleMap("......X..XXX.X..X.....X.X.XX..........X..X...XX..X........X.....X.XXXX...XX.XXX....X.X..X......XX.XX...X....XX......X...X.X.....XX....X..X..X.XX.......X..X....X..X.XX....X.....X...X.X.XX..X..X.....XXX...........XX........XX...X.XX.X...X.......X.....X.....XX..X......X..X.....X.X..........X....X........X.X....X..........X.X.......XX.X...X..X.XX.X............X................X..X...X...XX...X...X.....X.X..X..X.........X.XX.XX.....X..XX...X.X..XX...X..X...X....X..X....X.X.....XXXX..............X...XX.X.X.........X...X.....X....X....X......X...X.XXX.....X...XX...X.......X.X..X..X.XX..XX..X.XX..X.........X.XX.X...XX.X.X....................X.X.XX...........X.X..X....X......X.XXX....X.X.X.X.....X...X.X..X.XXX...X.X...X.X......XX.X...XX.X...X............XX..X..XX..X....X............XX...X.X..X..XX...X..X...X.......XXX....X......XX.....X.XXX.....X.....X.X....X..........XX..X.X....X.X.........X.X..X.....X.....X...X.....X.X....X....X...............X.X...X....X......X...XX....XX...X........XX...........XXX.X...X.XXX..X..X..XXX.X.........X.........X....X...........XX.X.....X.X.XX...XX.....X....X...X....X..XX....XX....X..X....XX..XXX...X.....XXXX...X.X........X..X.................X.X.X.X........X..X.X..X..X.....X..X.........X.X..........X......X.X.....X..XXX....XX.......X.X......XX.X.........X.....XX.XX....X..X.....X.X.........X...X......X.X...XX..XXX....X.....X.X.........X..XX.XX.X......X.X.X.....X...X.X..X.XXX...X.....X.X.......X..X..X.........X.......X.X.X.X.XX.....X.X....XXX.....X.XX.X.X.XX..X.X.X.X.X.....XX..X...X......X....X....X........X...X.......XX....X..X....X..........XX.XX.X.X.X........X.XX..X...XX...X..XX.....X..XXXX..X.XX...XX......XX...X...X..X...X..XX.XX......X.X........X....XX..XX...X.X.X.XXX.X...X..XXXX..X.X....X.XX..X..X...X.....X.X.XX.....X..X.X...XX.......X.....XX...XX....X......X.....X.........XX....X..XX....XX..XX........X..X.........X........X...X...X..X....X.X...X....X.X.....X........X.XX..X.XXX.X..........XX..X.X.........X.........XX..XXX..XX..X..X.XXX...X...X..XX.XX.X....X...X.......X.X...X..X.......X..X..X.X....X..XX....X....X...X..X..XX.........X.................XX.XX....X..X.X..XX.........XX.X..X.....XX.X...X......X.X......X...........X..X.XXX...X.X...X..X....X...X....XX....X........X........XX...XX..XXX.X..X....X.X.X.X....X..X.XX.XX......X.X......XX.X..X..X........X....X......XX..X..X..........X.X.X..XXX.XX.XX.X.X.......X......X..XX.X.X..X.X..X....X.....XX...................X..X.X.....X........X..X.......XXX.XXX.X.XXX..X.X............X...XX.X..X..X...X..XXXX.X.X.X....X.........X...X......X..X..X.......XX.X....XX...X.X.X.......X......X...X.", 51)
    }

    @Ignore
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
