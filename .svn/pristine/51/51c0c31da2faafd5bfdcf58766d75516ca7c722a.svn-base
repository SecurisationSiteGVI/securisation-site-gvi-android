/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionSecteurDetecteurIntrusion.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.Camera;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public class RESTAttributionSecteurDetecteurIntrusionAttribuer {
    /**
     *
     * @param ressource
     * @param secteur
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws RuntimeException
     */
    public static void execute(Ressource ressource, Secteur secteur, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException, RuntimeException  {
        URL url = new URL(ressource.getPathToAccesWebService() + "attributionsecteurdetecteurintrusion/attribuer/"+secteur.getId()+"/"+detecteurIntrusion.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
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
    }
}
