package code;

import mechanism.HttpServlet;
import message.format.HTTP;
import message.format.HTTPRequest;
import message.format.HTTPResponse;

/**
 * Created by imran on 2/4/17.
 */
public class PermissionDenied extends HttpServlet {

    @Override
    public void doGet(HTTPRequest request, HTTPResponse response) {
        response.setStatus(HTTP.NOT_FOUND);
        response.setHeader("Content-Type", "text/html");
        response.body().write("Permission Denied...");
    }

    @Override
    public void doPost(HTTPRequest request, HTTPResponse response) {
        doGet(request,response);
    }
}
