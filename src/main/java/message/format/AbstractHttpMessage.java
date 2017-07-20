package message.format;

import message.impl.Body;
import message.impl.Headers;
import message.impl.Protocol;

/**
 * Created by imran on 1/31/17.
 */
public abstract class AbstractHttpMessage implements HttpMessage {
    protected Protocol protocol;
    protected Headers headers;
    protected Body body;


    public AbstractHttpMessage(){

    }

    protected void initialize(){
        initProtocol();
        initHeader();
        initBody();
    }

    protected abstract void initProtocol();
    protected abstract void initHeader();
    protected abstract void initBody();


    public Protocol protocol() {
        return protocol;
    }

    public Headers headers() {
        return headers;
    }

    public Body body() {
        return body;
    }

    public String location(){
        return protocol.getLocation();
    }

    public String URLlocation(){
        return protocol.getUrlLocation();
    }


    public String method(){
        return protocol.getMethod();
    }


}
