package com.microsoft.samples.nexo.openprotocol.impl.results;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * LastResultsAcknMessage
 */
public class LastResultsAcknMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 61;

    public static final int STANDARD_REVISION = 1;

    public LastResultsAcknMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "LastResultsAcknMessage []";
    }
}