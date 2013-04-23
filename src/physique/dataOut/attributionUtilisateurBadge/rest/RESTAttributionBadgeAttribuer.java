/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionUtilisateurBadge.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;

/**
 *
 * @author damien
 */
public class RESTAttributionBadgeAttribuer {

    /**
     *
     * @param params
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static Object execute(Object... params) throws MalformedURLException, IOException {
        boolean ret = false;
        Ressource ressource = (Ressource) params[0];
        Utilisateur utilisateur = (Utilisateur) params[1];
        Badge badge = (Badge) params[2];
        URL url = new URL(ressource.getPathToAccesWebService() + "attributionutilisateurbadge/attribuer/" + utilisateur.getId() + "/" + badge.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream os = conn.getOutputStream();
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
        ret = true;

        return ret;
    }
}