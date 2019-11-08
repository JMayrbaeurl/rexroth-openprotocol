package com.microsoft.samples.nexo.openprotocol.impl.plc;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * OutSignalChangeSubRequestMessage
 */
public class OutSignalChangeSubRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 500;

    public static final int STANDARD_REVISION = 2;
    
    public OutSignalChangeSubRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "OutSignalChangeSubRequestMessage []";
    }
}