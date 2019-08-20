package com.microsoft.samples.nexo.openprotocol;

import java.util.Date;

/**
 * TighteningResults
 */
public class TighteningResults {

    public enum TighteningStatus { NOK, OK };

    public enum TorqueStatus { Low, OK, High };

    public enum AngleStatus { Low, OK, High };

    public enum OKNOKCounterStatus { Other, ChXYCntOK, NotUsed}

    private int cellID;

    private int channelID;

    private String controllerName;

    private String idCode;

    private int jobNumber;

    private int programNumber;

    private int okCounterLimit;

    private int okCounterValue;

    private TighteningResults.TighteningStatus tighteningStatus;

    private TighteningResults.TorqueStatus torqueStatus;

    private TighteningResults.AngleStatus angleStatus;

    private double minTorqueLimit;

    private double maxTorqueLimit;

    private double targetTorque;

    private double torque;

    private int minAngle;

    private int maxAngle;

    private int targetAngle;

    private int angle;

    private Date tighteningTime;

    private Date lastChangedTime;

    private TighteningResults.OKNOKCounterStatus counterStatus;

    private long tighteningID;

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public int getProgramNumber() {
        return programNumber;
    }

    public void setProgramNumber(int programNumber) {
        this.programNumber = programNumber;
    }

    public int getOkCounterLimit() {
        return okCounterLimit;
    }

    public void setOkCounterLimit(int okCounterLimit) {
        this.okCounterLimit = okCounterLimit;
    }

    public int getOkCounterValue() {
        return okCounterValue;
    }

    public void setOkCounterValue(int okCounterValue) {
        this.okCounterValue = okCounterValue;
    }

    public TighteningResults.TighteningStatus getTighteningStatus() {
        return tighteningStatus;
    }

    public void setTighteningStatus(TighteningResults.TighteningStatus tighteningStatus) {
        this.tighteningStatus = tighteningStatus;
    }

    public TighteningResults.TorqueStatus getTorqueStatus() {
        return torqueStatus;
    }

    public void setTorqueStatus(TighteningResults.TorqueStatus torqueStatus) {
        this.torqueStatus = torqueStatus;
    }

    public TighteningResults.AngleStatus getAngleStatus() {
        return angleStatus;
    }

    public void setAngleStatus(TighteningResults.AngleStatus angleStatus) {
        this.angleStatus = angleStatus;
    }

    public double getMinTorqueLimit() {
        return minTorqueLimit;
    }

    public void setMinTorqueLimit(double minTorqueLimit) {
        this.minTorqueLimit = minTorqueLimit;
    }

    public double getMaxTorqueLimit() {
        return maxTorqueLimit;
    }

    public void setMaxTorqueLimit(double maxTorqueLimit) {
        this.maxTorqueLimit = maxTorqueLimit;
    }

    public double getTargetTorque() {
        return targetTorque;
    }

    public void setTargetTorque(double targetTorque) {
        this.targetTorque = targetTorque;
    }

    public double getTorque() {
        return torque;
    }

    public void setTorque(double torque) {
        this.torque = torque;
    }

    public int getMinAngle() {
        return minAngle;
    }

    public void setMinAngle(int minAngle) {
        this.minAngle = minAngle;
    }

    public int getMaxAngle() {
        return maxAngle;
    }

    public void setMaxAngle(int maxAngle) {
        this.maxAngle = maxAngle;
    }

    public int getTargetAngle() {
        return targetAngle;
    }

    public void setTargetAngle(int targetAngle) {
        this.targetAngle = targetAngle;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Date getTighteningTime() {
        return tighteningTime;
    }

    public void setTighteningTime(Date tighteningTime) {
        this.tighteningTime = tighteningTime;
    }

    public Date getLastChangedTime() {
        return lastChangedTime;
    }

    public void setLastChangedTime(Date lastChangedTime) {
        this.lastChangedTime = lastChangedTime;
    }

    public TighteningResults.OKNOKCounterStatus getCounterStatus() {
        return counterStatus;
    }

    public void setCounterStatus(TighteningResults.OKNOKCounterStatus counterStatus) {
        this.counterStatus = counterStatus;
    }

    public long getTighteningID() {
        return tighteningID;
    }

    public void setTighteningID(long tighteningID) {
        this.tighteningID = tighteningID;
    }

    @Override
    public String toString() {
        return "TighteningResults [angle=" + angle + ", angleStatus=" + angleStatus + ", cellID=" + cellID
                + ", channelID=" + channelID + ", controllerName=" + controllerName + ", counterStatus=" + counterStatus
                + ", idCode=" + idCode + ", jobNumber=" + jobNumber + ", lastChangedTime=" + lastChangedTime
                + ", maxAngle=" + maxAngle + ", maxTorqueLimit=" + maxTorqueLimit + ", minAngle=" + minAngle
                + ", minTorqueLimit=" + minTorqueLimit + ", okCounterLimit=" + okCounterLimit + ", okCounterValue="
                + okCounterValue + ", programNumber=" + programNumber + ", targetAngle=" + targetAngle
                + ", targetTorque=" + targetTorque + ", tighteningID=" + tighteningID + ", tighteningStatus="
                + tighteningStatus + ", tighteningTime=" + tighteningTime + ", torque=" + torque + ", torqueStatus="
                + torqueStatus + "]";
    }
}