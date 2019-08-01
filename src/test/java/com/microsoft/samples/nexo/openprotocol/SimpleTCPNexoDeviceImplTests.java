package com.microsoft.samples.nexo.openprotocol;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * SimpleTCPNexoDeviceImplTests
 */
public class SimpleTCPNexoDeviceImplTests {

    @Test
    public void testStartCommunication() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
    }

    @Test
    public void testStartCommunicationMultipleTimes() {

        NexoDevice device = this.createDeviceClient();

        for(int i = 0; i < 3; i++)
            Assert.assertTrue(device.startCommunication());
    }

    @Test
    public void testGetBatteryLevel() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int level = device.getBatteryLevel();
        Assert.assertTrue(level > 0 && level <= 100);
    }

    @Test
    public void testShowOnDisplay() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Assert.assertTrue(device.showOnDisplay("Das ist ein Test"));
    }

    @Test
    public void testReadProgramnumbers() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int[] numbers = device.getTighteningprogramNumbers();
        Assert.assertTrue(numbers.length > 0);
    }
    
    @Test
    public void testGetTime() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Date currTime = device.getTime();
        Assert.assertNotNull(currTime);
    }

    @Test
    public void testGetToolData() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        NexoDeviceToolData data = device.getToolData();
        Assert.assertNotNull(data);
    }

    private NexoDevice createDeviceClient() {
        
        return NexoDeviceClientFactory.createDefaultNexoDeviceClient("192.168.1.22", 4545);
    }
}