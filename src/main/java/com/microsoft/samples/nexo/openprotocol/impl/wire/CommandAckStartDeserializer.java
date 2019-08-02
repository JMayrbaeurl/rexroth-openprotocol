package com.microsoft.samples.nexo.openprotocol.impl.wire;

import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReply;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev1;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev2;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev3;

/**
 * CommandAckStartDeserializer
 */
public class CommandAckStartDeserializer extends ROPMessageDeserializer {

    public static final int DATALENGTH_REV1 = 37;
    public static final int DATALENGTH_REV2 = 42;
    public static final int DATALENGTH_REV3 = 105;

    @Override
    public int datalength(int revision) {

        if (revision == 1)
            return DATALENGTH_REV1;
        else if (revision == 2)
            return DATALENGTH_REV2;
        else if (revision == 3)
            return DATALENGTH_REV3;
        else
            return super.datalength(revision);
    }

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {

        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof CommunicationStartReply))
            throw new IllegalArgumentException("Resulting message type is not Communication Start Acknowledge");

        if (message instanceof CommunicationStartReplyRev1) {
            CommunicationStartReplyRev1 reply = (CommunicationStartReplyRev1)message;
            reply.setCellID(Integer.parseInt(str.substring(2, 6)));
            reply.setChannelID(Integer.parseInt(str.substring(8, 10)));
            reply.setControllerName(str.substring(12, 37));
        } else if (message instanceof CommunicationStartReplyRev2) {
            //CommunicationStartReplyRev2 reply = (CommunicationStartReplyRev2)message;
            throw new IllegalStateException("Revision 2 of Communication Start Acknowledge commands not supported yet");
        } else if (message instanceof CommunicationStartReplyRev3) {
            //CommunicationStartReplyRev3 reply = (CommunicationStartReplyRev3)message;
            throw new IllegalStateException("Revision 3 of Communication Start Acknowledge commands not supported yet");
        }
        

        return message;
    }
}