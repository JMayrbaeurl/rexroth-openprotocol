package com.microsoft.samples.nexo.openprotocol.impl.comm;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * CommunicationKeepAliveMessage
 */
public class CommunicationKeepAliveMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 9999;

    public static final int STANDARD_REVISION = 1;

    public CommunicationKeepAliveMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "CommunicationKeepAliveMessage []";
    }
}