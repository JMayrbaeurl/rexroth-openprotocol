package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.NexoDevice;

import org.junit.Assert;
import org.junit.Test;

/**
 * ProgramTests
 */
public class ProgramTests extends AbstractNexoTests {

    @Test
    public void testReadProgramnumbers() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int[] numbers = device.getTighteningprogramNumbers();
        Assert.assertTrue(numbers.length > 0);
    }
    
    @Test
    public void testSelectLastProgram() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int[] numbers = device.getTighteningprogramNumbers();
        Assert.assertTrue(numbers.length > 0);
        Assert.assertTrue(device.selectTighteningProgram(numbers[numbers.length-1]));
    }

    @Test
    public void testSelectFirstProgram() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int[] numbers = device.getTighteningprogramNumbers();
        Assert.assertTrue(numbers.length > 0);
        Assert.assertTrue(device.selectTighteningProgram(numbers[0]));
    }

    @Test
    public void testSelectAllPrograms() {
        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());
        int[] numbers = device.getTighteningprogramNumbers();
        Assert.assertTrue(numbers.length > 0);

        boolean allOK = true;
        for(int i = 0; i < numbers.length; i++) {
            boolean wasSelected = device.selectTighteningProgram(i);
            if (!wasSelected) allOK = false;
        }
        Assert.assertTrue(allOK);
    }

    @Test
    public void testReconnect() throws InterruptedException {

        NexoDevice device = this.createDeviceClient();
        Assert.assertTrue(device.startCommunication());

        Thread.sleep(1000*30);

        int[] numbers = device.getTighteningprogramNumbers();
        Assert.assertTrue(numbers.length > 0);
    }
}