package com.microsoft.samples.nexo.openprotocol.impl;

/**
 * CommandErrorMessage
 */
public class CommandErrorMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 4;
    public static final int STANDARD_REVISION = 1;

    private int causingMessageID;
    private int errorNumber;

    public CommandErrorMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public int getCausingMessageID() {
        return causingMessageID;
    }

    public void setCausingMessageID(int causingMessageID) {
        this.causingMessageID = causingMessageID;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

}