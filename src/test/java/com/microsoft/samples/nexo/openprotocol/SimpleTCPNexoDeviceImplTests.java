package com.microsoft.samples.nexo.openprotocol;

import java.util.Date;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractNexoTests;

import org.junit.Assert;
import org.junit.Test;

/**
 * SimpleTCPNexoDeviceImplTests
 */
public class SimpleTCPNexoDeviceImplTests extends AbstractNexoTests {

    @Test
    public void testStartCommunication() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        device.stopCommunication();
    }

    @Test
    public void testMultipleCommunications() {

        NexoDevice device1 = this.createDeviceClient();
        NexoDevice device2 = this.createDeviceClient();

        Assert.assertTrue(device1.startCommunication());
        device1.stopCommunication();
        Assert.assertTrue(device2.startCommunication());
        device2.stopCommunication();
    }

    @Test
    public void testStartStopStartSequence() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        device.stopCommunication();
        Assert.assertTrue(device.startCommunication());
    }

    @Test
    public void testStartCommunicationMultipleTimes() {

        NexoDevice device = this.createDeviceClient();

        for(int i = 0; i < 3; i++) 
            Assert.assertTrue(device.startCommunication());
    }

    @Test
    public void testKeepAlive() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());

        device.sendKeepAlive();
        device.stopCommunication();
    }

    @Test
    public void testGetBatteryLevel() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int level = device.getBatteryLevel();
        Assert.assertTrue(level > 0 && level <= 100);
    }

    @Test
    public void testGetBatteryLevelMultipleTimes() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());

        for(int i = 0; i < 3; i++) {
            int level = device.getBatteryLevel();
            Assert.assertTrue(level > 0 && level <= 100);
        }

        device.stopCommunication();
    }

    @Test
    public void testShowOnDisplay() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Assert.assertTrue(device.showOnDisplay("Das ist ein Test"));
    }

    @Test
    public void testShowOnDisplay10Seconds() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Assert.assertTrue(device.showOnDisplay("Das ist ein Test", 10));
    }

    @Test
    public void testGetTime() {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Date currTime = device.getTime();
        Assert.assertNotNull(currTime);
    }

    @Test 
    public void testSetCurrentTime() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        device.setTime(new Date());
    }

    @Test
    public void testOKCounters() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int[] counters = device.getOKCounters();
        Assert.assertNotNull(counters);
    }

    @Test
    public void testSendStartCommandAsString() {
        NexoDevice device = this.createDeviceClient();
        OpenProtocolCommands rawCommands = (OpenProtocolCommands)device;
        String result = rawCommands.sendROPCommand("00200001001000000000");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.startsWith("00570002001000000000010000020103"));
    }

    @Test
    public void testGetWIFILevel() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int level = device.getWIFILevel();
        Assert.assertTrue(level != -1);
    }

    @Test
    public void testGetLockState() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        Assert.assertTrue(device.subscribeToTighteningResults());
        PLCOutputSignalChange signals = device.subscribeToOutputSignalChange();
        Assert.assertNotNull(signals);
        Assert.assertTrue(signals.numberOfSignals() == 16);
        //Assert.assertTrue(signals.isSignalSet(3));
    }

    @Test
    public void testProgramChangeSubscription() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        device.subscribeToProgramChange();
    }
}