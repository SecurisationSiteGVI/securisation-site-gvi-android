/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.camera.rest;

import physique.dataOut.detecteurIntrusion.rest.*;
import physique.dataOut.position.rest.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.Camera;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Position;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public class RESTCameraRemove {
    /**
     *
     * @param ressource
     * @param camera
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static boolean execute(Ressource ressource, Camera camera) throws MalformedURLException, IOException {
        Boolean retour = true;
        URL url = new URL(ressource.getPathToAccesWebService() + "camera/" + camera.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        String input = "{\"id\":" + camera.getId() + "}";
        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            } else {
                System.out.println("Requete envoyé mais pas de réponse du serveur.");
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
        return retour;
    }
}
