package com.microsoft.samples.nexo.openprotocol.impl.time;

import java.util.Date;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * TimeSetMessage
 */
public class TimeSetMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 82;

    public static final int STANDARD_REVISION = 1;

    private final Date newTime;

    public TimeSetMessage(Date useTime) {
        super(MESSAGEID, STANDARD_REVISION);

        this.newTime = useTime;
    }

    @Override
    public String toString() {
        return "TimeSetMessage [newTime=" + newTime + "]";
    }

    public Date getNewTime() {
        return newTime;
    }
}