/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.borneAcces.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.BorneAcces;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public class RESTBorneAccesAdd {
    /**
     *
     * @param ressource
     * @param borneAcces
     * @throws MalformedURLException
     * @throws IOException
     */
    public static void execute(Ressource ressource, BorneAcces borneAcces) throws MalformedURLException, IOException  {
        URL url = new URL(ressource.getPathToAccesWebService() + "borneacces/"+borneAcces.getPosition().getId()+"/"+borneAcces.getNom()+"/"+borneAcces.getEntrer());
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
