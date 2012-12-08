package org.hangfire.solution;

import java.util.ArrayList;
import java.util.List;

import org.hangfire.problem.ProblemContainer;

public class SolutionContainer {
	
	private List<Instruction> instructionPath;
	
	public void initializePath(int size) {
		this.instructionPath = new ArrayList<Instruction>(size);
		for (Instruction instruction : this.instructionPath) {
			instruction.setDirection(Instruction.RIGHT);
		}
	}
	
	public List<Instruction> getPath() {
		return this.instructionPath;
	}

//	public void resolveProblem(Problem problem) {
//		for (String tile : problem.getMapArray()) {
//			
//		}
//	}
	
}
