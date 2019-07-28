package com.microsoft.samples.nexo.openprotocol.impl.program;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * ProgramNumbersMessage
 */
public class ProgramNumbersMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 11;

    public static final int STANDARD_REVISION = 1;

    List<Integer> programNumbers = new ArrayList<Integer>();

    public ProgramNumbersMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public int[] programNumbers() {
        
        int[] result = new int[this.programNumbers.size()];
        for(int i = 0; i < this.programNumbers.size(); i++)
            result[i] = this.programNumbers.get(i).intValue();

        return result;
    }

    public List<Integer> getProgramNumbers() {
        return programNumbers;
    }

    public void setProgramNumbers(List<Integer> programNumbers) {
        this.programNumbers = programNumbers;
    }

    @Override
    public String toString() {
        return "ProgramNumbersMessage [programNumbers=" + programNumbers + "]";
    }
}