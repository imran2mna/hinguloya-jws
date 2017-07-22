package server;

import conf.Configs;
import mechanism.RequestSwitcher;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Created by imran on 1/29/17.
 */
public class ConnectionHandler implements Runnable {
    Logger logger = Logger.getLogger(ConnectionHandler.class);
    private Socket clientSocket;
    private long t1;
    private InputStream inputStream;
    private OutputStream outputStream;


    public ConnectionHandler(Socket clientSocket, long t1){
        this.clientSocket = clientSocket;
        this.t1 = t1;
    }

    @Override
    public void run() {

        try{
            clientSocket.setSoTimeout(Configs.TIMEOUT);

            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();


            RequestSwitcher requestSwitcher = new RequestSwitcher(
                    new BufferedReader(new InputStreamReader(inputStream)),
                    new PrintWriter(new OutputStreamWriter(outputStream))
            );
            requestSwitcher.doSwitch();


        }catch(Exception e){
            logger.error(e);
        }finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }

        long t2 = System.currentTimeMillis();
        logger.info("Time elapsed : " + (t2-t1));
    }

    private void closeStream(Closeable closeable){
        if(closeable != null){
            try{
                closeable.close();
            }catch (IOException e){
                logger.error("error in closing stream...");
            }
        }
    }

}
