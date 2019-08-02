package com.microsoft.samples.nexo.openprotocol.impl.comm;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * CommunicationKeepAliveReply
 */
public class CommunicationKeepAliveReply extends AbstractROPReplyMessage {

    public static final int MESSAGEID = CommunicationKeepAliveMessage.MESSAGEID;

    public static final int STANDARD_REVISION = 1;

    public CommunicationKeepAliveReply() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "CommunicationKeepAliveReply []";
    }
}