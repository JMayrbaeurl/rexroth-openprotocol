package com.microsoft.samples.nexo.openprotocol.impl.program;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * ProgramNumbersRequestMessage
 */
public class ProgramNumbersRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 10;

    public static final int STANDARD_REVISION = 1;

    public ProgramNumbersRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "ProgramNumbersRequestMessage []";
    }
}