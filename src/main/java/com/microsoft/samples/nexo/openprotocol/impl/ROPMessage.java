package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * ROPMessage
 */
public interface ROPMessage {

    public int messageID();

    public int revision();
    public int minimumRevision();
    public int maximumRevision();
    public boolean isRevisionSupported(int rev);
}