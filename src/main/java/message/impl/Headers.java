package message.impl;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by imran on 1/31/17.
 */
public class Headers {
    private Map<String, String> headerMap;
    private String headersString;

    private static final int BUFFER_SIZE = 1024;


    public Headers() {
        headerMap = new HashMap<>();
        // we need to set through pre allocated file
        headerMap.put("Content-Type", "text/html");
    }

    public Headers(BufferedReader reader) {
        this();

        try {

            if(!reader.ready()) return;

            String headerLine;
            StringBuilder sb = new StringBuilder();
            while ((headerLine = reader.readLine()) != null && headerLine.length() != 0) {
                headerMap.put(
                        headerLine.substring(0,headerLine.indexOf(':')),
                        headerLine.substring(headerLine.indexOf(':') + 1)
                );

                sb.append(headerLine);
                sb.append("\r\n");


            }

            this.headersString = sb.toString();
        }catch(Exception e){
            System.err.println(e);
        }
    }


    void setHeader(String key, String value){
        headerMap.put(key,value);
    }

    public String getHeader(String key){
        return headerMap.get(key);
    }



    public String getHeadersString(){
        return headersString;
    }


    List<char []> getWriteList(){
        List<char[]> writeList = new ArrayList<>();

        StringBuilder sb;
        for (Map.Entry<String,String > entry : headerMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            sb = new StringBuilder();
            sb.append(key).append(":").append(value).append("\r\n");
            write(sb.toString(), writeList);
        }

        return writeList;
    }

    private void write(String s,List<char[]> writeList){
        int len = s.length();

        char[] buff;
        int srcBegin = 0;
        while(len > BUFFER_SIZE){
            buff = new char[BUFFER_SIZE];
            s.getChars(srcBegin,BUFFER_SIZE,buff,0);
            writeList.add(buff);
            len -= BUFFER_SIZE;
            srcBegin += BUFFER_SIZE;
        }

        if(len > 0) {
            buff = new char[len];
            s.getChars(srcBegin, len, buff, 0);
            writeList.add(buff);
        }
    }

}
