package com.microsoft.samples.nexo.openprotocol;

/**
 * TCPBasedNexoDevice
 */
public interface TCPBasedNexoDevice extends NexoDevice {

    public String listeningIPAddress();
    public int listeningPort();
}