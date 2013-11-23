package org.hangfire.problemSolver;

import org.hangfire.attempt.Attempt;
import org.hangfire.attempt.AttemptFactory;
import org.hangfire.problem.Problem;
import org.hangfire.problem.PuzzleMap;

public class ProblemSolver {
	
	private boolean solved = false;

    public Attempt solve(final Problem problem) {
        Attempt attempt = null;
        final int minInstructions = problem.getMinInstructions();
        final int maxInstructions = problem.getMaxInstructions();
        for (int instructionSize = minInstructions; instructionSize <= maxInstructions; instructionSize++) {
            attempt = AttemptFactory.defaultAttemptOfSize(instructionSize);
            PuzzleMap puzzleMap = problem.getPuzzleMap();
            if (checkAttempt(attempt)) {
                solved = true;
                break;
            }
        }
        return solved? attempt : null;
    }

    private boolean checkAttempt(final Attempt attempt) {
        return false;
    }



/*
	public ProblemSolver solutionFactory(ProblemSolver problemSolver, int failedInstructionIndex) {
		ProblemSolver newProblemSolver = new ProblemSolver(problemSolver.getPath().size());
		
		//Copy old problemSolver up to and including the failed instruction
		for (int i = 0; i <= failedInstructionIndex; i++) {
			newProblemSolver.getInstructionPath().set(i, problemSolver.getInstructionPath().get(i));
		}
		
		
		while (newProblemSolver.getInstructionPath().get(failedInstructionIndex).toString().equals(Instruction.DOWN)) {
			if (--failedInstructionIndex == -1) {
				newProblemSolver = new ProblemSolver(instructionSize -1);
				return newProblemSolver;
			}
		}
		
		newProblemSolver.getInstructionPath().set(failedInstructionIndex, Instruction.DOWN);
		
		newProblemSolver.populateInstructionPathWithRightsFrom(failedInstructionIndex+1, newProblemSolver);
		
		return newProblemSolver;
	}
	

	
	public List<Instruction> getPath() {
		return this.instructionPath;
	}

	public int attemptToResolve(Problem problem) {
		int column = 1;
		int row = 1;
		int instructionIndex = 0;
		
		while(!this.isSolved()) {
			for(instructionIndex = 0; instructionIndex < instructionPath.size(); instructionIndex++) {
				if(instructionPath.get(instructionIndex).toString().equals(Instruction.RIGHT)) {
					column++;
				} else {
					row++;
				}
				
				if (column == problem.getMapSize() + 1 || row == problem.getMapSize() + 1) {
					System.out.println("success");
					this.setSolved(true);
				}
				
				System.out.println("Assessing: " + column + ":" + row);
				
				if (problem.getPuzzleMap().getTileAt(column, row).getStatus() == (Tile.BLOCKED)) {
					System.out.println("Boom at: " + column + ":" + row);
					System.out.println("returning " + instructionIndex);
					return instructionIndex;
				}
			}
		}
		return instructionIndex;
	}

	private List<Instruction> getInstructionPath() {
		return instructionPath;
	}

	public void setInstructionPath(List<Instruction> instructionPath) {
		this.instructionPath = instructionPath;
	}

	private void populateInstructionPathWithRightsFrom(int position, ProblemSolver s) {
		if (s.getInstructionPath().isEmpty()) {
			for (int i = position; i < s.instructionSize; i++) {
				s.instructionPath.add(Instruction.RIGHT);
			}
		} else {
			for (int i = position; i < s.instructionSize; i++) {
				s.instructionPath.set(i, Instruction.RIGHT);
			}
		}
	}

	private int getInstructionSize() {
		return instructionSize;
	}

	private void setInstructionSize(int instructionSize) {
		this.instructionSize = instructionSize;
	}

	private boolean isSolved() {
		return solved;
	}

	private void setSolved(boolean solved) {
		this.solved = solved;
	}
	*/
}
