package com.microsoft.samples.nexo.openprotocol;

/**
 * PLCOutputSignalChange
 */
public final class PLCOutputSignalChange {

    private String signalsState;

    public PLCOutputSignalChange() {
    }

    public String getSignalsState() {
        return signalsState;
    }

    public void setSignalsState(String signalsState) {
        this.signalsState = signalsState;
    }

    @Override
    public String toString() {
        return "PLCOutputSignalChange [signalsState=" + signalsState + "]";
    }

    public PLCOutputSignalChange(String signalsState) {
        this.signalsState = signalsState;
    }

    public int numberOfSignals() {

        return this.signalsState != null ? this.signalsState.length() : 0;
    }

    public boolean isSignalSet(int signalNum) {

        if (signalNum < 0)
            throw new IllegalArgumentException("Parameter 'signalNum' must be greater or equal to zero");

        if (this.signalsState == null || signalNum >= this.signalsState.length())
            throw new IllegalArgumentException("Parameter 'signalNum' not in range of available signals");

        return this.signalsState.charAt(signalNum) == '1';
    }
}