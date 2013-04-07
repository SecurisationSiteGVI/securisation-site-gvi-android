/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.util.List;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public interface SecteurService {
    public boolean ajouter(Context context,  Secteur secteur)throws IOException, Exception;
    public List<Secteur> getAll(Context context,  int index, int nbResutltat)throws Exception;
    public boolean remove(Context context, Secteur secteur)throws Exception;
}
