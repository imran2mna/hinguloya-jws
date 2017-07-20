package message.format;


/**
 * Created by imran on 1/31/17.
 */
public interface HttpResponse extends HttpMessage {
    public void setStatus(int status);
    public void setHeader(String key, String value);
}
