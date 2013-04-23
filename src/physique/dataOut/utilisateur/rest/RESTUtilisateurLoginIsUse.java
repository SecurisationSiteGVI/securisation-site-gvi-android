/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import metier.entitys.Ressource;
import metier.entitys.Technicien;

/**
 *
 * @author damien
 */
public class RESTUtilisateurLoginIsUse {

    /**
     *
     * @param params
     * @return
     * @throws IOException
     */
    public Object execute(Object... params) throws IOException {
        BufferedReader br = null;
        Boolean ret = false;
        Ressource ressource = (Ressource) params[1];
        Technicien utilisateur = new Technicien();
        Long id = utilisateur.getId();
        String prenom = utilisateur.getPrenom();
        String nom = utilisateur.getNom();
        String email = utilisateur.getEmail();
        String telephoneFixe = utilisateur.getTelephoneFixe();
        String telephonePortable = utilisateur.getTelephonePortable();
        String ville = utilisateur.getVille();
        int codePostale = utilisateur.getCodePostale();
        String adresse = utilisateur.getAdresse();
        boolean homme = utilisateur.isHomme();
        String login = (String) params[0];
        String password = utilisateur.getPassword();
        Date dateDeNaissance = utilisateur.getDateDeNaissance();
        URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/loginIsUse");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        String input = "{\"id\":" + id + ",\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\","
                + "\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"codePostale\":" + codePostale + ","
                + "\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + dateDeNaissance + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\"}";
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            } else {
                System.out.println("Requete envoyé mais pas de réponse du serveur.");
            }
        }
        br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output;

        while ((output = br.readLine()) != null) {
            System.out.println("'" + output + "'");
        }
        if (output.equals("true")) {
            ret = true;
        }
        conn.disconnect();
        br.close();
        return ret;
    }
}