package code;

import conf.Configs;
import mechanism.HttpServlet;
import message.format.HTTP;
import message.format.HttpRequest;
import message.format.HttpResponse;

/**
 * Created by imran on 1/31/17.
 */
public class Google extends HttpServlet {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setStatus(HTTP.NOT_FOUND);
        response.body().write(Configs.libFolder);
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        doGet(request,response);
    }
}
