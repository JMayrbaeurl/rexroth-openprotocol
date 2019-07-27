package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;

/**
 * BatteryLevelMessageDeserializer
 */
public class BatteryLevelMessageDeserializer extends ROPMessageDeserializer {

    @Override
    public int datalength(int revision) {
        return 8;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof BatteryLevelMessage))
            throw new IllegalArgumentException("Resulting message type is not Battery Level");

        if (message instanceof BatteryLevelMessage) {
            BatteryLevelMessage reply = (BatteryLevelMessage)message;
            reply.setBatteryLevel(Integer.parseInt(str.substring(2, 5)));
        }

        return message;
    }

}