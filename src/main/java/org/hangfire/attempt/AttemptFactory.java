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

/*    public static Attempt createAlternativeAttempt(final Attempt originalAttempt) {
        Attempt attempt = new Attempt();
        int boomInstruction = getFirstUpPreBoomPoint(originalAttempt);
        if (boomInstruction != -1) {
            List<Instruction> newInstructions = new ArrayList<Instruction>(originalAttempt.getInstructions().subList(0, boomInstruction+1));
            newInstructions = changeInstructions(newInstructions);
            completeListWithInstructionRight(newInstructions, originalAttempt.getSize());
            attempt.setInstructions(newInstructions);
            attempt.setValid(true);
        }
        return attempt;
    }*/

    private static List<Instruction> completeListWithInstructionRight(List<Instruction> instructions, int size) {
        while(instructions.size() < size) {
            instructions.add(Instruction.UP);
        }
        return instructions;
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
