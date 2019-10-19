package com.microsoft.samples.nexo.openprotocol.impl;

import com.microsoft.samples.nexo.openprotocol.NexoDevice;
import com.microsoft.samples.nexo.openprotocol.Subscriber;
import com.microsoft.samples.nexo.openprotocol.NexoDevice.ResultsHandler;
import com.microsoft.samples.nexo.openprotocol.TighteningResults;

import org.junit.Test;

/**
 * LastResultsSubscriptionTests
 */
public class LastResultsSubscriptionTests extends AbstractNexoTests {

    @Test
    public void testResultsSubscription() {

        NexoDevice device = this.createDeviceClient();
        Subscriber subscription = device.subscribeToTighteningResults(new TestResultsHandler());
        subscription.startSubscription();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
        }

        subscription.stopSubscription();
    }

    static class TestResultsHandler implements ResultsHandler {

        @Override
        public void handleResult(TighteningResults result) {
            System.out.println(result.toString());
        }
    }
}