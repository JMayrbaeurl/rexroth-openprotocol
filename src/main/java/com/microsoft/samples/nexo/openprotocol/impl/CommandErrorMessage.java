package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.Errors;

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

    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public boolean isOK() {
        return false;
    }

    public String errorMessage() {
        return Errors.message(this.errorNumber);
    }

    @Override
    public String toString() {
        return "CommandErrorMessage [causingMessageID=" + causingMessageID + ", errorNumber=" + errorNumber + ": " + this.errorMessage() + "]";
    }
}