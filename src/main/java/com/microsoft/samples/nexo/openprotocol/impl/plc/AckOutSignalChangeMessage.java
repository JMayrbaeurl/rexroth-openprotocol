package com.microsoft.samples.nexo.openprotocol.impl.plc;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * AckOutSignalChangeMessage
 */
public class AckOutSignalChangeMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 502;

    public static final int STANDARD_REVISION = 1;
    
    public AckOutSignalChangeMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "AckOutSignalChangeMessage []";
    }
}