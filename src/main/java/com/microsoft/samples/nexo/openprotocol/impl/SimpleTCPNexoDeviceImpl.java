package com.microsoft.samples.nexo.openprotocol.impl;

import java.io.IOException;
import java.net.UnknownHostException;

import com.microsoft.samples.nexo.openprotocol.NexoCommException;
import com.microsoft.samples.nexo.openprotocol.NexoDevice;
import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleTCPNexoDeviceImpl
 */
public class SimpleTCPNexoDeviceImpl implements NexoDevice {

    private static final Logger log = LoggerFactory.getLogger(SimpleTCPNexoDeviceImpl.class);

    private RexrothOpenProtocolAdapter protocolAdapter;

    private ROPMessageFactory messageFactory;

    @Override
    public int getBatteryLevel() throws NexoCommException {
        
        int result = -1;

        ROPRequestMessage request = this.messageFactory.createBatteryLevelRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof BatteryLevelMessage) {
                    result = ((BatteryLevelMessage)reply).getBatteryLevel();
                } else
                   throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
            }

        } catch (UnknownHostException e) {
            log.error("Exception on getting battery level from Nexo device", e);
            throw new NexoCommException("Exception on getting battery level from Nexo device", e);
        } catch (IOException e) {
            log.error("Exception on getting battery level from Nexo device", e);
            throw new NexoCommException("Exception on getting battery level from Nexo device", e);
        }

        return result;
    }

    @Override
    public boolean startCommunication() throws NexoCommException {

        boolean result = false;

        log.debug("Now send start communication command to Nexo device");

        ROPRequestMessage request = this.messageFactory.createStartCommunicationRequest();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            result = reply != null;
            
        } catch (UnknownHostException e) {
            log.error("Can not start communication with NexoDevice", e);
            throw new NexoCommException("Could not start communication with Nexo device", e);
        } catch (IOException e) {
            log.error("Can not start communication with NexoDevice", e);
            throw new NexoCommException("Could not start communication with Nexo device", e);
        }
        
        if(log.isInfoEnabled()) {
            if (result)
                log.info("Successfully starte communication with Nexo device");
            else
                log.info("Could not start communication with Nexo device");
        }

        return result;
    }

    public RexrothOpenProtocolAdapter getProtocolAdapter() {
        return protocolAdapter;
    }

    public void setProtocolAdapter(RexrothOpenProtocolAdapter protocolAdapter) {
        this.protocolAdapter = protocolAdapter;
    }

    public ROPMessageFactory getMessageFactory() {
        return messageFactory;
    }

    public void setMessageFactory(ROPMessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

}