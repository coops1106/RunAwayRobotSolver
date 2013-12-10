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
        attempt.setValid(true);
        return attempt;
    }

    public static Attempt createAlternativeAttempt(final Attempt originalAttempt) {
        Attempt attempt = new Attempt();
        int boomInstruction = getFirstRightPreBoomPoint(originalAttempt);
        if (boomInstruction != -1) {
            List<Instruction> newInstructions = new ArrayList<Instruction>(originalAttempt.getInstructions().subList(0, boomInstruction+1));
            newInstructions = changeInstructions(newInstructions);
            completeListWithInstructionRight(newInstructions, originalAttempt.getSize());
            attempt.setInstructions(newInstructions);
            attempt.setValid(true);
        }
        return attempt;
    }

    public static int getFirstRightPreBoomPoint(final Attempt originalAttempt) {
        int boomPoint = originalAttempt.getBoomPoint();
        while(boomPoint > 0) {
            boomPoint--;
            int instructionPoint = boomPoint%originalAttempt.getSize();
            if (originalAttempt.instructionAt(instructionPoint) == Instruction.RIGHT) {
                return instructionPoint;
            }
        }
        return -1;
    }

    private static List<Instruction> completeListWithInstructionRight(List<Instruction> instructions, int size) {
        while(instructions.size() < size) {
            instructions.add(Instruction.RIGHT);
        }
        return instructions;
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
