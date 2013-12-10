
package org.hangfire.attempt
import org.hangfire.RunAwayRobotTest
import org.hangfire.problemSolver.ProblemSolver

public class ProblemSolverTest extends RunAwayRobotTest {

	private ProblemSolver uut = new ProblemSolver();

    def "test"() {
        given:"A problem"
            def problem = RunAwayRobotTest.PROBLEM_FACTORY.fetchProblemForLevel(1)
        when:"I solve"
            uut.solve(problem)
        then:"I call problem utils"


    }
	
	def givenAProblemAndASuccessfulPathWhenIAttemptToResolveThenIExpectToKnowTheProblemIsSolved() {
        given:
		    System.out.println("Attempt " + uut.getPath());
		    int failedInstructionIndex = uut.attemptToResolve(problem); //>>>
		    uut = uut.solutionFactory(uut, failedInstructionIndex);

        when:
		    System.out.println("Attempt " + uut.getPath());
		    failedInstructionIndex = uut.attemptToResolve(problem);//>v>
		    uut = uut.solutionFactory(uut, failedInstructionIndex);
		
		    System.out.println("Attempt " + uut.getPath());
		    failedInstructionIndex = uut.attemptToResolve(problem);//V>V
		    uut = uut.solutionFactory(uut, failedInstructionIndex);
		
		    System.out.println("Attempt " + uut.getPath());
		    failedInstructionIndex = uut.attemptToResolve(problem);
		    uut = uut.solutionFactory(uut, failedInstructionIndex);
		
		    System.out.println("Attempt " + uut.getPath());
		    failedInstructionIndex = uut.attemptToResolve(problem);
		    uut = uut.solutionFactory(uut, failedInstructionIndex);
        then:
            true
	}

}
/*	@Test
	public void givenAProblemWhenIInitializeAPathThenIExpectAPathOfRightDirectionsOfSizeMaxInstructions() {
		ArrayList<Instruction> testPath = new ArrayList<Instruction>();
		for (int i = 0; i < this.problem.getMaxInstructions(); i++) {
			testPath.add(Instruction.RIGHT);
		}
		
		assertEquals("Test path and get path should be the same length", testPath.size(), uut.getPath().size());
		assertTrue(uut.getPath().containsAll(testPath));
		assertTrue(testPath.containsAll(uut.getPath()));	
	}
	
	@Test
	public void givenAProblemWhenITryToResolveItIKnowTheIndexOfTheInstructionThatCausesTheFailure() {
		int instructionIndex = uut.attemptToResolve(this.problem);
		assertEquals(8, instructionIndex);
	}
	
	@Test
	public void givenAProblemAndAnInitialSolutionWhenAResolveAttemptFailsICanCreateANewSolutionContainer() {
		uut = uut.solutionFactory(uut, 8);
		int instructionIndex = uut.attemptToResolve(this.problem); //Boom at 9:2
		
		int i = 0;
		
		while(true) {
			System.out.println("" + ++i);
			if (i == 40) {
				System.out.println("Booya");
			}
			uut = uut.solutionFactory(uut, instructionIndex);
			instructionIndex = uut.attemptToResolve(this.problem); //Boom at 8:2
			System.out.println(uut.getPath());
			
		}
		

	}
}*/
