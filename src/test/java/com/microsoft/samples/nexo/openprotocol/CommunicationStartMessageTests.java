package com.microsoft.samples.nexo.openprotocol;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageDecoder;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessageEncoder;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReply;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev1;

import org.junit.Assert;
import org.junit.Test;

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

        CommunicationStartReply msg = new CommunicationStartReplyRev1();
        msg = (CommunicationStartReply)decoder.decodeMessage("002000020010000000000000000000000000000000000000000000000", msg);
        Assert.assertEquals(1, msg.getRevision());
    }
}