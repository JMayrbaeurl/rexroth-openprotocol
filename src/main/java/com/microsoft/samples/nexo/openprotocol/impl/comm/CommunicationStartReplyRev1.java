package com.microsoft.samples.nexo.openprotocol.impl.comm;

/**
 * CommunicationStartReplyRev1
 */
public class CommunicationStartReplyRev1 extends CommunicationStartReply {

    private int cellID;
    private int channelID;
    private String controllerName;

    public CommunicationStartReplyRev1() {
        super(1);
    }

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    @Override
    public String toString() {
        return "CommunicationStartReplyRev1 [cellID=" + cellID + ", channelID=" + channelID + ", controllerName="
                + controllerName + "]";
    }

}