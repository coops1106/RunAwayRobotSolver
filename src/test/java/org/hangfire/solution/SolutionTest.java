package org.hangfire.solution;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.hangfire.problem.ProblemContainer;
import org.junit.Test;

public class SolutionTest {

	private SolutionContainer uut = new SolutionContainer();
	private static int TEST_PATH_SIZE;

	@Test
	public void givenINeedToSolveAPuzzleWhenINeedASolutionContainerIGetOne() {
		assertNotNull(uut);
	}
	
	@Test
	public void givenAPathSizeWhenIInitializeAPathThenIExpectAPathFullOfRightDirections() {
		ArrayList<Instruction> testPath = new ArrayList<Instruction>();
		for (Instruction instruction : testPath) {
			instruction.setDirection(Instruction.RIGHT);
		}
		uut.initializePath(TEST_PATH_SIZE);
		assertTrue(uut.getPath().containsAll(testPath));	
	}

}
