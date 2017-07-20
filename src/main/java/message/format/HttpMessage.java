package message.format;

import message.impl.Body;
import message.impl.Headers;
import message.impl.Protocol;

/**
 * Created by imran on 1/31/17.
 */
public interface HttpMessage {

    public Protocol protocol();
    public Headers headers();
    public Body body();
    public String location();
    public String URLlocation();
    public String method();
}
