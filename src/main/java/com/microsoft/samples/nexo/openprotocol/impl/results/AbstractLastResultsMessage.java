package com.microsoft.samples.nexo.openprotocol.impl.results;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * LastResultsMessage
 */
public abstract class AbstractLastResultsMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 61;

    public static final int STANDARD_REVISION = 1;

    protected AbstractLastResultsMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    protected AbstractLastResultsMessage(int rev) {
        super(MESSAGEID, rev);
    }
}