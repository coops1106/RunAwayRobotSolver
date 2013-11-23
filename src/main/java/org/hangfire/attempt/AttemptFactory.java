package org.hangfire.attempt;

import java.util.ArrayList;
import java.util.List;

public class AttemptFactory {

    public static Attempt defaultAttemptOfSize(int size) {
        Attempt attempt = new Attempt();
        List<Instruction> instructions = new ArrayList<Instruction>(size);
        for(int i=0; i<size; i++) {
            instructions.add(Instruction.RIGHT);
        }
        attempt.setInstructions(instructions);
        return attempt;
    }

    public static Attempt createAlternativeAttempt(final Attempt originalAttempt) {
        Attempt attempt = new Attempt();
        List<Instruction> newInstructions = originalAttempt.getInstructionsUpToBoomPoint();
        try {
            newInstructions = changeInstructions(newInstructions);
            completeListWithInstructionRight(newInstructions, originalAttempt.getSize());
            attempt.setInstructions(newInstructions);
        } catch (ArrayIndexOutOfBoundsException AIOOBE) {
            attempt.setValid(false);
        }
        return attempt;
    }

    private static List<Instruction> completeListWithInstructionRight(List<Instruction> instructions, int size) {
        while(instructions.size() < size) {
            instructions.add(Instruction.RIGHT);
        }
        return instructions;
    }

    private static List<Instruction> copyOriginalAttemptInstructionsPreBoomPoint(final Attempt originalAttempt) {
        return originalAttempt.getInstructionsUpToBoomPoint();
    }

    private static List<Instruction> changeInstructions(final List<Instruction> instructions) throws ArrayIndexOutOfBoundsException {
        int i = instructions.size() -1;
        while(instructions.get(i) == Instruction.DOWN) {
            i--;
        }
        List<Instruction> changedInstructions = instructions.subList(0, i);
        changedInstructions.add(Instruction.DOWN);
        return changedInstructions;
    }
}
