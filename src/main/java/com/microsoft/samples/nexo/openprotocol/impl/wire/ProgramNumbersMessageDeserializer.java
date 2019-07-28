package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersMessage;

/**
 * ProgramNumbersMessageDeserializer
 */
public class ProgramNumbersMessageDeserializer extends ROPMessageDeserializer {

    @Override
    public int datalength(int revision) {
        return 0;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {

        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof ProgramNumbersMessage))
            throw new IllegalArgumentException("Resulting message type is not Battery Level");

        if (message instanceof ProgramNumbersMessage) {
            ProgramNumbersMessage reply = (ProgramNumbersMessage)message;
            
            int number = Integer.parseInt(str.substring(0, 3));
            if (number > 0) {
                for (int i = 0; i < number; i++) {
                    reply.getProgramNumbers().add(new Integer(Integer.parseInt(str.substring(3+i*3, 6+i*3))));
                }
            }

        }

        return message;
    }

    
}