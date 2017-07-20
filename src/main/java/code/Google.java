package code;

import mechanism.HttpServlet;
import message.format.HTTP;
import message.format.HTTPRequest;
import message.format.HTTPResponse;

/**
 * Created by imran on 1/31/17.
 */
public class Google extends HttpServlet {
    @Override
    public void doGet(HTTPRequest request, HTTPResponse response) {
        response.setStatus(HTTP.NOT_FOUND);
        response.body().write("Google");
    }

    @Override
    public void doPost(HTTPRequest request, HTTPResponse response) {
        doGet(request,response);
    }
}
