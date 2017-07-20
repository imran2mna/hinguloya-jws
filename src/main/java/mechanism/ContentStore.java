package mechanism;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;



/**
 * Created by imran on 1/31/17.
 */
public class ContentStore {
    private static ApplicationContext context;

    static {
        try{
            context = new ClassPathXmlApplicationContext("app-context.xml");}
        catch (BeansException e){
            System.err.println("Error in app-context.xml initialize...");
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
                System.err.println(e);
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
