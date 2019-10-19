package com.microsoft.samples.nexo.openprotocol.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationKeepAliveMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStopMessage;
import com.microsoft.samples.nexo.openprotocol.impl.job.OKCounterRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.SelectProgramRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsAcknMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsSubRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsSubStopMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeSetMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ActivateRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.DeactivateRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wifi.WifiLevelRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ROPMessageSerializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.SelectProgramMessageSerializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.ShowOnDisplayMessageSerializer;
import com.microsoft.samples.nexo.openprotocol.impl.wire.TimeSetMessageSerializer;

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
        Integer msgID = Integer.valueOf(message.messageID());

        if (this.messageSerializers.containsKey(msgID)) {

            List<ROPMessageSerializer> serializers = this.messageSerializers.get(msgID);
            if (serializers.size() >= message.revision())
                result = serializers.get(message.revision()-1).toString(message);
        }

        return result;
    }

    private void addStandardSerializers() {

        this.messageSerializers.put(Integer.valueOf(CommunicationStartMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(CommunicationStartMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(CommunicationStopMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(CommunicationStopMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(CommunicationKeepAliveMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(CommunicationKeepAliveMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(BatteryLevelRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(BatteryLevelRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(WifiLevelRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(WifiLevelRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(ShowOnDisplayRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(ShowOnDisplayRequestMessage.MESSAGEID)).add(new ShowOnDisplayMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(ProgramNumbersRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(ProgramNumbersRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(SelectProgramRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(SelectProgramRequestMessage.MESSAGEID)).add(new SelectProgramMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(TimeRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(TimeRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(TimeSetMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(TimeSetMessage.MESSAGEID)).add(new TimeSetMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(ToolDataRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(ToolDataRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(ActivateRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(ActivateRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(DeactivateRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(DeactivateRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(OKCounterRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(OKCounterRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());

        this.messageSerializers.put(Integer.valueOf(LastResultsSubRequestMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(LastResultsSubRequestMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(LastResultsAcknMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(LastResultsAcknMessage.MESSAGEID)).add(new ROPMessageSerializer());
        this.messageSerializers.put(Integer.valueOf(LastResultsSubStopMessage.MESSAGEID), new ArrayList<>());
        this.messageSerializers.get(Integer.valueOf(LastResultsSubStopMessage.MESSAGEID)).add(new ROPMessageSerializer());
    }

    public Map<Integer, List<ROPMessageSerializer>> getMessageSerializers() {
        return messageSerializers;
    }

    public void setMessageSerializers(Map<Integer, List<ROPMessageSerializer>> messageSerializers) {
        this.messageSerializers = messageSerializers;
    }
}