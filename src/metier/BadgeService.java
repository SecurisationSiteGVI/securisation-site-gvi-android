/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import metier.entitys.Badge;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public interface BadgeService {

    public List<Badge> getAll(Context context) throws Exception;

    public List<Badge> getAll(Context context, int index, int nbResultat) throws Exception;

    public int count(Context c) throws Exception;

    public boolean remove(Context c, Badge badge) throws Exception;

    public boolean add(Context context, Badge badge) throws Exception;
}
