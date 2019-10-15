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
import com.microsoft.samples.nexo.openprotocol.impl.wifi.WifiLevelMessage;
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
import com.microsoft.samples.nexo.openprotocol.impl.wire.WifiLevelMessageDeserializer;

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
            Integer msgID = Integer.valueOf(message.messageID());

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
        
        this.messageDeserializers.put(Integer.valueOf(CommunicationStartReply.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(CommunicationStartReply.MESSAGEID)).add(new CommandAckStartDeserializer());
        this.messageDeserializers.get(Integer.valueOf(CommunicationStartReply.MESSAGEID)).add(new CommandAckStartDeserializer());
        this.messageDeserializers.get(Integer.valueOf(CommunicationStartReply.MESSAGEID)).add(new CommandAckStartDeserializer());

        this.messageDeserializers.put(Integer.valueOf(CommunicationKeepAliveReply.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(CommunicationKeepAliveReply.MESSAGEID)).add(new ROPMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(CommandErrorMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(CommandErrorMessage.MESSAGEID)).add(new CommandErrorMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(CommandAcceptedMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(CommandAcceptedMessage.MESSAGEID)).add(new CommandAcceptedMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(BatteryLevelMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(BatteryLevelMessage.MESSAGEID)).add(new BatteryLevelMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(WifiLevelMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(WifiLevelMessage.MESSAGEID)).add(new WifiLevelMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(ProgramNumbersMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(ProgramNumbersMessage.MESSAGEID)).add(new ProgramNumbersMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(TimeMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(TimeMessage.MESSAGEID)).add(new TimeMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(ToolDataMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(ToolDataMessage.MESSAGEID)).add(new ToolDataMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(OKCounterReplyMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(OKCounterReplyMessage.MESSAGEID)).add(new OKCounterReplyMessageDeserializer());

        this.messageDeserializers.put(Integer.valueOf(AbstractLastResultsMessage.MESSAGEID), new ArrayList<ROPMessageDeserializer>());
        this.messageDeserializers.get(Integer.valueOf(AbstractLastResultsMessage.MESSAGEID)).add(new LastResultsMessageRev1Deserializer());
    }
}