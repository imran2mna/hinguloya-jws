package server;


import conf.Configs;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by imran on 1/29/17.
 */
public class ListenerThread extends Thread {
    private int port;
    private boolean isStopped = false;
    private ServerSocket serverSocket;

    private ExecutorService threadPool = Executors.newFixedThreadPool(Configs.NO_THREADS);


    public ListenerThread(int port) {
        this.port = port;
    }

    @Override
    public void run() {

            try {
                serverSocket = new ServerSocket(this.port);
                System.out.println("Listening on port : " + this.port);
            }catch (IOException e){
                System.err.println(e);
                doStop();
            }

            while (!isStopped) {

                try{
                    threadPool.execute(new ConnectionHandler(serverSocket.accept(), System.currentTimeMillis()));
                } catch (IOException e){
                    if(isStopped){
                        System.err.println("Server stopped...");
                        break;
                    } else {
                        System.err.println("Error in accepting connection : " + e.getMessage());
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
                System.err.println("Closing server socket : " + e.getMessage());
            }
        }
    }

}
