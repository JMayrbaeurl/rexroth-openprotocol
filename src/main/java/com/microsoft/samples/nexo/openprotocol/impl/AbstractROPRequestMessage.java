package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * AbstractROPRequestMessage
 */
public abstract class AbstractROPRequestMessage extends AbstractROPMessage implements ROPRequestMessage {

    protected AbstractROPRequestMessage(int mid, int rev) {
        super(mid, rev);
    }
    
}