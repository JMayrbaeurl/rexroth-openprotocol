package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.util.Formatter;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;

/**
 * ROPMessageSerializer
 */
public class ROPMessageSerializer {

    public static final String DEFAULTHEADER = "0020%04d%03d000000000";

    public String toString(final ROPMessage message) {

       if (message == null)
            throw new IllegalArgumentException("Parameter 'message' must not be null");

        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format(DEFAULTHEADER, message.messageID(), message.revision());
        fmt.close();

        return sbuf.toString();
    }
}