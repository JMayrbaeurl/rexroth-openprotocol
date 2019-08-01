package com.microsoft.samples.nexo.openprotocol.impl.time;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * TimeRequestMessage
 */
public class TimeRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 80;

    public static final int STANDARD_REVISION = 1;

    public TimeRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "TimeRequestMessage []";
    }
}