package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.microsoft.samples.nexo.openprotocol.NexoDeviceToolData;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataMessage;

/**
 * ToolDataMessageDeserializer
 */
public class ToolDataMessageDeserializer extends ROPMessageDeserializer {

    @Override
    public int datalength(int revision) {
        return 101;
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {
        
        if (str == null | str.length() == 0)
        throw new IllegalArgumentException("Parameter str must not be null");

    if (message == null | !(message instanceof ToolDataMessage))
        throw new IllegalArgumentException("Resulting message type is not Tool data");

    if (message instanceof ToolDataMessage) {
        ToolDataMessage reply = (ToolDataMessage)message;
        NexoDeviceToolData data = new NexoDeviceToolData();
        data.setToolNumber(str.substring(2, 16).trim());
        data.setCycles(Integer.parseInt(str.substring(18, 28)));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy:hh:mm:ss a");
        try {
            data.setLastMaintenanceDate(format.parse(str.substring(30, 49)));
        } catch (ParseException e) {}
        data.setSerialNumber(str.substring(51, 61));
        
        reply.setToolData(data);
    }

    return message;
    }
}