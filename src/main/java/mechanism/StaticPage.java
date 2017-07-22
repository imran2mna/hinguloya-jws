package mechanism;

import message.format.HTTP;
import message.format.HTTPRequest;
import message.format.HTTPResponse;

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
    public final void doGet(HTTPRequest request, HTTPResponse response) {
        processRequest(request,response);
    }

    @Override
    public final void doPost(HTTPRequest request, HTTPResponse response) {
        processRequest(request,response);
    }


    private void processRequest(HTTPRequest request, HTTPResponse response) {
        FileReader fileReader = null;
        BufferedReader br = null;
        String line;
        response.setContentType(HTTP.getContentType(fileLocation.substring(fileLocation.indexOf('.') + 1)));
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
