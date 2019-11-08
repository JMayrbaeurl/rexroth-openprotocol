package com.microsoft.samples.nexo.openprotocol.impl.program;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * ProgramChangeSubRequestMessage
 */
public class ProgramChangeSubRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 14;

    public static final int STANDARD_REVISION = 1;

    public ProgramChangeSubRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "ProgramChangeSubRequestMessage []";
    }
}