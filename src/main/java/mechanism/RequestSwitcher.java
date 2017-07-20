package mechanism;


import code.NotFound;
import message.format.HttpRequest;
import message.format.HttpResponse;
import message.impl.HttpRequestImpl;
import message.impl.HttpResponseImpl;

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

        HttpRequest httpRequest = new HttpRequestImpl(reader);
        HttpResponse httpResponse = new HttpResponseImpl(writer);

        HttpServlet httpServlet = ContentStore.getServlet(httpRequest.location());

        // use configuration property file for document root
        httpServlet = (httpServlet == null) ? ContentStore.getStaticPage("/home/imran/", httpRequest.URLlocation()) : httpServlet;
        httpServlet = (httpServlet == null) ? new NotFound(): httpServlet;


        httpServlet.process(httpRequest,httpResponse);

    }

}
