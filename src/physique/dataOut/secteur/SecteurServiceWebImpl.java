/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.secteur;

import android.content.Context;
import java.io.IOException;
import java.util.List;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import physique.dataOut.secteur.rest.RESTSecteurAjout;

/**
 *
 * @author damien
 */
public class SecteurServiceWebImpl implements SecteurServiceWeb{

    public boolean ajouter( Ressource ressource, Secteur secteur) throws IOException  {
        RESTSecteurAjout.execute(ressource, secteur);
        return true;
    }

    public List<Secteur> getAll( Ressource ressource, int index, int nbResutltat) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean remove( Ressource ressource, Secteur secteur) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
