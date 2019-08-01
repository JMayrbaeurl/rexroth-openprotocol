package com.microsoft.samples.nexo.openprotocol.impl.time;

import java.util.Date;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * TimeMessage
 */
public class TimeMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 81;

    public static final int STANDARD_REVISION = 1;

    private Date deviceTime;

    public TimeMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public Date getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Date deviceTime) {
        this.deviceTime = deviceTime;
    }

    @Override
    public String toString() {
        return "TimeMessage [deviceTime=" + deviceTime + "]";
    }
}