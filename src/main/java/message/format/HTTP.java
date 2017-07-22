package message.format;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by imran on 2/5/17.
 */
public final class HTTP {

    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static int OK = 200;
    public final static int NOT_MODIFY = 302;
    public final static int NOT_FOUND = 400;
    public final static int INTERNAL_ERROR = 500;

    public final static String EXT_HTML = "html";
    public final static String EXT_TXT = "txt";

    public final static String TXT_HTML = "text/html";
    public final static String TXT_PLAIN = "text/plain";


    private final static Map<String,String> statusMap = new HashMap<>();
    private final static Map<String,String> extensionMap = new HashMap<>();


    private static void includeStatDesc(int key, String statusDesc){
        statusMap.put(String.valueOf(key), statusDesc);
    }

    private static void includeExtensionDesc(String key, String statusDesc){
        extensionMap.put(key, statusDesc);
    }

    public static String statusDescription(int status){
        String statusDesc = statusMap.get(String.valueOf(status));
        return (statusDesc != null) ? statusDesc:"Unknown status";
    }

    public static String getContentType(String key){
        String extensionDesc = extensionMap.get(key);
        return (extensionDesc != null) ? extensionDesc:"Unknown MIME";
    }

    static {
        includeStatDesc(OK, "OK");
        includeStatDesc(NOT_MODIFY, "NOT MODIFIED");
        includeStatDesc(NOT_FOUND, "NOT FOUND");
        includeStatDesc(INTERNAL_ERROR, "INTERNAL ERROR");


        includeExtensionDesc(EXT_HTML,TXT_HTML);
        includeExtensionDesc(EXT_TXT,TXT_PLAIN);
    }



    private HTTP(){}

}
