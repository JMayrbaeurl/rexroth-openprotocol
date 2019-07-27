package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;

/**
 * ROPMessageDeserializer
 */
public class ROPMessageDeserializer {

    public static final int HEADER_LENGTH = 20;

    public final ROPMessage fromString(String msg, ROPMessage message) {

        if (msg == null || msg.length() == 0)
            throw new IllegalArgumentException("Cant read from empty string");

        if (msg.length() < ROPMessageDeserializer.HEADER_LENGTH)
            throw new IllegalArgumentException(
                    "Minimum string length of " + ROPMessageDeserializer.HEADER_LENGTH + " not available");

        int readMsgID = ROPMessageDeserializer.readMessageIDfromReplyString(msg);
        if (readMsgID != message.messageID())
            throw new IllegalArgumentException("Unequal message ids. Was " + message.messageID() + " and read " + readMsgID);

        int revision = ROPMessageDeserializer.readRevisionfromReplyString(msg);

        if(!message.isRevisionSupported(revision))
            throw new IllegalArgumentException("Revision " + revision + " currently not supported");

        if (msg.length() < this.messagelength(revision))
            throw new IllegalArgumentException("Minimum string length of " + this.messagelength(revision) + " not available");

        String dataString = ROPMessageDeserializer.readDatafromReplyString(msg);
        message = this.doFromString(dataString, message);

        return message;
    }

    protected ROPMessage doFromString(String str, ROPMessage message) {
        return message;
    }

    public static int readMessageIDfromReplyString(String replyString) {

        if (replyString == null || replyString.length() == 0)
            throw new IllegalArgumentException("Cant read from empty string");

        if (replyString.length() < ROPMessageDeserializer.HEADER_LENGTH)
            throw new IllegalArgumentException(
                    "Minimum string length of " + ROPMessageDeserializer.HEADER_LENGTH + " not available");

        String msgIDString = replyString.substring(4, 8);
        int readMsgID = Integer.parseInt(msgIDString);

        return readMsgID;
    }

    public static int readRevisionfromReplyString(String replyString) {

        if (replyString == null || replyString.length() == 0)
            throw new IllegalArgumentException("Cant read from empty string");

        if (replyString.length() < ROPMessageDeserializer.HEADER_LENGTH)
            throw new IllegalArgumentException(
                "Minimum string length of " + ROPMessageDeserializer.HEADER_LENGTH + " not available");

        String revString = replyString.substring(8, 11);
        return Integer.parseInt(revString);
    }

    public static String readDatafromReplyString(String replyString) {

        if (replyString == null || replyString.length() == 0)
            throw new IllegalArgumentException("Cant read from empty string");

        if(replyString.length() > ROPMessageDeserializer.HEADER_LENGTH) 
            return replyString.substring(ROPMessageDeserializer.HEADER_LENGTH);
        else
            return "";
    }

    public int messagelength(int revision) {
        return HEADER_LENGTH + this.datalength(revision);
    }

    public int datalength(int revision) {
        return 0;
    }
}