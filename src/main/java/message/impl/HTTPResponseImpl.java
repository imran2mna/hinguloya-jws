package message.impl;

import conf.Configs;
import message.format.AbstractHTTPMessage;
import message.format.HTTPResponse;
import message.format.ResponseProcessor;

import java.io.PrintWriter;

/**
 * Created by imran on 1/31/17.
 */
public final class HTTPResponseImpl extends AbstractHTTPMessage implements HTTPResponse, ResponseProcessor {
    private PrintWriter printWriter;

    public HTTPResponseImpl(PrintWriter printWriter) {
        this.printWriter = printWriter;
        super.initialize();
    }


    @Override
    protected void initProtocol() {
        protocol = new Protocol();
    }

    @Override
    protected void initHeader() {
        headers = new Headers();
    }

    @Override
    protected void initBody() {
        body = new Body();
    }

    @Override
    public void setStatus(int status) {
        protocol.setStatus(status);
    }

    @Override
    public void setHeader(String key, String value) {
        headers.setHeader(key, value);
    }

    @Override
    public void setContentType(String type) {
        headers.setHeader("Content-Type",type);
    }


    @Override
    public void publish() {
        headers.setHeader("Server", Configs.SERVER_NAME);
        headers.setHeader("Content-Length", String.valueOf(body.length()));

        for (char[] c : protocol.getWriteList()) {
            printWriter.write(c);
            printWriter.flush();
        }

        for (char[] c : headers.getWriteList()) {
            printWriter.write(c);
            printWriter.flush();
        }

        // line separator
        printWriter.write("\r\n");

        for (char[] c : body.getWriteList()) {
            printWriter.write(c);
            printWriter.flush();
        }


    }

}
