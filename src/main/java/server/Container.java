package server;


import conf.Configs;

/**
 * Created by imran on 1/29/17.
 */
public class Container {
    public static void main(String[] args)  {
        ListenerThread listenerThread = new ListenerThread(Configs.PORT);
        listenerThread.start();
    }
}
