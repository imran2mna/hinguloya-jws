package mechanism;


import conf.Configs;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.util.HashMap;


/**
 * Created by imran on 1/31/17.
 */
class ContentStore {
    private static Logger logger = Logger.getLogger(ContentStore.class);
    private static ApplicationContext context;


    static {
        try{
            context = new FileSystemXmlApplicationContext("file:"+Configs.getLibFolder() + Configs.DOCUMENT_ROOT + "/app-context.xml");
        }
        catch (BeansException e){
            logger.error("Error in app-context.xml initialize...", e);
        }
    }

    private ContentStore(){}


    static HttpServlet getServlet(String location){
        HttpServlet httpServlet = null;


        if(context != null) {
            try {
                httpServlet = (HttpServlet) context.getBean(location);
            } catch (BeansException e) {
                logger.error(e);
            }
        }
        return httpServlet;
    }

    static StaticPage getStaticPage(String documentRoot, String urlLocation){
        StaticPage staticPage = null;

        File file = new File(documentRoot + urlLocation);
        if(file.exists() && !file.isDirectory()){
            staticPage = new StaticPage(documentRoot + urlLocation);
        }
        return staticPage;
    }


}
