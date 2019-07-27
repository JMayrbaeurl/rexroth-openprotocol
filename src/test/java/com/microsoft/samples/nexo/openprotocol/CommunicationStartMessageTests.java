package com.microsoft.samples.nexo.openprotocol;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageDecoder;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageEncoder;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartMessage;

import org.junit.Test;

import org.junit.Assert;

/**
 * CommunicationStartMessageTests
 */
public class CommunicationStartMessageTests {

    private static final ROPMessageEncoder encoder = new ROPMessageEncoder();
    private static final ROPMessageDecoder decoder = new ROPMessageDecoder();

    @Test
    public void testSimpleToAscii() {

        CommunicationStartMessage msg = new CommunicationStartMessage();
        Assert.assertEquals("00200001001000000000", encoder.encodeMessage(msg));
    }

    @Test
    public void testSimpleFromAscii() {

        CommunicationStartMessage msg = new CommunicationStartMessage();
        msg = (CommunicationStartMessage)decoder.decodeMessage("00200001002000000000", msg);
        Assert.assertEquals(2, msg.getRevision());
    }
}