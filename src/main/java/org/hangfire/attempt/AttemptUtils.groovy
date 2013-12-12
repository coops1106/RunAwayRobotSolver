package org.hangfire.attempt

class AttemptUtils {

    static String parseAttempt(Attempt attempt) {
        String returnString = ""
        for (Instruction ins in attempt.instructions) {
            returnString = ins==Instruction.LEFT? returnString + "R" : returnString + "D";
        }
        return returnString;
    }
}
