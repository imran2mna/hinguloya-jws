package conf;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * Created by imran on 3/26/17.
 */
public final class Configs {
    private static final String propertyFile = "/conf/config.properties";
    private static String libFolder;
    private static Properties properties;

    public static String SERVER_NAME = Defaults.SERVER_NAME;
    public static int PORT = Defaults.PORT;
    public static String DOCUMENT_ROOT = Defaults.DOCUMENT_ROOT;
    public static int NO_THREADS = Defaults.NO_THREADS;
    public static int TIMEOUT = Defaults.TIMEOUT;


    public static String getLibFolder(){
        return libFolder;
    }

    static {
        loadProperties();
    }

    private static void loadProperties() {

        File file = new File(Configs.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            libFolder = URLDecoder.decode(file.getParent(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            libFolder = null;
        }

        if (libFolder != null) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(libFolder + propertyFile);
                properties = new Properties();
                properties.load(inputStream);

                for(String property: properties.stringPropertyNames()){
                    setProperty(property);
                }

            } catch (FileNotFoundException e) {
                System.err.println("File not found : " + libFolder + propertyFile);
            } catch (IOException e) {
                System.err.println(e);
            } finally {

                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {

                }
            }
        }

    }

    private static String setProperty(String key){
        String value = properties.getProperty(key);

        if (value != null && !value.trim().equals("")) {
            if ("PORT".equals(key)) {
                PORT = Integer.parseInt(value);
            } else if ("SERVER_NAME".equals(key)) {
                SERVER_NAME = value;
            }else if ("DOCUMENT_ROOT".equals(key)) {
                DOCUMENT_ROOT = value;
            }else if ("NO_THREADS".equals(key)) {
                NO_THREADS = Integer.parseInt(value);
            }else if ("TIMEOUT".equals(key)) {
                TIMEOUT = Integer.parseInt(value);
            }
        }
        return value;
    }

}
