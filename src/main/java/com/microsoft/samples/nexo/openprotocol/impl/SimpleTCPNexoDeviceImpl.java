package com.microsoft.samples.nexo.openprotocol.impl;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;

import com.microsoft.samples.nexo.openprotocol.Errors;
import com.microsoft.samples.nexo.openprotocol.NexoCommException;
import com.microsoft.samples.nexo.openprotocol.NexoDeviceToolData;
import com.microsoft.samples.nexo.openprotocol.OpenProtocolCommands;
import com.microsoft.samples.nexo.openprotocol.Subscriber;
import com.microsoft.samples.nexo.openprotocol.TCPBasedNexoDevice;
import com.microsoft.samples.nexo.openprotocol.impl.batt.BatteryLevelMessage;
import com.microsoft.samples.nexo.openprotocol.impl.comm.CommunicationKeepAliveReply;
import com.microsoft.samples.nexo.openprotocol.impl.job.OKCounterReplyMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.ProgramNumbersMessage;
import com.microsoft.samples.nexo.openprotocol.impl.program.SelectProgramRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeMessage;
import com.microsoft.samples.nexo.openprotocol.impl.time.TimeSetMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ActivateRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.DeactivateRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.tool.ToolDataMessage;
import com.microsoft.samples.nexo.openprotocol.impl.vis.ShowOnDisplayRequestMessage;
import com.microsoft.samples.nexo.openprotocol.impl.wifi.WifiLevelMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleTCPNexoDeviceImpl
 */
public class SimpleTCPNexoDeviceImpl implements TCPBasedNexoDevice, OpenProtocolCommands {

    private static final Logger log = LoggerFactory.getLogger(SimpleTCPNexoDeviceImpl.class);

    private RexrothOpenProtocolAdapter protocolAdapter;

    private ROPMessageFactory messageFactory;

    /**
     * 
     */
    @Override
    public NexoDeviceToolData getToolData() throws NexoCommException {

        NexoDeviceToolData result = null;

        ROPRequestMessage request = this.messageFactory.createToolDataRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof ToolDataMessage) {
                    result = ((ToolDataMessage) reply).getToolData();
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Getting tool data of Tightening device didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on getting tool data from tightening device", e);
            throw new NexoCommException("Exception on getting tool data from tightening device", e);
        }

