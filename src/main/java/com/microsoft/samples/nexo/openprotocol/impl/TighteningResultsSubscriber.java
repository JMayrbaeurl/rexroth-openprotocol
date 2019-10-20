package com.microsoft.samples.nexo.openprotocol.impl;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.microsoft.samples.nexo.openprotocol.Errors;
import com.microsoft.samples.nexo.openprotocol.NexoCommException;
import com.microsoft.samples.nexo.openprotocol.NexoDevice;
import com.microsoft.samples.nexo.openprotocol.Subscriber;
import com.microsoft.samples.nexo.openprotocol.NexoDevice.ResultsHandler;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsMessageRev1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TighteningResultsSubscriber
 */
public class TighteningResultsSubscriber implements Subscriber, Runnable {

    private static final Logger log = LoggerFactory.getLogger(TighteningResultsSubscriber.class);

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    private ResultsHandler resultsHandler;

    private final NexoDevice device;

    private RexrothOpenProtocolAdapter protocolAdapter;

    private ROPMessageFactory messageFactory;
    
    public TighteningResultsSubscriber(int interval, NexoDevice nexo, RexrothOpenProtocolAdapter protocolAdapter,
            ROPMessageFactory messageFactory, ResultsHandler handler) {

        this.interval = interval;
        this.device = nexo;
        this.protocolAdapter = protocolAdapter;
        this.messageFactory = messageFactory;
        this.resultsHandler = handler;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    public void run() {

        running.set(true);

        log.debug("Starting last tightening results reading");

        while (running.get()) {

            this.processNextResult();

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        log.debug("Stopped last tightening results reading");
    }

    public void processNextResult() {

        ROPMessage message = null;
        try {
            message = this.protocolAdapter.readNextMessage();
            if (message != null)
                this.protocolAdapter.sendMessage(this.messageFactory.createLastResultsAcknMessage());
        } catch (IOException e) {
            log.error("Exception on reading next tightening result. ", e);
            running.set(false);
        }

        if (log.isDebugEnabled() && message != null)
            log.debug("Last tightening result from Nexo read: " + message);

        if (this.resultsHandler != null && message != null && message instanceof LastResultsMessageRev1) {
            this.resultsHandler.handleResult(((LastResultsMessageRev1)message).getResults());
        }
    }

    @Override
    public void startSubscription() {

        if (this.device.startCommunication()) {
            if (this.sendStartSubscriptionMessage())
                this.start();
        } else
            log.error("Cannot start subscription, because communication with nexo could not be started");
    }

    @Override
    public void stopSubscription() {
        this.interrupt();

        this.sendStopSubscriptionMessage();
    }

    private boolean sendStartSubscriptionMessage() {

        boolean result = false;

        log.debug("Now sending start subscription of tightening results command to Nexo device");

        ROPRequestMessage request = this.messageFactory.createLastResultsSubRequestMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply.isOK())
                    result = true;
                else {
                    if (reply instanceof CommandErrorMessage) {
                        result = ((CommandErrorMessage) reply).getErrorNumber() == Errors.ACTIVATIONLASTRESULTALREADYAVAIL;
                    }
                }
            } else {
                log.debug("Activate last tightening result subscription command didn't get a reply");
            }
        } catch (IOException e) {
                log.error("Can not Activate last tightening result subscription on NexoDevice", e);
                throw new NexoCommException("Can not Activate last tightening result subscription on NexoDevice", e);
        }

        if (log.isInfoEnabled()) {
            if (result)
                log.info("Successfully activated last tightening results subscription on Nexo device");
            else
                log.info("Could not activate last tightening results subscription on Nexo device");
        }

        return result;
    }

    private void sendStopSubscriptionMessage() {

        boolean result = false;

        log.debug("Now sending stop subscription of tightening results command to Nexo device");

        ROPRequestMessage request = this.messageFactory.createLastResultsSubStopMessage();
        try {
            ROPReplyMessage reply = this.protocolAdapter.sendROPRequestMessage(request);
            if (reply != null) {
                if (reply.isOK())
                    result = true;
            } else {
                log.debug("Deactivate last tightening result subscription command didn't get a reply");
            }
        } catch (IOException e) {
                log.error("Can not Deactivate last tightening result subscription on NexoDevice", e);
                throw new NexoCommException("Can not Deactivate last tightening result subscription on NexoDevice", e);
        }

        if (log.isInfoEnabled()) {
            if (result)
                log.info("Successfully deactivated last tightening results subscription on Nexo device");
            else
                log.info("Could not deactivate last tightening results subscription on Nexo device");
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

    public ResultsHandler getResultsHandler() {
        return resultsHandler;
    }

    public void setResultsHandler(ResultsHandler resultsHandler) {
        this.resultsHandler = resultsHandler;
    }
}