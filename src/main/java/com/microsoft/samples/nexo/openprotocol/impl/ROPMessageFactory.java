package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;
import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev1;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev2;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationStartReplyRev3;

/**
 * ROPMessageFactory
 */
public class ROPMessageFactory {

    public CommunicationStartMessage createStartCommunicationRequest() {

        return new CommunicationStartMessage();
    }

    public BatteryLevelRequestMessage createBatteryLevelRequestMessage() {
        
        return new BatteryLevelRequestMessage();
    }

    public ROPMessage createMessageFor(int msgID, int rev) {

        ROPMessage result = null;

        switch(msgID) {
            case 2:
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
            case 4:
                result = new CommandErrorMessage();
                break;
            case 801:
                result = new BatteryLevelMessage();
                break;
            default:
                break;
        }

        return result;
    }
}