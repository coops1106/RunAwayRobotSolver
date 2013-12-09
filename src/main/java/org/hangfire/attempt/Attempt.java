package org.hangfire.attempt;

import java.util.List;

public class Attempt {
    private List<Instruction> instructions;
    private boolean valid;
    private int boomPoint;
    private AttemptOutcome attemptOutcome = AttemptOutcome.UNABLE_TO_SOLVE;

    public int getSize() {
        return this.instructions.size();
    }

    public Instruction instructionAt(int position) {
        return this.instructions.get(position);
    }

    public int getBoomPoint() {
        return boomPoint;
    }

    public void setBoomPoint(final int boomPoint) {
        System.out.println("Setting Boom Point : " + boomPoint);
        this.boomPoint = boomPoint;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(final List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean valid) {
        this.valid = valid;
    }

    public AttemptOutcome getAttemptOutcome() {
        return attemptOutcome;
    }

    public void setAttemptOutcome(final AttemptOutcome attemptOutcome) {
        this.attemptOutcome = attemptOutcome;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "instructions=" + instructions +
                ", valid=" + valid +
                ", boomPoint=" + boomPoint +
                ", attemptOutcome=" + attemptOutcome +
                '}';
    }
}
