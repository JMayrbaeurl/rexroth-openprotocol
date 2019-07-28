package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * AbstractROPReplyMessage
 */
public abstract class AbstractROPReplyMessage extends AbstractROPMessage implements ROPReplyMessage {

    protected AbstractROPReplyMessage(int mid, int rev) {
        super(mid, rev);
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public boolean isOK() {
        return true;
    }
}