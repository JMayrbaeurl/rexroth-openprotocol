package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;
import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationKeepAliveMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationKeepAliveReply;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReply;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev1;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev2;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev3;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStopMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;

/**
 * ROPMessageFactory
 */
public class ROPMessageFactory {

    public CommunicationStartMessage createStartCommunicationRequest() {

        return new CommunicationStartMessage();
    }

    public CommunicationStopMessage createStopCommunicationRequest() {

        return new CommunicationStopMessage();
    }

    public CommunicationKeepAliveMessage createCommunicationKeepAliveMessage() {

        return new CommunicationKeepAliveMessage();
    }

    public BatteryLevelRequestMessage createBatteryLevelRequestMessage() {
        
        return new BatteryLevelRequestMessage();
    }

    public ShowOnDisplayRequestMessage createShowOnDisplayRequestMessage(String message) {

        if (message == null)
            throw new IllegalArgumentException("Parameter message must not be null");

        ShowOnDisplayRequestMessage result = new ShowOnDisplayRequestMessage();
        result.setNeedsOKToDismiss(true);
        String[] onlines = message.split("(?<=\\G.{15})");
        int numberoflines = onlines.length > 4 ? 4 : onlines.length;
        for(int i = 0; i < numberoflines; i++)
           result.getLines()[i] = onlines[i];

        return result;
    }

    public ProgramNumbersRequestMessage createProgramNumbersRequestMessage() {
        return new ProgramNumbersRequestMessage();
    }

    public TimeRequestMessage createTimeRequestMessage() {
        return new TimeRequestMessage();
    }

    public ToolDataRequestMessage createToolDataRequestMessage() {
        return new ToolDataRequestMessage();
    }

    public ROPMessage createMessageFor(int msgID, int rev) {

        ROPMessage result = null;

        switch(msgID) {
            case CommunicationStartReply.MESSAGEID:
                {
                    switch (rev) {
                        case 1:
                            result  = new CommunicationStartReplyRev1();
                            break;
                        case 2:
                            result = new CommunicationStartReplyRev2();
                            break;
                        case 3:
                            result = new CommunicationStartReplyRev3();
                            break;
                        default:
                            break;
                    }
                }
                break;
            case CommunicationKeepAliveMessage.MESSAGEID:
                result = new CommunicationKeepAliveReply();
                break;
            case CommandErrorMessage.MESSAGEID:
                result = new CommandErrorMessage();
                break;
            case CommandAcceptedMessage.MESSAGEID:
                result = new CommandAcceptedMessage();
                break;
            case BatteryLevelMessage.MESSAGEID:
                result = new BatteryLevelMessage();
                break;
            case ProgramNumbersMessage.MESSAGEID:
                result = new ProgramNumbersMessage();
                break;
            case TimeMessage.MESSAGEID:
                result = new TimeMessage();
                break;
            case ToolDataMessage.MESSAGEID:
                result = new ToolDataMessage();
                break;
            default:
                break;
        }

        return result;
    }
}