package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.PLCOutputSignalChange;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.plc.OutputSignalChangeMessageRev2;

/**
 * OutputSignalChangeMessageRev2Deserializer
 * 0036 0501 002 000000000 | 0001 1000 0100 1000
 */
public class OutputSignalChangeMessageRev2Deserializer extends ROPMessageDeserializer {

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof OutputSignalChangeMessageRev2))
            throw new IllegalArgumentException("Resulting message type is not Last Results reply");

        if (message instanceof OutputSignalChangeMessageRev2) {
            ((OutputSignalChangeMessageRev2)message).setChange(new PLCOutputSignalChange(str));
        }

        return message;
    }

    @Override
    public int datalength(int revision) {
        return 16;
    }
}