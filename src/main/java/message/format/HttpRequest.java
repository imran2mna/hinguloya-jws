package message.format;

/**
 * Created by imran on 1/31/17.
 */
public interface HttpRequest extends HttpMessage {
    public String query(String key);
}
