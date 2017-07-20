package mechanism;


import code.NotFound;
import conf.Configs;
import message.format.HTTPRequest;
import message.format.HTTPResponse;
import message.impl.HTTPRequestImpl;
import message.impl.HTTPResponseImpl;

import java.io.BufferedReader;
import java.io.PrintWriter;


/**
 * Created by imran on 1/31/17.
 */
public class RequestSwitcher {
    private final BufferedReader reader;
    private final PrintWriter writer;


    public RequestSwitcher(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void doSwitch() {

        HTTPRequest httpRequest = new HTTPRequestImpl(reader);
        HTTPResponse httpResponse = new HTTPResponseImpl(writer);

        HttpServlet httpServlet = ContentStore.getServlet(httpRequest.location());

        httpServlet = (httpServlet == null) ? ContentStore.getStaticPage(Configs.DOCUMENT_ROOT, httpRequest.URLlocation()) : httpServlet;
        httpServlet = (httpServlet == null) ? new NotFound(): httpServlet;


        httpServlet.process(httpRequest,httpResponse);

    }

}
