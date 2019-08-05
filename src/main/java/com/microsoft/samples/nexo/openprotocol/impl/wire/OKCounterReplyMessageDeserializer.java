package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.job.OKCounterReplyMessage;

/**
 * OKCounterReplyMessageDeserializer
 */
public class OKCounterReplyMessageDeserializer extends ROPMessageDeserializer {

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof OKCounterReplyMessage))
            throw new IllegalArgumentException("Resulting message type is not OK Counter reply");

        if (message instanceof OKCounterReplyMessage) {
            OKCounterReplyMessage reply = (OKCounterReplyMessage)message;
            
            int numCounters = Integer.parseInt(str.substring(0, 2));
            if (numCounters < 0) numCounters = 0;
            for(int i = 0; i < numCounters; i++) {
                reply.getCounters().add(new Integer(Integer.parseInt(str.substring(2+(i*2), 4+(i*2)))));
            }
        }

        return message;
    }

}