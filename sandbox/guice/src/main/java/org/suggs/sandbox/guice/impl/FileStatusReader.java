package org.suggs.sandbox.guice.impl;

import org.suggs.sandbox.guice.State;
import org.suggs.sandbox.guice.StatusReader;

public class FileStatusReader implements StatusReader {

    private String fileUrl;

    public FileStatusReader(String aFileUrl) {
        fileUrl = aFileUrl;
    }

    @Override
    public State readState() {
        return new State();
    }
}
