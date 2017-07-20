package conf;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by imran on 3/26/17.
 */
public final class ConfigurationSettings {
    private static ConfigurationSettings instance;
    public static String deploymentFolder;

    static {
        File file = new File(ConfigurationSettings.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            deploymentFolder = URLDecoder.decode(file.getParent(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            deploymentFolder = null;
        }
    }



    private ConfigurationSettings(){

    }

    public static ConfigurationSettings getInstance(){
        if(instance == null){
            synchronized (ConfigurationSettings.class){
                instance = (instance == null) ? new ConfigurationSettings(): instance;
            }
        }
        return instance;
    }





}
