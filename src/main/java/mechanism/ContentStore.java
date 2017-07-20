package mechanism;


import com.thoughtworks.xstream.XStream;
import conf.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by imran on 1/31/17.
 */
public class ContentStore {
    private static Logger logger = Logger.getLogger(ContentStore.class);
    private static ApplicationContext context;
    private static final String documentRoot = "/home/imran/Desktop/WebServer";


    static {
        try{
            context = new ClassPathXmlApplicationContext("app-context.xml");}
        catch (BeansException e){
            logger.info("Error in app-context.xml initialize...");
            throw e;
        }
    }


    private ContentStore(){}

    public  static HttpServlet getServlet(String location){
        HttpServlet httpServlet = null;


        // use JAXB to map URL and beans

        if(context != null) {
            try {
                httpServlet = (HttpServlet) context.getBean(location);
            } catch (BeansException e) {
                logger.info(e);
            }
        }
        return httpServlet;
    }

    public static StaticPage getStaticPage(String documentRoot, String urlLocation){
        StaticPage staticPage = null;

        File file = new File(documentRoot + urlLocation);
        if(file.exists() && !file.isDirectory()){
            staticPage = new StaticPage(documentRoot + urlLocation);
        }
        return staticPage;
    }

}
