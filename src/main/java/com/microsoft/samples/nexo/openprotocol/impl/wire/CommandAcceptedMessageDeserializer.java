package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.CommandAcceptedMessage;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;

/**
 * CommandAcceptedMessageDeserializer
 */
public class CommandAcceptedMessageDeserializer extends ROPMessageDeserializer {

    @Override
    public int datalength(int revision) {
        return 4;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof CommandAcceptedMessage))
            throw new IllegalArgumentException("Resulting message type is not CommandAcceptedMessage");

        CommandAcceptedMessage acceptedMsg = (CommandAcceptedMessage)message;
        acceptedMsg.setAcceptedMessageID(Integer.parseInt(str.substring(0, 4)));

        return message;
    }

}