package com.microsoft.samples.nexo.openprotocol.impl.batt;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * BatteryLevelRequestMessage
 */
public class BatteryLevelRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 800;

    public static final int STANDARD_REVISION = 1;

    public BatteryLevelRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }
}