package com.microsoft.samples.nexo.openprotocol.impl.batt;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * BatteryLevelMessage
 */
public class BatteryLevelMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 801;

    public static final int STANDARD_REVISION = 1;

    private int batteryLevel;

    public BatteryLevelMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Override
    public String toString() {
        return "BatteryLevelMessage [batteryLevel=" + batteryLevel + "]";
    }

}