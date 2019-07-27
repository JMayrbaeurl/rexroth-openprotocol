package com.microsoft.samples.nexo.openprotocol.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.samples.nexo.openprotocol.impl.wire.ROPMessageSerializer;

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

        this.messageSerializers.put(new Integer(1), new ArrayList<>());
        this.messageSerializers.get(new Integer(1)).add(new ROPMessageSerializer());

        this.messageSerializers.put(new Integer(800), new ArrayList<>());
        this.messageSerializers.get(new Integer(800)).add(new ROPMessageSerializer());
    }

    public Map<Integer, List<ROPMessageSerializer>> getMessageSerializers() {
        return messageSerializers;
    }

    public void setMessageSerializers(Map<Integer, List<ROPMessageSerializer>> messageSerializers) {
        this.messageSerializers = messageSerializers;
    }
}