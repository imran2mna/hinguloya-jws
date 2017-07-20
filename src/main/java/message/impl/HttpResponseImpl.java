package message.impl;

import message.format.AbstractHttpMessage;
import message.format.HttpResponse;
import message.format.ResponseProcessor;

import java.io.PrintWriter;

/**
 * Created by imran on 1/31/17.
 */
public final class HttpResponseImpl extends AbstractHttpMessage implements HttpResponse, ResponseProcessor {
    private PrintWriter printWriter;

    public HttpResponseImpl(PrintWriter printWriter) {
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

    public void setHeader(String key, String value) {
        headers.setHeader(key, value);
    }


    @Override
    public void publish() {


        headers.setHeader("Server", "Hinguloya WS");
        headers.setHeader("Content-Length", String.valueOf(body.length()));

        // give the servlets to control their content-type
        headers.setHeader("Content-Type", "text/html");

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
