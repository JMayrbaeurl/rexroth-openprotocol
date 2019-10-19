package com.microsoft.samples.nexo.openprotocol.impl.tool;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * DeactivateRequestMessage
 */
public class DeactivateRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 42;

    public static final int STANDARD_REVISION = 1;

    public DeactivateRequestMessage() {
        super(DeactivateRequestMessage.MESSAGEID, DeactivateRequestMessage.STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "DeactivateRequestMessage []";
    }
}