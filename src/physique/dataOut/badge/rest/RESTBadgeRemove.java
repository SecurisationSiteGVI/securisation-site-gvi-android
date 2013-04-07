/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge.rest;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTBadgeRemove {

    public static Object execute(Object... params) throws MalformedURLException, IOException {
        Boolean retour = false;
        Context c = (Context) params[0];
        Ressource ressource = (Ressource) params[1];
        Badge badge = (Badge) params[2];
        URL url = new URL(ressource.getPathToAccesWebService() + "badge/" + badge.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        String input = "{\"id\":" + badge.getId() + "}";
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
        retour = true;
        return retour;
    }
}