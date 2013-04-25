/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.authorisationAcces.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import metier.entitys.AuthorisationAcces;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public class RESTAuthorisationAccesAdd {

    public static void execute(Ressource ressource, AuthorisationAcces authorisationAcces) throws MalformedURLException, IOException  {
        URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        String trameSecteur="\"secteurs\":[";
        String prefix="]";
        String[] secteurs= new String[authorisationAcces.getSecteurs().size()];
        for(int i=0;i<authorisationAcces.getSecteurs().size();i++){
            trameSecteur+="{\"id\":\""+authorisationAcces.getSecteurs().get(i).getId()+"\",\"nom\":\""+authorisationAcces.getSecteurs().get(i).getNom()+"\"}";
            if((i<1)&&(i!=authorisationAcces.getSecteurs().size()-1)){
                trameSecteur+=",";
            }
        }trameSecteur+=prefix;
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String heureDebut= dateFormat.format(authorisationAcces.getHeureOuverture());
        String heureFin= dateFormat.format(authorisationAcces.getHeureFermeture());
        String input = "{\"heureFermeture\":\""+heureFin+"\",\"heureOuverture\":\""+heureFin+"\","
                + "\"id\":\""+authorisationAcces.getId()+"\","+trameSecteur+",\"utilisateur\":{"
                + "\"codePostale\":\""+authorisationAcces.getUtilisateur().getCodePostale()+"\","
                + "\"homme\":\""+authorisationAcces.getUtilisateur().isHomme()+"\","
                + "\"id\":\""+authorisationAcces.getId()+"\",\"nom"
                + "\":\""+authorisationAcces.getUtilisateur().getNom()+"\",\""
                + "prenom\":\""+authorisationAcces.getUtilisateur().getPrenom()+"\"}}";
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
    }
}