package code;

import mechanism.HttpServlet;
import message.format.HTTP;
import message.format.HTTPRequest;
import message.format.HTTPResponse;

/**
 * Created by imran on 2/4/17.
 */
public class NotFound extends HttpServlet {

    @Override
    public void doGet(HTTPRequest request, HTTPResponse response) {
        response.setStatus(HTTP.NOT_FOUND);
        response.setContentType( "text/html");
        response.body().write("Not found");
    }

    @Override
    public void doPost(HTTPRequest request, HTTPResponse response) {
        doGet(request,response);
    }
}
