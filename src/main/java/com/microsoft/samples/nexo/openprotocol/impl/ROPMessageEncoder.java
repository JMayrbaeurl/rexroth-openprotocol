package com.microsoft.samples.nexo.openprotocol.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ROPMessageSerializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ShowOnDisplayMessageSerializer;

/**
 * ROPMessageEncoder
 */
public class ROPMessageEncoder {

    private Map<Integer, List<ROPMessageSerializer>> messageSerializers = new HashMap<Integer, List<ROPMessageSerializer>>();

    public ROPMessageEncoder() {
        super();

        this.addStandardSerializers();
    }

    public String encodeMessage(ROPMessage message) {

        String result = "";
        Integer msgID = new Integer(message.messageID());

        if (this.messageSerializers.containsKey(msgID)) {

            List<ROPMessageSerializer> serializers = this.messageSerializers.get(msgID);
            if (serializers.size() >= message.revision())
                result = serializers.get(message.revision()-1).toString(message);
        }

        return result;
    }

    private void addStandardSerializers() {

        this.messageSerializers.put(new Integer(CommunicationStartMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(new Integer(CommunicationStartMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(new Integer(BatteryLevelRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(new Integer(BatteryLevelRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(new Integer(ShowOnDisplayRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(new Integer(ShowOnDisplayRequestMessage.MESSAGEID)).add(new ShowOnDisplayMessageSerializer());

        this.messageSerializers.put(new Integer(ProgramNumbersRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(new Integer(ProgramNumbersRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(new Integer(TimeRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(new Integer(TimeRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(new Integer(ToolDataRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(new Integer(ToolDataRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
    }

    public Map<Integer, List<ROPMessageSerializer>> getMessageSerializers() {
        return messageSerializers;
    }

    public void setMessageSerializers(Map<Integer, List<ROPMessageSerializer>> messageSerializers) {
        this.messageSerializers = messageSerializers;
    }
}