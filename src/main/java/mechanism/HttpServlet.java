package mechanism;


import message.format.HTTP;
import message.format.HTTPRequest;
import message.format.HTTPResponse;
import message.format.ResponseProcessor;
import org.apache.log4j.Logger;

/**
 * Created by imran on 1/31/17.
 */
public abstract class HttpServlet {
    private static Logger logger = Logger.getLogger(HttpServlet.class);

    final void process(HTTPRequest request, HTTPResponse response){
        switch (request.method()) {
            case HTTP.GET:
                doGet(request,response);
                break;

            case HTTP.POST:
                doPost(request,response);
                break;

            default:
                logger.debug("Not an implemented method...");
                break;

        }
        // instead of below code, use executor command pattern to publish
        ((ResponseProcessor)response).publish();
    }

    public abstract void doGet(HTTPRequest request, HTTPResponse response);
    public abstract void doPost(HTTPRequest request, HTTPResponse response);
}
