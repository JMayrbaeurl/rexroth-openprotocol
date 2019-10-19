package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.NexoDevice;
import com.microsoft.samples.nexo.openprotocol.NexoDeviceToolData;

import org.junit.Assert;
import org.junit.Test;

/**
 * ToolTests
 */
public class ToolTests extends AbstractNexoTests {

    @Test
    public void testGetToolData() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        NexoDeviceToolData data = device.getToolData();
        Assert.assertNotNull(data);
    }

    @Test
    public void testActivateTool() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Assert.assertTrue(device.activateTool());
    }

    @Test
    public void testDeactivateTool() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Assert.assertTrue(device.deactivateTool());
    }
}