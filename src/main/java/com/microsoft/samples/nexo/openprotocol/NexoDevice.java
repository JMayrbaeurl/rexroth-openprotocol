package com.microsoft.samples.nexo.openprotocol;

/**
 * NexoDevice
 */
public interface NexoDevice {

    public static final int DEFAULT_PORT = 4545;

    public boolean startCommunication() throws NexoCommException;

    public int getBatteryLevel() throws NexoCommException;
}