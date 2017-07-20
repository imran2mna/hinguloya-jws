package mechanism;

import message.format.HttpRequest;
import message.format.HttpResponse;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by imran on 3/11/17.
 */
final class StaticPage extends HttpServlet{
    public String fileLocation;

    StaticPage(String fileLocation){
        this.fileLocation = fileLocation;
    }


    @Override
    public final void doGet(HttpRequest request, HttpResponse response) {
        processRequest(request,response);
    }

    @Override
    public final void doPost(HttpRequest request, HttpResponse response) {
        processRequest(request,response);
    }


    private void processRequest(HttpRequest request, HttpResponse response) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            String line;
            while ((line = br.readLine()) != null ){
                response.body().write(line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
