package com.microsoft.samples.nexo.openprotocol.impl;

import java.util.Date;

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
import com.microsoft.samples.nexo.openprotocol.impl.job.OKCounterReplyMessage;
import com.microsoft.samples.nexo.openprotocol.impl.job.OKCounterRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.plc.AckOutSignalChangeMessage;
import com.microsoft.samples.nexo.openprotocol.impl.plc.OutSignalChangeSubRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.plc.OutputSignalChangeMessageRev2;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramChangeSubRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.SelectProgramRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.AbstractLastResultsMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsAcknMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsCancelRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsMessageRev1;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsSubRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsSubStopMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeSetMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ActivateRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.DeactivateRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wifi.WifiLevelMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wifi.WifiLevelRequestMessage;

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

    public WifiLevelRequestMessage createWifiLevelRequestMessage() {

        return new WifiLevelRequestMessage();
    }

    public ShowOnDisplayRequestMessage createShowOnDisplayRequestMessage(String message, int duration) {

        if (message == null)
            throw new IllegalArgumentException("Parameter message must not be null");

        ShowOnDisplayRequestMessage result = new ShowOnDisplayRequestMessage();
        if (duration >= 0)
            result.setDisplayDurationInSeconds(duration);
        else {
            result.setNeedsOKToDismiss(true);
            // This seems to be a bug in the Nexo firmware. Must be at least one second to display something
            result.setDisplayDurationInSeconds(1);
        }
        String[] onlines = message.split("(?<=\\G.{15})");
        int numberoflines = onlines.length > 4 ? 4 : onlines.length;
        for(int i = 0; i < numberoflines; i++)
           result.getLines()[i] = onlines[i];

        return result;
    }

    public ProgramNumbersRequestMessage createProgramNumbersRequestMessage() {
        return new ProgramNumbersRequestMessage();
    }

    public OKCounterRequestMessage createOKCountersRequestMessage() {
        return new OKCounterRequestMessage();
    }

    public TimeRequestMessage createTimeRequestMessage() {
        return new TimeRequestMessage();
    }

    public TimeSetMessage createTimeSetMessage(Date newTime) {
        return new TimeSetMessage(newTime);
    }

    public ToolDataRequestMessage createToolDataRequestMessage() {
        return new ToolDataRequestMessage();
    }

    public LastResultsSubRequestMessage createLastResultsSubRequestMessage() {
        return new LastResultsSubRequestMessage();
    }

    public LastResultsCancelRequestMessage createLastResultsCancelRequestMessage() {
        return new LastResultsCancelRequestMessage();
    }

    public OutSignalChangeSubRequestMessage createOutputSignalChangeSubRequestMessage() {
        return new OutSignalChangeSubRequestMessage();
    }

    public AckOutSignalChangeMessage createAckOutSignalChangeMessage() {
        return new AckOutSignalChangeMessage();
    }

    public LastResultsAcknMessage createLastResultsAcknMessage() {
        return new LastResultsAcknMessage();
    }

    public LastResultsSubStopMessage createLastResultsSubStopMessage() {
        return new LastResultsSubStopMessage();
    }

    public ActivateRequestMessage createActivateRequestMessage() {
        return new ActivateRequestMessage();
    }

    public DeactivateRequestMessage createDeactivateRequestMessage() {
        return new DeactivateRequestMessage();
    }

    public SelectProgramRequestMessage createSelectProgramRequestMessage(int programNumber) {
        return new SelectProgramRequestMessage(programNumber);
    }

    public ProgramChangeSubRequestMessage createProgramChangeSubRequestMessage() {
        return new ProgramChangeSubRequestMessage();
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
            case OKCounterReplyMessage.MESSAGEID:
                result = new OKCounterReplyMessage();
                break;
            case AbstractLastResultsMessage.MESSAGEID:
                result = new LastResultsMessageRev1();
                break;
            case WifiLevelMessage.MESSAGEID:
                result = new WifiLevelMessage();
                break;
            case OutputSignalChangeMessageRev2.MESSAGEID:
                result = new OutputSignalChangeMessageRev2();
                break;
            default:
                break;
        }

        return result;
    }
}