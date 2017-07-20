package message.impl;


import message.format.AbstractHttpMessage;
import message.format.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by imran on 1/31/17.
 */
public final class HttpRequestImpl extends AbstractHttpMessage implements HttpRequest {
    private final BufferedReader reader;


    public HttpRequestImpl(BufferedReader reader) {
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
