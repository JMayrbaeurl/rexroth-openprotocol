package com.microsoft.samples.nexo.openprotocol.impl.wifi;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * WifiLevelMessage
 */
public class WifiLevelMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 806;

    public static final int STANDARD_REVISION = 1;

    private int wifiLevel;

    public WifiLevelMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }
    
    public int getWifiLevel() {
        return wifiLevel;
    }

    public void setWifiLevel(int wifiLevel) {
        this.wifiLevel = wifiLevel;
    }
}