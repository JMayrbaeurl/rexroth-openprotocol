package com.microsoft.samples.nexo.openprotocol.impl.results;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * LastResultsCancelRequestMessage
 */
public class LastResultsCancelRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 63;

    public static final int STANDARD_REVISION = 1;

    public LastResultsCancelRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "LastResultsCancelRequestMessage []";
    }
}