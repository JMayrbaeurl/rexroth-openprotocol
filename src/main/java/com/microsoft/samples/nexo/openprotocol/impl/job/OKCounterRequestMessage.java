package com.microsoft.samples.nexo.openprotocol.impl.job;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPRequestMessage;

/**
 * OKCounterRequestMessage
 */
public class OKCounterRequestMessage extends AbstractROPRequestMessage {

    public static final int MESSAGEID = 30;

    public static final int STANDARD_REVISION = 1;

    public OKCounterRequestMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    @Override
    public String toString() {
        return "OKCounterRequestMessage []";
    }

}