package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;

import org.junit.Assert;
import org.junit.Test;

/**
 * ShowOnDisplayMessageSerializerTests
 */
public class ShowOnDisplayMessageSerializerTests {

    @Test
    public void testSimpleSerializing() {

        ShowOnDisplayMessageSerializer serializer = new ShowOnDisplayMessageSerializer();
        ShowOnDisplayRequestMessage msg = new ShowOnDisplayRequestMessage();
        msg.setNeedsOKToDismiss(true);
        msg.getLines()[0] = "Das ist ein Test";
        String str = serializer.toString(msg);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.length() == 137);
    }
}