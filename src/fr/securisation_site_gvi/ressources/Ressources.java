/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.ressources;

/**
 *
 * @author damien
 */
public class Ressources {
    private final static String protocol ="http://";
    private final static String serveurURL ="192.168.1.11";//"localhost";
    private final static int  port = 8080;
    private final static String applicationName ="securisation-site-gvi-web";
    private final static String resourcesPath ="webresources";
    private final static String pathToAccesWebService =protocol+serveurURL+":"+port+"/"+applicationName+"/"+resourcesPath+"/";

    public static String getProtocol() {
        return protocol;
    }

    public static String getServeurURL() {
        return serveurURL;
    }

    public static int getPort() {
        return port;
    }

    public static String getApplicationName() {
        return applicationName;
    }

    public static String getResourcesPath() {
        return resourcesPath;
    }

    public static String getPathToAccesWebService() {
        return pathToAccesWebService;
    }
}
