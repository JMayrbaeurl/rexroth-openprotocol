package com.microsoft.samples.nexo.openprotocol.impl.comm;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * CommunicationStopMessage
 */
public class CommunicationStopMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 3;

    public static final int STANDARD_REVISION = 1;

    public CommunicationStopMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "CommunicationStopMessage []";
    }
}