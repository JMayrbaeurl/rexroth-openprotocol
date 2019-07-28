package com.microsoft.samples.nexo.openprotocol;

import java.util.Date;

/**
 * NexoDevice
 */
public interface NexoDevice {

    public static final int DEFAULT_PORT = 4545;

    public boolean startCommunication() throws NexoCommException;
    public int getBatteryLevel() throws NexoCommException;
    public Date getTime() throws NexoCommException;
    public boolean showOnDisplay(String message) throws NexoCommException;

    public int[] getTighteningprogramNumbers() throws NexoCommException;
}