        return result;
    }

    /**
     * 
     */
    @Override
    public boolean activateTool() throws NexoCommException {

        boolean result = false;

        ROPRequestMessage request = this.messageFactory.createActivateRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof CommandAcceptedMessage) {
                    result = ((CommandAcceptedMessage) reply)
                            .getAcceptedMessageID() == ActivateRequestMessage.MESSAGEID;
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Activate Tool command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on trying to activate tool", e);
            throw new NexoCommException("Exception on trying to activate tool", e);
        }

        return result;
    }

    @Override
    public boolean deactivateTool() throws NexoCommException {

        boolean result = false;

        ROPRequestMessage request = this.messageFactory.createDeactivateRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof CommandAcceptedMessage) {
                    result = ((CommandAcceptedMessage) reply)
                            .getAcceptedMessageID() == DeactivateRequestMessage.MESSAGEID;
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Deactivate Tool command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on trying to deactivate tool", e);
            throw new NexoCommException("Exception on trying to deactivate tool", e);
        }

        return result;
    }

    @Override
    public boolean isActivated() throws NexoCommException {

        log.error("Unimplemented method 'isActivated' called");
        throw new NexoCommException("Unimplemented method 'isActivated' called");
    }

    @Override
    public Date getTime() throws NexoCommException {

        Date result = null;

        ROPRequestMessage request = this.messageFactory.createTimeRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof TimeMessage) {
                    result = ((TimeMessage) reply).getDeviceTime();
                    if (result == null)
                        throw new NexoCommException("Could not get the current time from the device");
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Getting time on Tightening device didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on getting current time from tightening device", e);
            throw new NexoCommException("Exception on getting current time from tightening device", e);
        }

        return result;
    }

    @Override
    public void setTime(final Date newTime) throws NexoCommException {

        if (newTime == null)
            throw new IllegalArgumentException("Parameter for new time of Nexo device must not be null");

        ROPRequestMessage request = this.messageFactory.createTimeSetMessage(newTime);
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof CommandAcceptedMessage) {
                    if (((CommandAcceptedMessage) reply).getAcceptedMessageID() != TimeSetMessage.MESSAGEID) {
                        throw new NexoCommException("Wrong accept message for command "
                                + ((CommandAcceptedMessage) reply).getAcceptedMessageID() + ". Expected "
                                + TimeSetMessage.MESSAGEID);
                    }
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                    else
                        throw new NexoCommException("Error message returned from Nexo: " + reply);
                }
            } else {
                log.debug("Setting time on Tightening device didn't get a reply");
            }

        } catch (IOException e) {
            log.error("Exception on setting time on tightening device", e);
            throw new NexoCommException("Exception on setting time on tightening device", e);
        }
    }

    @Override
    public int[] getTighteningprogramNumbers() throws NexoCommException {

        int[] result = null;

        ROPRequestMessage request = this.messageFactory.createProgramNumbersRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof ProgramNumbersMessage) {
                    result = ((ProgramNumbersMessage) reply).programNumbers();
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Read Tightening program numbers command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on reading tightening program numbers", e);
            throw new NexoCommException("Exception on reading tightening program numbers", e);
        }

        return result;
    }

    @Override
    public boolean selectTighteningProgram(int programNumber) throws NexoCommException {

        boolean result = false;

        if (programNumber < 0 || programNumber > 999) {
            throw new NexoCommException("Tightening program number has to be in the range of 0 to 999");
        }

        ROPRequestMessage request = this.messageFactory.createSelectProgramRequestMessage(programNumber);
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof CommandAcceptedMessage) {
                    result = ((CommandAcceptedMessage) reply)
                            .getAcceptedMessageID() == SelectProgramRequestMessage.MESSAGEID;
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                    else
                        log.error("Tightning program " + programNumber + " cannot be selected.");
                }
            } else {
                log.debug("Select tightening program command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on trying to select tightening program " + programNumber, e);
            throw new NexoCommException("Exception on trying to select tightening program " + programNumber, e);
        }

        return result;    
    }

    @Override
    public int[] getOKCounters() throws NexoCommException {

        int[] result = null;

        ROPRequestMessage request = this.messageFactory.createOKCountersRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof OKCounterReplyMessage) {
                    result = ((OKCounterReplyMessage) reply).toIntArray();
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Read OK Counters command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on reading OK Counters", e);
            throw new NexoCommException("Exception on reading OK Counters", e);
        }

        return result;
    }

    @Override
    public boolean showOnDisplay(String message) throws NexoCommException {

        return this.doShowOnDisplay(message, -1);
    }

    @Override
    public boolean showOnDisplay(String message, int duration) throws NexoCommException {

        return this.doShowOnDisplay(message, duration);
    }

    protected boolean doShowOnDisplay(String message, int duration) throws NexoCommException {

        boolean result = false;

        ROPRequestMessage request = this.messageFactory.createShowOnDisplayRequestMessage(message, duration);
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof CommandAcceptedMessage) {
                    result = ((CommandAcceptedMessage) reply)
                            .getAcceptedMessageID() == ShowOnDisplayRequestMessage.MESSAGEID;
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Show on display command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on showing string on Nexo device display", e);
            throw new NexoCommException("Exception on showing string on Nexo device display", e);
        }

        return result;
    }

    @Override
    public int getBatteryLevel() throws NexoCommException {

        int result = -1;

        ROPRequestMessage request = this.messageFactory.createBatteryLevelRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof BatteryLevelMessage) {
                    result = ((BatteryLevelMessage) reply).getBatteryLevel();
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Get Battery level command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on getting battery level from Nexo device", e);
            throw new NexoCommException("Exception on getting battery level from Nexo device", e);
        }

        return result;
    }

    @Override
    public int getWIFILevel() throws NexoCommException {

        int result = -1;

        ROPRequestMessage request = this.messageFactory.createWifiLevelRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof WifiLevelMessage) {
                    result = ((WifiLevelMessage) reply).getWifiLevel();
                } else {
                    if (!reply.isError())
                        throw new NexoCommException("Unknown reply message received: " + reply.getClass().toString());
                }
            } else {
                log.debug("Get WIFI level command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Exception on getting wifi level from Nexo device", e);
            throw new NexoCommException("Exception on getting wifi level from Nexo device", e);
        }

        return result;
    }

    @Override
    public boolean startCommunication() throws NexoCommException {

        boolean result = false;

        log.debug("Now send start communication command to Nexo device");

        ROPRequestMessage request = this.messageFactory.createStartCommunicationRequest();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply.isOK())
                    result = true;
                else {
                    if (reply instanceof CommandErrorMessage) {
                        result = ((CommandErrorMessage) reply).getErrorNumber() == Errors.CLIENTALREADYCONNECTED;
                    }
                }
            } else {
                log.debug("Start communication command didn't get a reply");
            }
        } catch (IOException e) {
            if (!(e instanceof ConnectException)) {
                log.error("Can not start communication with NexoDevice", e);
                throw new NexoCommException("Could not start communication with Nexo device", e);
            } else {
                log.warn("Can not start communication with NexoDevice. Connection timed out");
            }
        }

        if (log.isInfoEnabled()) {
            if (result)
                log.info("Successfully started communication with Nexo device");
            else
                log.info("Could not start communication with Nexo device");
        }

        return result;
    }

    @Override
    public void stopCommunication() throws NexoCommException {

        boolean result = false;

        log.debug("Now send stop communication command to Nexo device");

        ROPRequestMessage request = this.messageFactory.createStopCommunicationRequest();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                result = reply.isOK();
            } else {
                log.debug("Stop communication command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Can not start communication with NexoDevice", e);
        } finally {
            if (this.protocolAdapter != null)
                this.protocolAdapter.close();
        }

        if (log.isInfoEnabled()) {
            if (result)
                log.info("Successfully stopped communication with Nexo device");
            else
                log.info("Could not stop communication with Nexo device");
        }
    }

    @Override
    public void sendKeepAlive() throws NexoCommException {

        log.debug("Now send keep alive command to Nexo device");

        ROPRequestMessage request = this.messageFactory.createCommunicationKeepAliveMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply instanceof CommunicationKeepAliveReply)
                    log.debug("Keep alive command received keep alive acknowledement");
                else {
                    log.error("Keep alive command didn't get correct reply: " + reply.toString());
                }
            } else {
                log.error("Keep alive command didn't get a reply");
            }
        } catch (IOException e) {
            log.error("Can not keep communication with NexoDevice alive", e);
            throw new NexoCommException("Can not keep communication with NexoDevice alive", e);
        }
    }

    @Override
    public String sendROPCommand(final String commandString) throws NexoCommException {

        try {
            return this.protocolAdapter.sendROPRequestMessageString(commandString);
        } catch (IOException e) {
            log.error("Can not send command string to Nexo device", e);
            throw new NexoCommException("Can not send command string to Nexo device", e);
        }
    }

    public RexrothOpenProtocolAdapter getProtocolAdapter() {
        return protocolAdapter;
    }

    public void setProtocolAdapter(RexrothOpenProtocolAdapter protocolAdapter) {
        this.protocolAdapter = protocolAdapter;
    }

    public ROPMessageFactory getMessageFactory() {
        return messageFactory;
    }

    public void setMessageFactory(ROPMessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    @Override
    public Subscriber subscribeToTighteningResults(ResultsHandler handler) {

        TighteningResultsSubscriber result = new TighteningResultsSubscriber(100, this, this.protocolAdapter,
                this.messageFactory, handler);

        return result;
    }

    @Override
    public String listeningIPAddress() {

        return this.protocolAdapter != null ? this.protocolAdapter.getIp() : null;
    }

    @Override
    public int listeningPort() {

        return this.protocolAdapter != null ? this.protocolAdapter.getPort() : -1;
    }

}