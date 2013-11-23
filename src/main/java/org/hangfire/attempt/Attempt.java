package org.hangfire.attempt;

import java.util.ArrayList;
import java.util.List;

public class Attempt {
    private List<Instruction> instructions;
    private boolean success;
    private boolean valid = true;
    private int boomPoint;

    public int getSize() {
        return this.instructions.size();
    }

    public Instruction instructionAt(int position) {
        return this.instructions.get(position);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public int getBoomPoint() {
        return boomPoint;
    }

    public void setBoomPoint(final int boomPoint) {
        this.boomPoint = boomPoint;
    }

    public List<Instruction> getInstructionsUpToBoomPoint() {
        return new ArrayList<Instruction>(this.instructions.subList(0, this.boomPoint));
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
}
