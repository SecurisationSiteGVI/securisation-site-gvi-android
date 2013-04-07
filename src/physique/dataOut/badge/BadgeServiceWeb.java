/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge;

import android.content.Context;
import java.util.List;
import metier.entitys.Badge;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public interface BadgeServiceWeb {

    public List<Badge> getAll(Context context, Ressource ressource) throws Exception;

    public List<Badge> getAll(Context context, Ressource ressource, int index, int nbResultat) throws Exception;

    public int count(Context c, Ressource ressource) throws Exception;

    public boolean remove(Context c, Ressource ressource, Badge badge) throws Exception;

    public boolean add(Context context, Ressource ressource, Badge badge) throws Exception;
}
