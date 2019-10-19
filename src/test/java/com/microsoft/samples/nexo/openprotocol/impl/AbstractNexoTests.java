package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.NexoDevice;
import com.microsoft.samples.nexo.openprotocol.NexoDeviceClientFactory;

/**
 * AbstractNexoTests
 */
public abstract class AbstractNexoTests {

    protected NexoDevice createDeviceClient() {
        
        return NexoDeviceClientFactory.createDefaultNexoDeviceClient("192.168.1.22", 4545);
    }
}