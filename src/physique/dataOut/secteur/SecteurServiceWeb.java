/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.secteur;

import android.content.Context;
import java.util.List;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public interface SecteurServiceWeb {

    public boolean ajouter(Ressource ressource, Secteur secteur) throws Exception;

    public List<Secteur> getAll(Ressource ressource, int index, int nbResutltat) throws Exception;

    public boolean remove(Ressource ressource, Secteur secteur) throws Exception;
}
