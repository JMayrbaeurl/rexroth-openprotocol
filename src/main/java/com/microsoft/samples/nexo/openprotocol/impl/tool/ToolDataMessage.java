package com.microsoft.samples.nexo.openprotocol.impl.tool;

import com.microsoft.samples.nexo.openprotocol.NexoDeviceToolData;
import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * ToolDataMessage
 */
public class ToolDataMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 41;

    public static final int STANDARD_REVISION = 1;

    private NexoDeviceToolData toolData;

    public ToolDataMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public NexoDeviceToolData getToolData() {
        return toolData;
    }

    public void setToolData(NexoDeviceToolData toolData) {
        this.toolData = toolData;
    }

    @Override
    public String toString() {
        return "ToolDataMessage [toolData=" + toolData + "]";
    }
}