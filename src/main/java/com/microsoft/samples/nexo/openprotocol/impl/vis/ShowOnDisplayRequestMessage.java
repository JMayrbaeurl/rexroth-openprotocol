package com.microsoft.samples.nexo.openprotocol.impl.vis;

import java.util.Arrays;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * ShowOnDisplayRequestMessage
 */
public class ShowOnDisplayRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 111;
    public static final int STANDARD_REVISION = 1;

    private int displayDurationInSeconds;
    private boolean needsOKToDismiss = false;
    private String[] lines = new String[4];

    public ShowOnDisplayRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public int getDisplayDurationInSeconds() {
        return displayDurationInSeconds;
    }

    public void setDisplayDurationInSeconds(int displayDurationInSeconds) {
        this.displayDurationInSeconds = displayDurationInSeconds;
    }

    public boolean isNeedsOKToDismiss() {
        return needsOKToDismiss;
    }

    public void setNeedsOKToDismiss(boolean needsOKToDismiss) {
        this.needsOKToDismiss = needsOKToDismiss;
    }

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "ShowOnDisplayRequestMessage [displayDurationInSeconds=" + displayDurationInSeconds + ", lines="
                + Arrays.toString(lines) + ", needsOKToDismiss=" + needsOKToDismiss + "]";
    }
}