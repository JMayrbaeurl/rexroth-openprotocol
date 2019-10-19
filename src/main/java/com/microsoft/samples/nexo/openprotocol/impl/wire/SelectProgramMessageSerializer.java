package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.util.Formatter;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.SelectProgramRequestMessage;

/**
 * SelectProgramMessageSerializer
 */
public class SelectProgramMessageSerializer extends ROPMessageSerializer {

    @Override
    public String toString(ROPMessage message) {
        
        if (message == null)
            throw new IllegalArgumentException("Parameter 'message' must not be null");
        if (!(message instanceof SelectProgramRequestMessage))
            throw new IllegalArgumentException("Parameter 'message' has to be of type SelectProgramRequestMessage");

        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("%s%03d", super.toString(message), ((SelectProgramRequestMessage)message).getProgramNumber());
        fmt.close();

        return String.format("%04d", sbuf.length()) + sbuf.toString().substring(4);
    }

}