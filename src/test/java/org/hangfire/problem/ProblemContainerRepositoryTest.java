package org.hangfire.problem;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProblemContainerRepositoryTest {
	
	ProblemContainerRepository uut = new ProblemContainerRepository();
	
	@Test
	public void givenIWantToSolveRunAwayRobotProblemsWhenINeedAProblemRepositoryIGetOne() {
		assertNotNull(this.uut);
	}
	
	@Test
	public void givenIWantToSolveTheCurrentLevelInRunAwayRobotWhenINeedItIGetAPopulatedProblemContainer() {
		String problemString = uut.fetchCurrentProblem();
		assertNotNull(problemString);
	}
	
}
