/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

/**
 *
 * @author damien
 */
public class Ressource {

    private String protocol;
    private String serveurURL;
    private int port;
    private String applicationName;
    private String resourcesPath;

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the serveurURL
     */
    public String getServeurURL() {
        return serveurURL;
    }

    /**
     * @param serveurURL the serveurURL to set
     */
    public void setServeurURL(String serveurURL) {
        this.serveurURL = serveurURL;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * @return the resourcesPath
     */
    public String getResourcesPath() {
        return resourcesPath;
    }

    /**
     * @param resourcesPath the resourcesPath to set
     */
    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    /**
     *
     * @return
     */
    public String getPathToAccesWebService() {
        String cr = protocol + serveurURL + ":" + port + "/" + applicationName + "/" + resourcesPath + "/";
        return cr;
    }
}
