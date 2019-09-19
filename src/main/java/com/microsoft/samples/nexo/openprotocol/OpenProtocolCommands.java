package com.microsoft.samples.nexo.openprotocol;

/**
 * OpenProtocolCommands
 */
public interface OpenProtocolCommands {

    public String sendROPCommand(String commandString) throws NexoCommException;
}