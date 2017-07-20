package code;

import mechanism.HttpServlet;
import message.format.HTTP;
import message.format.HTTPRequest;
import message.format.HTTPResponse;

/**
 * Created by imran on 1/31/17.
 */
public class HelloWorld extends HttpServlet {
    @Override
    public void doGet(HTTPRequest request, HTTPResponse response) {

        String userName = request.query("user-name");
        response.body().write("<html><h1>Hello World " + ((userName != null) ? userName : "" )+ "</h1></html>");
        response.setStatus(HTTP.OK);
    }

    @Override
    public void doPost(HTTPRequest request, HTTPResponse response) {
        doGet(request,response);
    }
}
