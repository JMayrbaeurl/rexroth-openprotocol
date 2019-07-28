package com.microsoft.samples.nexo.openprotocol.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReply;
import com.microsoft.samples.nexo.openprotocol.impl.wire.BatteryLevelMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.CommandAcceptedMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.CommandAckStartDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.CommandErrorMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ROPMessageDeserializer;

/**
 * ROPMessageDecoder
 */
public class ROPMessageDecoder {

    private Map<Integer, List<ROPMessageDeserializer>> messageDeserializers = new HashMap<Integer, List<ROPMessageDeserializer>>();

    public ROPMessageDecoder() {
        this.addStandardDeserializers();
    }

    public ROPMessage decodeMessage(String fromString, ROPMessage message) {

        ROPMessage result = null;

        if(fromString == null || fromString.length() == 0)
            throw new IllegalArgumentException("Parameter fromString must not be null");

            Integer msgID = new Integer(message.messageID());

            if (this.messageDeserializers.containsKey(msgID)) {
    
                List<ROPMessageDeserializer> deSerializers = this.messageDeserializers.get(msgID);
                if (deSerializers.size() >= message.revision())
                    result = deSerializers.get(message.revision()-1).fromString(fromString, message);
            }

        return result;
    }

    public int readMessageIDfromReplyString(String replyString) {

        return ROPMessageDeserializer.readMessageIDfromReplyString(replyString);
    }

    public int readRevisionfromReplyString(String replyString) {

        return ROPMessageDeserializer.readRevisionfromReplyString(replyString);
    }

    private void addStandardDeserializers() {
        
        this.messageDeserializers.put(new Integer(CommunicationStartReply.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(CommunicationStartReply.MESSAGEID)).add(new CommandAckStartDeserializer());
        this.messageDeserializers.get(new Integer(CommunicationStartReply.MESSAGEID)).add(new CommandAckStartDeserializer());
        this.messageDeserializers.get(new Integer(CommunicationStartReply.MESSAGEID)).add(new CommandAckStartDeserializer());

        this.messageDeserializers.put(new Integer(CommandErrorMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(CommandErrorMessage.MESSAGEID)).add(new CommandErrorMessageDeserializer());

        this.messageDeserializers.put(new Integer(CommandAcceptedMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(CommandAcceptedMessage.MESSAGEID)).add(new CommandAcceptedMessageDeserializer());

        this.messageDeserializers.put(new Integer(BatteryLevelMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(BatteryLevelMessage.MESSAGEID)).add(new BatteryLevelMessageDeserializer());

    }
}