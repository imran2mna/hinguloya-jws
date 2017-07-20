package server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by imran on 1/29/17.
 */
public class ListenerThread extends Thread {
    private static Logger logger = Logger.getLogger(ListenerThread.class);
    private int port;
    private boolean isStopped = false;
    private ServerSocket serverSocket;

    // configure thread pool size through property file
    private ExecutorService threadPool = Executors.newFixedThreadPool(100);


    public ListenerThread(int port) {
        this.port = port;
    }

    @Override
    public void run() {

            try {
                serverSocket = new ServerSocket(this.port);
                logger.info("Listening on port : " + this.port);
            }catch (IOException e){
                logger.error(e);
                doStop();
            }

            while (!isStopped) {

                try{
                    threadPool.execute(new ConnectionHandler(serverSocket.accept(), System.currentTimeMillis()));
                } catch (IOException e){
                    if(isStopped){
                        logger.debug("Server stopped...");
                        break;
                    } else {
                        logger.error("Error in accepting connection : " + e.getMessage());
                    }
                }
            }

            threadPool.shutdown();
    }

    public synchronized void doStop() {
        isStopped = true;
        if(serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.info("Closing server socket : " + e.getMessage());
            }
        }
    }

}
