package com.microsoft.samples.nexo.openprotocol.impl.results;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * LastResultsSubStopMessage
 */
public class LastResultsSubStopMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 63;

    public static final int STANDARD_REVISION = 1;

    public LastResultsSubStopMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "LastResultsSubStopMessage []";
    }
}