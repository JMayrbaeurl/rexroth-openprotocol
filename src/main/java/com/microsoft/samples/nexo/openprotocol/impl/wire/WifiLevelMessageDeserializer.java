package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wifi.WifiLevelMessage;

/**
 * WifiLevelMessageDeserializer
 */
public class WifiLevelMessageDeserializer extends ROPMessageDeserializer {

    @Override
    public int datalength(int revision) {
        
        return 6;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
        throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof WifiLevelMessage))
            throw new IllegalArgumentException("Resulting message type is not Wifi Level");

        if (message instanceof WifiLevelMessage) {
            WifiLevelMessage reply = (WifiLevelMessage)message;
            reply.setWifiLevel(Integer.parseInt(str.substring(2, 6)));
        }

        return message;
    }

    
}