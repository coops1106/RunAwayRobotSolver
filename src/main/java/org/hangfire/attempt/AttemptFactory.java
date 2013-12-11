package org.hangfire.attempt;

import java.util.ArrayList;
import java.util.List;

public class AttemptFactory {

    public Attempt defaultAttemptOfSize(int size) {
        Attempt attempt = new Attempt();
        List<Instruction> instructions = new ArrayList<Instruction>(size);
        for(int i=0; i<size; i++) {
            instructions.add(Instruction.UP);
        }
        attempt.setInstructions(instructions);
        attempt.setValid(true);
        attempt.setAttemptOutcome(AttemptOutcome.NOT_YET_KNOWN);
        return attempt;
    }

    public Attempt createAlternativeAttempt(final Attempt originalAttempt) {
        Attempt attempt = new Attempt();
        int boomInstruction = getFirstUpFromBoomPoint(originalAttempt);
        if (boomInstruction >= 0) {
            List<Instruction> newInstructions = new ArrayList<Instruction>(originalAttempt.getInstructions().subList(boomInstruction+1, originalAttempt.getSize()));
            newInstructions.add(0, Instruction.LEFT);
            completeListWithInstructionUp(newInstructions, originalAttempt.getSize());
            attempt.setInstructions(newInstructions);
            attempt.setValid(true);
        } else {
            attempt.setValid(false);
        }
        return attempt;
    }

    private int getFirstUpFromBoomPoint(final Attempt originalAttempt) {
        int instructionIndex = originalAttempt.getBoomPoint()-1;
        try {
            while (originalAttempt.instructionAt(instructionIndex) == Instruction.LEFT && instructionIndex <= originalAttempt.getSize()) {
                instructionIndex++;
            }
        } catch (IndexOutOfBoundsException IOOBE) {
            instructionIndex = -1;
        }
        return instructionIndex;
    }

    private void completeListWithInstructionUp(List<Instruction> instructions, int size) {
        while(instructions.size() < size) {
            instructions.add(0, Instruction.UP);
        }
    }

    private static List<Instruction> changeInstructions(final List<Instruction> instructions) throws ArrayIndexOutOfBoundsException {
        int i = instructions.size() -1;
        while(instructions.get(i) == Instruction.UP) {
            i--;
        }
        List<Instruction> changedInstructions = instructions.subList(0, i);
        changedInstructions.add(Instruction.UP);
        return changedInstructions;
    }
}
