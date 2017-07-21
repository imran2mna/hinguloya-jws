package mechanism;

import conf.Configs;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.HashMap;

/**
 * Created by imran on 7/20/17.
 */
class FolderStore {
    private static HashMap<String,ApplicationContext> contexts;


    private FolderStore() {}

    static void list() {
        contexts = new HashMap<>();

        String deployPath = Configs.getLibFolder() + Configs.DOCUMENT_ROOT;
        File deployDir = new File(deployPath);

        if (!deployDir.exists() || !deployDir.isDirectory()) {
            deployPath = Configs.DOCUMENT_ROOT;
            deployDir = new File(deployPath);
            if (!deployDir.exists() || !deployDir.isDirectory()) {
                deployDir = null;
            }
        } else {
            deployDir = null;
        }

        if (deployDir != null) {
            for (String directory : deployDir.list()) {
                File file = new File(deployPath + "/" + directory);
                if (file.isDirectory()) {
                    contexts.put(directory, new ClassPathXmlApplicationContext(deployPath + "/" + directory +"/app-context.xml"));
                }
            }
        } else {
            System.err.println("Deployment directory not available...");
        }
    }
}
