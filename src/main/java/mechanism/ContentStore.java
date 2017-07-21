package mechanism;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.HashMap;


/**
 * Created by imran on 1/31/17.
 */
class ContentStore {
    private static ApplicationContext context;
    private static HashMap<String,ApplicationContext> contexts;

    static {
        try{
            context = new ClassPathXmlApplicationContext("app-context.xml");
            contexts = new HashMap<>();

        }
        catch (BeansException e){
            System.err.println("Error in app-context.xml initialize...");
            throw e;
        }
    }


    private ContentStore(){}



    static HttpServlet getServlet(String location){
        HttpServlet httpServlet = null;


        // use JAXB to map URL and beans
        FolderStore.list();

        if(context != null) {
            try {
                httpServlet = (HttpServlet) context.getBean(location);
            } catch (BeansException e) {
                System.err.println(e);
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


    public static void loadContexts(){
        contexts.clear();


    }

}
