package mechanism;


import message.format.HTTP;
import message.format.HttpRequest;
import message.format.HttpResponse;
import message.format.ResponseProcessor;

/**
 * Created by imran on 1/31/17.
 */
public abstract class HttpServlet {

    final void process(HttpRequest request, HttpResponse response){
        switch (request.method()) {
            case HTTP.GET:
                doGet(request,response);
                break;

            case HTTP.POST:
                doPost(request,response);
                break;

            default:
                System.out.println("Not an implemented method...");
                break;

        }
        // instead of below code, use executor command pattern to publish
        ((ResponseProcessor)response).publish();
    }

    public abstract void doGet(HttpRequest request, HttpResponse response);
    public abstract void doPost(HttpRequest request, HttpResponse response);
}
