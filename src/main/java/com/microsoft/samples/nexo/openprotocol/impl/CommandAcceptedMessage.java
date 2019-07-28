package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * CommandAcceptedMessage
 */
public class CommandAcceptedMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 5;
    public static final int STANDARD_REVISION = 1;

    private int acceptedMessageID;

    public CommandAcceptedMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public int getAcceptedMessageID() {
        return acceptedMessageID;
    }

    public void setAcceptedMessageID(int acceptedMessageID) {
        this.acceptedMessageID = acceptedMessageID;
    }

    @Override
    public String toString() {
        return "CommandAcceptedMessage [acceptedMessageID=" + acceptedMessageID + "]";
    }

}