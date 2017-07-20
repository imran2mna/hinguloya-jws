package server;



/**
 * Created by imran on 1/29/17.
 */
public class Container {
    public static void main(String[] args)  {
        ListenerThread listenerThread = new ListenerThread(9000);
        listenerThread.start();
    }
}
