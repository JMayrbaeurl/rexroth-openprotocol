package com.microsoft.samples.nexo.openprotocol.impl.tool;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * ActivateRequestMessage
 */
public class ActivateRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 43;

    public static final int STANDARD_REVISION = 1;

    public ActivateRequestMessage() {
        super(ActivateRequestMessage.MESSAGEID, ActivateRequestMessage.STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "ActivateRequestMessage []";
    }
}