package com.microsoft.samples.nexo.openprotocol;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageDecoder;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageEncoder;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageFactory;
import com.microsoft.samples.nexo.openprotocol.impl.RexrothOpenProtocolAdapter;
import com.microsoft.samples.nexo.openprotocol.impl.SimpleTCPNexoDeviceImpl;

/**
 * NexoDeviceClientFactory
 */
public class NexoDeviceClientFactory {

    public static NexoDevice createDefaultNexoDeviceClient(String ipAddress, int port) {

        NexoDeviceClientFactory factory = new NexoDeviceClientFactory();
        return factory.createNexoDeviceClient(ipAddress, port);
    }

    public NexoDevice createNexoDeviceClient(String ipAddress, int port) {

        SimpleTCPNexoDeviceImpl result = new SimpleTCPNexoDeviceImpl();
        ROPMessageFactory messageFactory = new ROPMessageFactory();
        RexrothOpenProtocolAdapter adaptor = new RexrothOpenProtocolAdapter();
        adaptor.setMessageFactory(messageFactory);
        adaptor.setIp(ipAddress); adaptor.setPort(port);
        adaptor.setMessageEncoder(new ROPMessageEncoder());
        adaptor.setMessageDecoder(new ROPMessageDecoder());
        result.setProtocolAdapter(adaptor);
        result.setMessageFactory(messageFactory);

        return result;
    }

    public NexoDevice createNexoDeviceClient(String ipAddress) {
        
        return this.createNexoDeviceClient(ipAddress, NexoDevice.DEFAULT_PORT);
    }
}