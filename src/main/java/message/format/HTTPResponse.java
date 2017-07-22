package message.format;


/**
 * Created by imran on 1/31/17.
 */
public interface HTTPResponse extends HTTPMessage {
    public void setStatus(int status);
    public void setHeader(String key, String value);
    public void setContentType(String type);
}
