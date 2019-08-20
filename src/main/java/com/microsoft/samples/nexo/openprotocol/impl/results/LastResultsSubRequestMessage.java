package com.microsoft.samples.nexo.openprotocol.impl.results;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * LastResultsSubRequestMessage
 */
public class LastResultsSubRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 60;

    public static final int STANDARD_REVISION = 1;
    
    public LastResultsSubRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "LastResultsSubRequestMessage []";
    }
}