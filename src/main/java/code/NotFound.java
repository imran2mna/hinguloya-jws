package code;

import mechanism.HttpServlet;
import message.format.HttpRequest;
import message.format.HttpResponse;

/**
 * Created by imran on 2/4/17.
 */
public class NotFound extends HttpServlet {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.body().write("Not found");
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        doGet(request,response);
    }
}
