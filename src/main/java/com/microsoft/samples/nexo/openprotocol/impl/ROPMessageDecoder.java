package com.microsoft.samples.nexo.openprotocol.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationKeepAliveReply;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReply;
import com.microsoft.samples.nexo.openprotocol.impl.job.OKCounterReplyMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.AbstractLastResultsMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wire.BatteryLevelMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.CommandAcceptedMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.CommandAckStartDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.CommandErrorMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.LastResultsMessageRev1Deserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.OKCounterReplyMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ProgramNumbersMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ROPMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.TimeMessageDeserializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ToolDataMessageDeserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ROPMessageDecoder
 */
public class ROPMessageDecoder {

    private static final Logger log = LoggerFactory.getLogger(ROPMessageDecoder.class);

    private Map<Integer, List<ROPMessageDeserializer>> messageDeserializers = new HashMap<Integer, List<ROPMessageDeserializer>>();

    public ROPMessageDecoder() {
        this.addStandardDeserializers();
    }

    public ROPMessage decodeMessage(String fromString, ROPMessage message) {

        ROPMessage result = null;

        if(fromString == null || fromString.length() == 0)
            throw new IllegalArgumentException("Parameter fromString must not be null");

        if (message != null) {
            Integer msgID = new Integer(message.messageID());

            if (this.messageDeserializers.containsKey(msgID)) {
    
                List<ROPMessageDeserializer> deSerializers = this.messageDeserializers.get(msgID);
                if (deSerializers.size() >= message.revision())
                    result = deSerializers.get(message.revision()-1).fromString(fromString, message);
            }
        } else
            log.warn("decodeMessage from '" + fromString + "' called for null message object. Check message factory setup");

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

        this.messageDeserializers.put(new Integer(CommunicationKeepAliveReply.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(CommunicationKeepAliveReply.MESSAGEID)).add(new ROPMessageDeserializer());

        this.messageDeserializers.put(new Integer(CommandErrorMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(CommandErrorMessage.MESSAGEID)).add(new CommandErrorMessageDeserializer());

        this.messageDeserializers.put(new Integer(CommandAcceptedMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(CommandAcceptedMessage.MESSAGEID)).add(new CommandAcceptedMessageDeserializer());

        this.messageDeserializers.put(new Integer(BatteryLevelMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(BatteryLevelMessage.MESSAGEID)).add(new BatteryLevelMessageDeserializer());

        this.messageDeserializers.put(new Integer(ProgramNumbersMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(ProgramNumbersMessage.MESSAGEID)).add(new ProgramNumbersMessageDeserializer());

        this.messageDeserializers.put(new Integer(TimeMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(TimeMessage.MESSAGEID)).add(new TimeMessageDeserializer());

        this.messageDeserializers.put(new Integer(ToolDataMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(ToolDataMessage.MESSAGEID)).add(new ToolDataMessageDeserializer());

        this.messageDeserializers.put(new Integer(OKCounterReplyMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(OKCounterReplyMessage.MESSAGEID)).add(new OKCounterReplyMessageDeserializer());

        this.messageDeserializers.put(new Integer(AbstractLastResultsMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(new Integer(AbstractLastResultsMessage.MESSAGEID)).add(new LastResultsMessageRev1Deserializer());
    }
}