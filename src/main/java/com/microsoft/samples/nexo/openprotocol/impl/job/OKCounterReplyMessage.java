package com.microsoft.samples.nexo.openprotocol.impl.job;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.samples.nexo.openprotocol.impl.AbstractROPReplyMessage;

/**
 * OKCounterReplyMessage
 */
public class OKCounterReplyMessage extends AbstractROPReplyMessage {

    public static final int MESSAGEID = 31;
    
    public static final int STANDARD_REVISION = 1;

    private List<Integer> counters = new ArrayList<Integer>();

    public OKCounterReplyMessage() {
        super(MESSAGEID, STANDARD_REVISION);
    }

    public List<Integer> getCounters() {
        return counters;
    }

    public void setCounters(List<Integer> counters) {
        this.counters = counters;
    }

    public int[] toIntArray() {

        int[] result = new int[(this.counters.size())];
        for(int i = 0; i < this.counters.size(); i++) result[i] = this.counters.get(i).intValue();
        return result;
    }

    @Override
    public String toString() {
        return "OKCounterReplyMessage [counters=" + counters + "]";
    }
}