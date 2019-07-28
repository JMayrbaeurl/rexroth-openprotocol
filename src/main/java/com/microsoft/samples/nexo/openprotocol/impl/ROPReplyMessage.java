package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * ROPReplyMessage
 */
public interface ROPReplyMessage extends ROPMessage {

    public boolean isOK();
    public boolean isError();
}