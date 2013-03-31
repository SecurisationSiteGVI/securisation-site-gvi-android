/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import physique.dataOut.badge.asyncTask.RESTBadgeCount;
import physique.dataOut.badge.asyncTask.RESTBadgeGetAll;
import physique.dataOut.badge.asyncTask.RESTBadgeGetAllByRange;

/**
 *
 * @author damien
 */
public class BadgeServiceWebImpl implements BadgeServiceWeb {

    public List<Badge> getAll(Context context, Ressource ressource) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTBadgeGetAll().execute(context, ressource);
        List<Badge> retour = (List<Badge>) ret.get();
        return retour;
    }

    public List<Badge> getAll(Context context, Ressource ressource, int index, int nbResultat) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTBadgeGetAllByRange().execute(context, ressource, index, nbResultat);
        List<Badge> retour = (List<Badge>) ret.get();
        return retour;
    }

    public int count(Context context, Ressource ressource) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTBadgeCount().execute(context, ressource);
        Integer retour = (Integer) ret.get();
        return retour;
    }
}
