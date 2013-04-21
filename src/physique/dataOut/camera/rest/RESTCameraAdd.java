/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.camera.rest;

import android.graphics.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.Camera;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public class RESTCameraAdd {
    public static void execute(Ressource ressource, Camera camera) throws MalformedURLException, IOException  {
        URL url = new URL(ressource.getPathToAccesWebService() + "camera/"+camera.getPosition().getId()+"/"+camera.getNom()+"/"+camera.getIp()+"/"+camera.getType());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
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
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }
}
