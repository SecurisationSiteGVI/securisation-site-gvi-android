/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge.asyncTask;

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
import physique.dataOut.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTBadgeRemove extends AsyncTask<Object, Void, Object> {

    @Override
    protected Object doInBackground(Object... params) {
        Boolean retour = false;
        Context c = (Context) params[0];
        Ressource ressource = (Ressource) params[1];
        Badge badge = (Badge) params[2];
        try {
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
            retour=true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
}