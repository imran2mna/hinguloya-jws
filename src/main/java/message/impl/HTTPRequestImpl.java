package message.impl;


import message.format.AbstractHTTPMessage;
import message.format.HTTPRequest;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by imran on 1/31/17.
 */
public final class HTTPRequestImpl extends AbstractHTTPMessage implements HTTPRequest {
    private final BufferedReader reader;


    public HTTPRequestImpl(BufferedReader reader) {
        this.reader = reader;
        super.initialize();
    }

    @Override
    public void initProtocol() {
        try {
            protocol = new Protocol(reader.readLine());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void initHeader() {
        headers = new Headers(reader);
    }

    @Override
    public void initBody() {
        body = new Body(reader);
    }


    @Override
    public String query(String key) {
        return protocol.query(key);
    }
}
