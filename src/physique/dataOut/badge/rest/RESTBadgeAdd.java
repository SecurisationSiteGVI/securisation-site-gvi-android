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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTBadgeAdd extends AsyncTask<Object, Void, Object> {

    @Override
    protected Object doInBackground(Object... params) {
        boolean ret = false;
        Context c = (Context) params[0];
        Ressource ressource = (Ressource) params[1];
        Badge badge = (Badge) params[2];
        try {
            URL url = new URL(ressource.getPathToAccesWebService() + "badge");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"id\":0,\"numero\":\"" + badge.getNumero() + "\"}";
            OutputStream os = conn.getOutputStream();
            System.out.println(input);
            os.write(input.getBytes());
            os.flush();
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
            ret = true;
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}