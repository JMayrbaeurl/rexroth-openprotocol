package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * AbstractROPReplyMessage
 */
public abstract class AbstractROPReplyMessage extends AbstractROPMessage implements ROPReplyMessage {

    protected AbstractROPReplyMessage(int mid, int rev) {
        super(mid, rev);
    }
}