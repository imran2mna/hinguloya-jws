package message.format;

/**
 * Created by imran on 1/31/17.
 */
public interface HTTPRequest extends HTTPMessage {
    public String query(String key);
}
