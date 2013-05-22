/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.position.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public class RESTPositionCount {
    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static Integer execute(Ressource ressource) throws MalformedURLException, IOException {
        int ret = 0;
        BufferedReader br = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "position/count");
        InputStream fluxLecture = url.openStream();
        br = new BufferedReader(new InputStreamReader((fluxLecture)));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            ret = Integer.parseInt(output);
        }
        br.close();
        return ret;
    }
}
