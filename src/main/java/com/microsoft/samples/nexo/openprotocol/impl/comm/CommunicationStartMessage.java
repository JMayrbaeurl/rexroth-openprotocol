package com.microsoft.samples.nexo.openprotocol.impl.comm;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * CommunicationStartMessage
 */
public class CommunicationStartMessage extends AbstractROPRequestMessage{

    public static final int MESSAGEID = 1;

    public static final int STANDARD_REVISION = 1;

    public CommunicationStartMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }
}