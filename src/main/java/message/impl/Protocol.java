package message.impl;

import message.format.HTTP;

import java.util.*;

/**
 * Created by imran on 1/31/17.
 */
public class Protocol {
    private String method;
    private String urlLocation;
    private String location;
    private double version;
    private int status;

    private Map<String, String> queries;
    private String protocolString;
    private static final int BUFFER_SIZE = 1024;


    public Protocol(){
        version = 1.1;
    }

    public Protocol(String protocolString) {
        this.protocolString = protocolString;

            StringTokenizer st = new StringTokenizer(protocolString, " ");

            int count = 0;
            while (st.hasMoreTokens()) {
                count++;
                switch (count) {
                    case 1:
                        method = st.nextToken();
                        break;

                    case 2:
                        urlLocation = st.nextToken();
                        break;

                    case 3:
                        String word = st.nextToken();
                        version = Double.parseDouble(word.substring(word.indexOf('/') + 1));
                        break;
                    default:
                }

            }

            generateQueries();
            location = getUrlLocation().substring(1);

    }


    String query(String key){
        return queries.get(key);
    }

    private void generateQueries() {

        queries = new HashMap<>();

        if (urlLocation.contains("?")) {

            String query = urlLocation.substring(urlLocation.indexOf('?') + 1);


            try {
                StringTokenizer stQuery = new StringTokenizer(query, "&");

                try {
                    while (stQuery.hasMoreTokens()) {
                        StringTokenizer stMap = new StringTokenizer(stQuery.nextToken(), "=");
                        queries.put(stMap.nextToken(), stMap.nextToken());
                    }
                } catch (NoSuchElementException e) {
                    System.err.println("no such element found - " + urlLocation);
                    throw e;
                }

            }catch (Exception e){
                System.err.println("error for urlLocation - " + urlLocation);
                throw e;
            }

            urlLocation = urlLocation.substring(0, urlLocation.indexOf('?'));
        }

    }

    public String getMethod() {
        return method;
    }

    public String getUrlLocation() {
        return urlLocation;
    }

    public String getLocation(){
        return location;
    }

    public double getVersion() {
        return version;
    }

    public String getProtocolString() {
        return protocolString;
    }



     List<char []> getWriteList(){

        List<char[]> writeList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP");
        sb.append('/');
        sb.append(version);
        sb.append(' ');

        if(status != 0){
            sb.append(status);
            sb.append(' ');
            sb.append(HTTP.statusDescription(status));
        } else {
            sb.append(HTTP.OK);
            sb.append(' ');
            sb.append(HTTP.statusDescription(HTTP.OK));
        }

        sb.append("\n");
        write(sb.toString(), writeList);

        return writeList;
    }


    // todo - refactor following methods
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

    void setStatus(int status) {
        this.status = status;
    }
}
