package com.microsoft.samples.nexo.openprotocol.impl.results;

import com.microsoft.samples.nexo.openprotocol.TighteningResults;

/**
 * LastResultsMessageRev1
 */
public class LastResultsMessageRev1 extends AbstractLastResultsMessage {

    private TighteningResults results;

    public LastResultsMessageRev1() {
        super(1);
    }

    public TighteningResults getResults() {
        return results;
    }

    public void setResults(TighteningResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "LastResultsMessageRev1 [results=" + results + "]";
    }

}