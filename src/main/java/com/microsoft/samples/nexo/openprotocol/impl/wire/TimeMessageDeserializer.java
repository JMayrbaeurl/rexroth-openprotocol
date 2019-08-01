package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TimeMessageDeserializer
 */
public class TimeMessageDeserializer extends ROPMessageDeserializer {

    private static final Logger logger = LoggerFactory.getLogger(TimeMessageDeserializer.class);

    @Override
    public int datalength(int revision) {
        return 19;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof TimeMessage))
            throw new IllegalArgumentException("Resulting message type is not Time message");

        if (message instanceof TimeMessage) {
            TimeMessage reply = (TimeMessage)message;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
            try {
                reply.setDeviceTime(formatter.parse(str));
            } catch (ParseException e) {
                logger.error("Exception on parsing Tightening device time string: " + str);
			}
        }

        return message;
    }
}