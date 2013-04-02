/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Ressource;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTEvenementCount extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            int ret = 0;
            try {
                Context c = (Context) params[0];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedReader br = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "evenement/count");
                InputStream fluxLecture = url.openStream();
                br = new BufferedReader(new InputStreamReader((fluxLecture)));
                String output;
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    ret = Integer.parseInt(output);
                }
                br.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }