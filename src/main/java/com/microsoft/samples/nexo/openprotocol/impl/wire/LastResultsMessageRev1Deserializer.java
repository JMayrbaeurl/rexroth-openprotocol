package com.microsoft.samples.nexo.openprotocol.impl.wire;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.microsoft.samples.nexo.openprotocol.TighteningResults;
import com.microsoft.samples.nexo.openprotocol.TighteningResults.AngleStatus;
import com.microsoft.samples.nexo.openprotocol.TighteningResults.OKNOKCounterStatus;
import com.microsoft.samples.nexo.openprotocol.TighteningResults.TighteningStatus;
import com.microsoft.samples.nexo.openprotocol.TighteningResults.TorqueStatus;
import com.microsoft.samples.nexo.openprotocol.impl.ROPMessage;
import com.microsoft.samples.nexo.openprotocol.impl.results.LastResultsMessageRev1;

/**
 * LastResultsMessageRev1Deserializer
 */
public class LastResultsMessageRev1Deserializer extends ROPMessageDeserializer {

    @Override
    protected ROPMessage doFromString(String str, ROPMessage message) {

        if (str == null | str.length() == 0)
            throw new IllegalArgumentException("Parameter str must not be null");

        if (message == null | !(message instanceof LastResultsMessageRev1))
            throw new IllegalArgumentException("Resulting message type is not Last Results reply");

        if (message instanceof LastResultsMessageRev1) {
            LastResultsMessageRev1 reply = (LastResultsMessageRev1)message;
            TighteningResults result = new TighteningResults();

            int offset = 2;
            result.setCellID(Integer.parseInt(str.substring(offset, offset+4)));
            offset += 4+2;
            result.setChannelID(Integer.parseInt(str.substring(offset, offset+2)));
            offset += 2+2;
            result.setControllerName(str.substring(offset, offset+25).trim());
            offset += 25+2;
            result.setIdCode(str.substring(offset, offset+25).trim());
            offset += 25+2;
            result.setJobNumber(Integer.parseInt(str.substring(offset, offset+2)));
            offset += 2+2;
            result.setProgramNumber(Integer.parseInt(str.substring(offset, offset+3)));
            offset += 3+2;
            result.setOkCounterLimit(Integer.parseInt(str.substring(offset, offset+4)));
            offset += 4+2;
            result.setOkCounterValue(Integer.parseInt(str.substring(offset, offset+4)));
            offset += 4+2;
            if (str.charAt(offset) == '0')
                result.setTighteningStatus(TighteningStatus.NOK);
            else if (str.charAt(offset) == '1')
                result.setTighteningStatus(TighteningStatus.OK);
            else
                throw new IllegalStateException("Unknown tightening status '" + str.charAt(offset) + "' found'");
            offset += 1+2;
            if (str.charAt(offset) == '0')
                result.setTorqueStatus(TorqueStatus.Low);
            else if (str.charAt(offset) == '1')
                result.setTorqueStatus(TorqueStatus.OK);
            else if (str.charAt(offset) == '2')
                result.setTorqueStatus(TorqueStatus.High);
            else 
                throw new IllegalStateException("Unknown torque status '" + str.charAt(offset) + "' found'");
            offset += 1+2;
            if (str.charAt(offset) == '0')
                result.setAngleStatus(AngleStatus.Low);
            else if (str.charAt(offset) == '1')
                result.setAngleStatus(AngleStatus.OK);
            else if (str.charAt(offset) == '2')
                result.setAngleStatus(AngleStatus.High);
            else 
                throw new IllegalStateException("Unknown angle status '" + str.charAt(offset) + "' found'");
            offset += 1+2;
            result.setMinTorqueLimit(Integer.parseInt(str.substring(offset, offset+6)) / 100);
            offset += 6+2;
            result.setMaxTorqueLimit(Integer.parseInt(str.substring(offset, offset+6)) / 100);
            offset += 6+2;
            result.setTargetTorque(Integer.parseInt(str.substring(offset, offset+6)) / 100);
            offset += 6+2;
            result.setTorque(Integer.parseInt(str.substring(offset, offset+6)) / 100);
            offset += 6+2;
            result.setMinAngle(Integer.parseInt(str.substring(offset, offset+5)));
            offset += 5+2;
            result.setMaxAngle(Integer.parseInt(str.substring(offset, offset+5)));
            offset += 5+2;
            result.setTargetAngle(Integer.parseInt(str.substring(offset, offset+5)));
            offset += 5+2;
            result.setAngle(Integer.parseInt(str.substring(offset, offset+5)));
            offset += 5+2;
            String datePattern = "yyyy-MM-dd:HH:mm:ss";
            SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
            try {
                result.setTighteningTime(formatter.parse(str.substring(offset, offset + 19)));
            } catch (ParseException e) {
                throw new IllegalStateException("Unknown date time format used for tightening time");
            }
            offset += 19+2;
            try {
                result.setLastChangedTime(formatter.parse(str.substring(offset, offset + 19)));
            } catch (ParseException e) {
                throw new IllegalStateException("Unknown date time format used for last changed time");
            }
            offset += 19+2;
            if (str.charAt(offset) == '0')
                result.setCounterStatus(OKNOKCounterStatus.Other);
            else if (str.charAt(offset) == '1')
                result.setCounterStatus(OKNOKCounterStatus.ChXYCntOK);
            else if (str.charAt(offset) == '2')
                result.setCounterStatus(OKNOKCounterStatus.NotUsed);
            else 
                throw new IllegalStateException("Unknown counter status '" + str.charAt(offset) + "' found'");
            offset += 1+2;
            result.setTighteningID(Long.parseLong(str.substring(offset, offset+10)));

            reply.setResults(result);
        }

        return message;
    }

    @Override
    public int datalength(int revision) {
        return 211;
    }

    
}