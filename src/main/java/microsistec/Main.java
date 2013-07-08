package microsistec;
import tools.Avocado.*;
import java.io.File;
import microsistec.Corretores;
import org.apache.catalina.startup.Tomcat;

public class Main {
	
	public static Connector DB;

    public static void main(String[] args) throws Exception {
		Main.DB = new Connector();
		Corretores corretores = new Corretores();
		
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();  
    }
}
