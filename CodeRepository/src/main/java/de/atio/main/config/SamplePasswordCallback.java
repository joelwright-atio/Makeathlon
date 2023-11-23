package de.atio.main.config;

import com.thingworx.communications.client.IPasswordCallback;

public class SamplePasswordCallback implements IPasswordCallback {

    private String appKey = null;

    public SamplePasswordCallback(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public char[] getSecret() {

        return appKey.toCharArray();
    }

}