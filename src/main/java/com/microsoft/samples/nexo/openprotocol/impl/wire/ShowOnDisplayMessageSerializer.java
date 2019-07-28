package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.util.Formatter;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;

/**
 * ShowOnDisplayMessageSerializer
 */
public class ShowOnDisplayMessageSerializer extends ROPMessageSerializer {

    @Override
    public String toString(ROPMessage message) {

        if (message == null)
            throw new IllegalArgumentException("Parameter 'message' must not be null");
        if (!(message instanceof ShowOnDisplayRequestMessage))
        throw new IllegalArgumentException("Parameter 'message' has to be of type ShowOnDisplayRequestMessage");

        ShowOnDisplayRequestMessage requestMessage = (ShowOnDisplayRequestMessage)message;
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("%s01%04d02%01d03%-25s04%-25s05%-25s06%-25s", super.toString(message), 
            requestMessage.getDisplayDurationInSeconds(), requestMessage.isNeedsOKToDismiss() ? 1:0, 
            requestMessage.getLines().length > 0 && requestMessage.getLines()[0] != null ? requestMessage.getLines()[0] : "",
            requestMessage.getLines().length > 1 && requestMessage.getLines()[1] != null ? requestMessage.getLines()[1] : "",
            requestMessage.getLines().length > 2 && requestMessage.getLines()[2] != null ? requestMessage.getLines()[2] : "",
            requestMessage.getLines().length > 3 && requestMessage.getLines()[3] != null ? requestMessage.getLines()[3] : "");
        fmt.close();

        return String.format("%04d", sbuf.length()) + sbuf.toString().substring(4);
    }

    
}