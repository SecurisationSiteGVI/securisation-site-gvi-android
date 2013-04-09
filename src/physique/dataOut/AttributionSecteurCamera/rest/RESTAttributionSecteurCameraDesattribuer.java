/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.AttributionSecteurCamera.rest;

import android.media.CamcorderProfile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.BorneAcces;
import metier.entitys.Camera;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public class RESTAttributionSecteurCameraDesattribuer {
    public static void execute(Ressource ressource, Secteur secteur, Camera camera) throws MalformedURLException, IOException, RuntimeException  {
        URL url = new URL(ressource.getPathToAccesWebService() + "attributionsecteurcamera/desattribuer/"+secteur.getId()+"/"+camera.getId());
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
