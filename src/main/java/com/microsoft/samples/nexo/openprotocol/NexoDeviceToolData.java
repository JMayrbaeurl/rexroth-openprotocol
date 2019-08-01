package com.microsoft.samples.nexo.openprotocol;

import java.io.Serializable;
import java.util.Date;

/**
 * NexoDeviceToolData
 */
public class NexoDeviceToolData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String toolNumber;
    private int cycles;
    private Date lastMaintenanceDate;
    private String serialNumber;

    public NexoDeviceToolData() {
    }

    public NexoDeviceToolData(String toolNumber) {
        this.toolNumber = toolNumber;
    }
    
    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public Date getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(Date lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "NexoDeviceToolData [cycles=" + cycles + ", lastMaintenanceDate=" + lastMaintenanceDate
                + ", serialNumber=" + serialNumber + ", toolNumber=" + toolNumber + "]";
    }



}