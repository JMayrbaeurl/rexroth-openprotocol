package com.microsoft.samples.nexo.openprotocol;

import java.util.HashMap;
import java.util.Map;

/**
 * ErrorMessages
 */
public class Errors {

    public static final int INVALIDDATA = 1;
    public static final String INVALIDDATA_MSG = "Invalid data";
    public static final String TIGHTENINGPROGRAMNOTAVAILABLE = "Tightening program not available";
    public static final String TIGHTENINGPROGRAMCANNOTBESPECIFIED = "Tightening program cannot be specified";
    public static final String TIGHTENINGPROGRAMNOTACTIVE = "Tightening program not active";
    public static final String ACTIVATIONIDCODEUPLOADALREADYAVAILABLE = "Activation of ID code upload already available";

    public static final int CLIENTALREADYCONNECTED = 96;
    public static final String CLIENTALREADYCONNECTED_MSG = "Client already connected";

    public static final int ACTIVATIONLASTRESULTALREADYAVAIL = 9;
    public static final String ACTIVATIONLASTRESULTALREADYAVAIL_MSG = "Activation for the last tightening result already available";

    public static final String message(int errorNumber) {

        if (errorNumber > 0 && errorNumber < 100) {
            if (Errors.errorMap.containsKey(new Integer(errorNumber)))
                return Errors.errorMap.get(new Integer(errorNumber));
            else
                return "Unknown error with number " + errorNumber;
        } else 
            throw new IllegalArgumentException("Error number must be in the range of 1 and 99");
    }

    private static Map<Integer, String> errorMap;

    static {
        Errors.errorMap = new HashMap<Integer, String>();
        Errors.errorMap.put(new Integer(INVALIDDATA), INVALIDDATA_MSG);
        Errors.errorMap.put(new Integer(CLIENTALREADYCONNECTED), CLIENTALREADYCONNECTED_MSG);
    }
}