package conf;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by imran on 3/26/17.
 */
public final class Configs {
    private static Configs instance;
    private final String propertyFile = "config.properties";
    public static String libFolder;

    static {
        File file = new File(Configs.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            libFolder = URLDecoder.decode(file.getParent(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            libFolder = null;
        }
    }


    private Configs() {}

    public static Configs getInstance() {
        if (instance == null) {
            synchronized (Configs.class) {
                instance = (instance == null) ? new Configs() : instance;
            }
        }
        return instance;
    }


}
