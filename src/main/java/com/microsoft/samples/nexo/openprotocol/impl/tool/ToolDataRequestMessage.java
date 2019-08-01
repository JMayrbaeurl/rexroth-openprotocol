package com.microsoft.samples.nexo.openprotocol.impl.tool;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * ToolDataRequestMessage
 */
public class ToolDataRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 40;

    public static final int STANDARD_REVISION = 1;

    public ToolDataRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "ToolDataRequestMessage []";
    }
}