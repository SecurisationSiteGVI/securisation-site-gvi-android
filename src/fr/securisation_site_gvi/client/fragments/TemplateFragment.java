/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;
import fr.securisation_site_gvi.client.R;


/**
 *
 * @author damien
 */
public class TemplateFragment extends Fragment{
    protected Context activityContext;
     public void setThisActivityOn() {
        this.initGraphicalObjects();
        this.addActionListnerForAllGraphicalObjects();
        this.addInitialValueForGraphicalObjects();
    }
    public void initGraphicalObjects() {
    }

    public void addActionListnerForAllGraphicalObjects() {
    }

    public void addInitialValueForGraphicalObjects() {
    }

    public  void throwConnectException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Celui-ci est surement éteint.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public  void throwIOException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Le réseau est défaillant, vérifier les paramétres d'accès.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public  void throwSAXException() {
//        Toast toast = Toast.makeText(this.activityContext, "Aucun résultat dans la base de données.", Toast.LENGTH_SHORT);
//        toast.show();
    }

    public  void throwParserConfigurationException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de récupérer les informations du serveur. L'application ne peut pas récuperer les informations.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public  void throwMalformedURLException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Veuillez verifier l'url que vous avez saisie.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public  void throwException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Impossible de dététerminer le probléme.", Toast.LENGTH_SHORT);
        toast.show();
    }
  
    public  void throwParseException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de récupérer les informations du serveur. La chaine de caractére n'a pas pu étre recupéré.", Toast.LENGTH_SHORT);
        toast.show();
    }
     @Deprecated
    public int getHeight() {
        int height = 0;
        height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    @Deprecated
    public int getWidth() {// nexus 7 : 800dp // s3 720dp
        int height = 0;
        height = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        return height;
    }

    public boolean isTablette7() {
        int width = 0;
        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        boolean ret = false;
        if (width > 799) {
            ret = true;
        }
        return ret;
    }

    public boolean isTelephone() {
        int width = 0;
        getActivity().getWindowManager().getDefaultDisplay().getWidth();
        boolean ret = false;
        if (width > 700 && width < 750) {
            ret = true;
        }
        return ret;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getActivity(), fr.securisation_site_gvi.client.Parametres.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
   
}
