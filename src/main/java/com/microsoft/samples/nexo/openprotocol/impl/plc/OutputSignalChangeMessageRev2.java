package com.microsoft.samples.nexo.openprotocol.impl.plc;

import com.microsoft.samples.nexo.openprotocol.PLCOutputSignalChange;
import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * OutputSignalChangeMessageRev2
 */
public class OutputSignalChangeMessageRev2 extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 501;

    public static final int STANDARD_REVISION = 2;

    public OutputSignalChangeMessageRev2() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    private PLCOutputSignalChange change;

    public PLCOutputSignalChange getChange() {
        return change;
    }

    public void setChange(PLCOutputSignalChange change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "OutputSignalChangeMessageRev2 [change=" + change + "]";
    }

    @Override
    public int maximumRevision() {
        
        return OutputSignalChangeMessageRev2.STANDARD_REVISION;
    }
}