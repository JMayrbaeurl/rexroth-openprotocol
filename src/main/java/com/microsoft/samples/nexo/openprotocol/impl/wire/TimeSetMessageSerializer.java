package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.text.SimpleDateFormat;
import java.util.Formatter;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeSetMessage;

/**
 * TimeSetMessageSerializer
 */
public class TimeSetMessageSerializer extends ROPMessageSerializer {

    private String datePattern = "yyyy-MM-dd:HH:mm:ss";

    @Override
    public String toString(ROPMessage message) {
        
        if (message == null)
            throw new IllegalArgumentException("Parameter 'message' must not be null");
        if (!(message instanceof TimeSetMessage))
            throw new IllegalArgumentException("Parameter 'message' has to be of type TimeSetMessage");

        StringBuilder sbuf = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat(this.datePattern);
        Formatter fmt = new Formatter(sbuf);
        fmt.format("%s%s", super.toString(message), formatter.format(((TimeSetMessage)message).getNewTime()));
        fmt.close();

        return String.format("%04d", sbuf.length()) + sbuf.toString().substring(4);
    }
}