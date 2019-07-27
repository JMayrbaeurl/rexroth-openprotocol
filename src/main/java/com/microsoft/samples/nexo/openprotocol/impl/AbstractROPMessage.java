package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * AbstractROPMessage
 */
public abstract class AbstractROPMessage implements ROPMessage {

    private int messageID;
    private int revision;

    protected AbstractROPMessage(int mid, int rev) {

        this.messageID = mid;
        this.revision = rev;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    @Override
    public int messageID() {
        return this.messageID;
    }

    @Override
    public int revision() {
        return this.revision;
    }

    @Override
    public boolean isRevisionSupported(int rev) {
        return rev >= this.minimumRevision() && rev <= this.maximumRevision();
    }

    @Override
    public int maximumRevision() {
        return 1;
    }

    @Override
    public int minimumRevision() {
        return 1;
    }
}