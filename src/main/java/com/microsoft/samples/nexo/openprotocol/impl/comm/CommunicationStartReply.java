package com.microsoft.samples.nexo.openprotocol.impl.comm;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * CommunicationStartReply
 */
public abstract class CommunicationStartReply extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 2;

    public CommunicationStartReply() {
        super(MESSAGEID, 1);
    }

    public CommunicationStartReply(int rev) {
        super(MESSAGEID, rev);
    }

    @Override
    public int maximumRevision() {
        return 3;
    }
}