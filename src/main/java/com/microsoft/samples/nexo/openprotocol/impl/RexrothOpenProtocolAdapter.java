package com.microsoft.samples.nexo.openprotocol.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import com.microsoft.samples.nexo.openprotocol.NexoCommException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RexrothOpenProtocolAdapter
 */
public class RexrothOpenProtocolAdapter {

    private static final Logger log = LoggerFactory.getLogger(RexrothOpenProtocolAdapter.class);

    private Socket clientSocket;
    private OutputStream outStream;
    private InputStreamReader inStream;

    private ROPMessageFactory messageFactory;
    private ROPMessageEncoder messageEncoder;
    private ROPMessageDecoder messageDecoder;

    private String ip;
    private int port;

    public RexrothOpenProtocolAdapter() {
        super();
    }

    public ROPReplyMessage sendROPRequestMessage(ROPRequestMessage requestMessage)
            throws UnknownHostException, IOException {

        if (requestMessage == null)
            throw new IllegalArgumentException("Value for parameter 'requestMessage' must not be NULL");

        if (!this.ready())
            this.open();

        String messageString = this.messageEncoder.encodeMessage(requestMessage);
        log.debug("Sending message to Nexo device using Open protocol: " + messageString);

        this.outStream.write(messageString.getBytes(StandardCharsets.US_ASCII));
        this.outStream.write(0);

        return this.createReplyMessage(requestMessage);
    }

    protected ROPReplyMessage createReplyMessage(ROPRequestMessage requestMessage) throws IOException {

        if (requestMessage == null)
            throw new IllegalArgumentException("Value for parameter 'requestMessage' must not be NULL");

        String resultString = this.readNexoResultString();
        ROPReplyMessage result = null;

        if (resultString != null && resultString.length() > 0) {

            int msgID = this.messageDecoder.readMessageIDfromReplyString(resultString);
            int rev = this.messageDecoder.readRevisionfromReplyString(resultString);

            result = (ROPReplyMessage)this.messageFactory.createMessageFor(msgID, rev);
            result = (ROPReplyMessage)this.messageDecoder.decodeMessage(resultString, result);
        }

        return result;
    }

    protected String readNexoResultString() throws IOException {

        char[] buffer = new char[1024];
        int i = inStream.read(buffer, 0, 1024);

        return i != -1 ? new String(buffer) : "";
    }

    public boolean ready() {
    
        return this.clientSocket != null && this.clientSocket.isConnected();
    }

    public void open() throws UnknownHostException, IOException {

        this.clientSocket = new Socket(this.ip, this.port);
        this.outStream = this.clientSocket.getOutputStream();
        this.inStream = new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.US_ASCII);
    }

    public void close()
    {
        if (this.inStream != null) {
            try {
                this.inStream.close();
            } catch (IOException e) {
                throw new NexoCommException("Exception on closing connection to Nexo", e);
            }
            this.inStream = null;
        }
        if (this.outStream != null) {
            try {
                this.outStream.close();
            } catch (IOException e) {
                throw new NexoCommException("Exception on closing connection to Nexo", e);
            }
            this.outStream = null;
        }
        if (this.clientSocket != null) {
            try {
                this.clientSocket.close();
            } catch (IOException e) {
                throw new NexoCommException("Exception on closing connection to Nexo", e);
            }
            this.clientSocket = null;
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ROPMessageFactory getMessageFactory() {
        return messageFactory;
    }

    public void setMessageFactory(ROPMessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public ROPMessageEncoder getMessageEncoder() {
        return messageEncoder;
    }

    public void setMessageEncoder(ROPMessageEncoder messageEncoder) {
        this.messageEncoder = messageEncoder;
    }

    public ROPMessageDecoder getMessageDecoder() {
        return messageDecoder;
    }

    public void setMessageDecoder(ROPMessageDecoder messageDecoder) {
        this.messageDecoder = messageDecoder;
    }
}