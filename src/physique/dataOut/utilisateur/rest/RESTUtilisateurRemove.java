/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur.rest;

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
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTUtilisateurRemove extends AsyncTask<Object, Void, Object> {

    @Override
    protected Object doInBackground(Object... params) {
        Boolean retour = true;
        try {
            Utilisateur utilisateur = (Utilisateur) params[0];
            Context c = (Context) params[1];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/" + utilisateur.getId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"id\":" + utilisateur.getId() + "}";
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
        } catch (MalformedURLException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
}