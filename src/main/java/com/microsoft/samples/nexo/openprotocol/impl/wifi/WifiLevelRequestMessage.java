package com.microsoft.samples.nexo.openprotocol.impl.wifi;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * WifiLevelRequestMessage
 */
public class WifiLevelRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 805;

    public static final int STANDARD_REVISION = 1;

    public WifiLevelRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "WifiLevelRequestMessage []";
    }
}