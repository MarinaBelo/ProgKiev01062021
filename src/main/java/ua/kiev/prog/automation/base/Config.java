package ua.kiev.prog.automation.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    final static public Param BROWSER_NAME  = new Param("browser.name", "chrome", true);
    final static public Param NO_GUI        = new Param("no.gui",       "false",  true);

    final static public Param TESTBED        = new Param("testbed",     "local",  true);
    final static public Param GRID_HOST      = new Param("grid.host",   "localhost",  true);
    final static public Param GRID_PORT      = new Param("grid.port",   "4444",  true);


    final static public Param SITE_URL      = new Param("site.url");
    final static public Param SITE_USERNAME = new Param("site.username");
    final static public Param SITE_PASSWORD = new Param("site.password");

    final static public Param MYSQL_HOST = new Param("mysql.host");
    final static public Param MYSQL_PORT = new Param("mysql.port");
    final static public Param MYSQL_DB = new Param("mysql.database");
    final static public Param MYSQL_USER = new Param("mysql.username");
    final static public Param MYSQL_PASSWD = new Param("mysql.password");

    final static public class Param{
        final public String value;

        public Param(String name){
            this(name, null);
        }

        public Param(String name, String defValue) {
            this(name, defValue, false);
        }
        public Param(String name, String defValue, boolean isSys) {
            String tmpValue = null;
                if(isSys)
                    tmpValue = System.getProperty(name);
                if (tmpValue==null)
                    tmpValue=getEnvProperties().getProperty(name);
                if (tmpValue==null)
                    tmpValue=defValue;
                if (tmpValue==null)
                    throw new RuntimeException("Parameter value is not found. Param: " +name);

            this.value = tmpValue;
        }

        final public boolean isTrue(){
            return "true".equalsIgnoreCase(value);
        }

        @Override
        final public String toString() {
            return this.value;
        }
    }

    static private Properties envProperties;

    static private Properties getEnvProperties () {
        if (envProperties == null){
            envProperties       = new Properties();
            String env          = System.getProperty("env", "dev");
            InputStream iStream = null;
            try{
                iStream = Config.class.getClassLoader().getResourceAsStream("env/" + env + ".properties");
                if (iStream == null)
                    throw  new RuntimeException("File is not found");
                envProperties.load(iStream);
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("Could not read properties file", e);
            }finally {
                try{
                    iStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                    throw new RuntimeException("Could not close input stream", e);
                }
            }
        }
        return envProperties;
    }
}
