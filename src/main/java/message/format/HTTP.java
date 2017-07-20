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

    public static String statusDescription(int status){
        String statusDesc = statusMap.get(String.valueOf(status));
        return (statusDesc != null) ? statusDesc:"Unknown status";
    }


    private final static Map<String,String> statusMap = new HashMap<>();
    private static void includeStatDesc(int key, String statusDesc){
        statusMap.put(String.valueOf(key), statusDesc);
    }

    private HTTP(){}

    static {
        includeStatDesc(OK, "OK");
        includeStatDesc(NOT_MODIFY, "NOT MODIFIED");
        includeStatDesc(NOT_FOUND, "NOT FOUND");
        includeStatDesc(INTERNAL_ERROR, "INTERNAL ERROR");
    }





}
