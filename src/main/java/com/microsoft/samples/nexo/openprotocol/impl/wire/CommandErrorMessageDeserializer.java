package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.CommandErrorMessage;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;

/**
 * CommandErrorMessageSerializer
 */
public class CommandErrorMessageDeserializer extends ROPMessageDeserializer {

    @Override
    public int datalength(int revision) {
        return 6;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {

        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof CommandErrorMessage))
            throw new IllegalArgumentException("Resulting message type is not CommandError");

        CommandErrorMessage result = (CommandErrorMessage)message;
        result.setCausingMessageID(Integer.parseInt(str.substring(0, 4)));
        result.setErrorNumber(Integer.parseInt(str.substring(4, 6)));

        return result;
    }
}