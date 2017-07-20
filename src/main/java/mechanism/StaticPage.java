package mechanism;

import message.format.HttpRequest;
import message.format.HttpResponse;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by imran on 3/11/17.
 */
final class StaticPage extends HttpServlet{
    public final String fileLocation;

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
        FileReader fileReader = null;
        BufferedReader br = null;
        String line;
        try {
            fileReader = new FileReader(fileLocation);
            br = new BufferedReader(fileReader);

            while ((line = br.readLine()) != null) {
                response.body().write(line);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
