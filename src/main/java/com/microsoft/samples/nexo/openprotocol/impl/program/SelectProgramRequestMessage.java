package com.microsoft.samples.nexo.openprotocol.impl.program;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * SelectProgramRequestMessage
 */
public class SelectProgramRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 18;

    public static final int STANDARD_REVISION = 1;

    private final int programNumber;

    public SelectProgramRequestMessage(int number) {
        super(SelectProgramRequestMessage.MESSAGEID, SelectProgramRequestMessage.STANDARD_REVISION);

        this.programNumber = number;
    }

    @Override
    public String toString() {
        return "SelectProgramRequestMessage []";
    }

    public int getProgramNumber() {
        return programNumber;
    }
}