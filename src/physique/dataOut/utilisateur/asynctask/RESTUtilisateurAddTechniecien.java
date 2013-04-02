/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Ressource;
import metier.entitys.Technicien;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTUtilisateurAddTechniecien extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[1];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Boolean retour = true;
            try {
                Technicien utilisateur = (Technicien) params[0];
                utilisateur.encode(true);
                String prenom = utilisateur.getPrenom();
                String nom = utilisateur.getNom();
                String email = utilisateur.getEmail();
                String login = utilisateur.getLogin();
                String password = utilisateur.getPassword();
                String telephoneFixe = utilisateur.getTelephoneFixe();
                String telephonePortable = utilisateur.getTelephonePortable();
                String ville = utilisateur.getVille();
                int codePostale = utilisateur.getCodePostale();
                String adresse = utilisateur.getAdresse();
                boolean homme = utilisateur.isHomme();
                Date dateDeNaissance = utilisateur.getDateDeNaissance();
                URL url = new URL(ressource.getPathToAccesWebService() + "technicien");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":0,\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephone"
                        + "Fixe\":\"" + telephoneFixe + "\",\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"cod"
                        + "ePostale\":" + codePostale + ",\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\""
                        + "dateDeNaissance\":\"" + dateDeNaissance + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\"}";
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

            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return retour;
        }
    }