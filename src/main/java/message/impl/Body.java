package message.impl;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imran on 1/31/17.
 */
public class Body {
    private  String bodyStr;
    private static final int BUFFER_SIZE = 1024;
    private List<char[]> writeList;
    private int bodyLength = 0;

    public Body() {
        writeList = new ArrayList<>();
    }

    public Body(BufferedReader reader) {
        this();
        try {

            StringBuilder sb = new StringBuilder();

            char[] buff = new char[8];
            int readCount;
            int i = 0;
            while (reader.ready()) {
                readCount = reader.read(buff);

                if(readCount != buff.length){
                    for(;i < readCount ; i++)
                        sb.append(buff[i]);
                }else {
                    sb.append(buff);
                }
            }

            this.bodyStr = sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<char []> getWriteList(){
        return writeList;
    }

    public String getBodyStr(){
        return (bodyStr != null) ? bodyStr : "";
    }


    public void write(String s){
        int len = s.length();

        char[] buff;
        int srcBegin = 0;
        while(len > BUFFER_SIZE){
            buff = new char[BUFFER_SIZE];
            s.getChars(srcBegin,BUFFER_SIZE,buff,0);
            write(buff);
            len -= BUFFER_SIZE;
            srcBegin += BUFFER_SIZE;
        }

        if(len > 0) {
            buff = new char[len];
            s.getChars(srcBegin, len, buff, 0);
            write(buff);
        }
    }

    public void write(char[] buff){
        bodyLength += buff.length;
        writeList.add(buff);
    }

    public void write(int c){
        char[] buff = new char[1];
        buff[0] = (char) c;
        write(buff);
    }

    public void println(){
        write("\n");
    }

    public void print(boolean b){
        write(b ? "true" : "false");
    }

    public void print(char c){
        write(c);
    }

    public int length() {
        return bodyLength;
    }
}
