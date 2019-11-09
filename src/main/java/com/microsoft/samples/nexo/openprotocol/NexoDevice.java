package com.microsoft.samples.nexo.openprotocol;

import java.util.Date;

/**
 * NexoDevice
 */
public interface NexoDevice {

    public static final int DEFAULT_PORT = 4545;

    public boolean openSession();
    public void closeSession();

    public boolean startCommunication() throws NexoCommException;
    public void stopCommunication() throws NexoCommException;
    public void sendKeepAlive() throws NexoCommException;

    public int getBatteryLevel() throws NexoCommException;
    public Date getTime() throws NexoCommException;
    public void setTime(Date newTime) throws NexoCommException;
    public int getWIFILevel() throws NexoCommException;

    public boolean showOnDisplay(String message) throws NexoCommException;
    public boolean showOnDisplay(String message, int duration) throws NexoCommException;

    public boolean selectTighteningProgram(int programNumber) throws NexoCommException;
    public int[] getTighteningprogramNumbers() throws NexoCommException;
    public int[] getOKCounters() throws NexoCommException;
    public boolean subscribeToProgramChange() throws NexoCommException;

    public boolean activateTool() throws NexoCommException;
    public boolean deactivateTool() throws NexoCommException;
    public boolean isActivated() throws NexoCommException;
    public NexoDeviceToolData getToolData() throws NexoCommException;

    public interface ResultsHandler {

        public void handleResult(TighteningResults result);
    }

    public boolean subscribeToTighteningResults() throws NexoCommException;
    public Subscriber subscribeToTighteningResults(ResultsHandler handler);

    public PLCOutputSignalChange subscribeToOutputSignalChange() throws NexoCommException;
}