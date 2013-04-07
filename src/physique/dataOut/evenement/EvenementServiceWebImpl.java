/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import metier.entitys.Evenement;
import physique.dataOut.evenement.rest.RESTEvenementCount;
import physique.dataOut.evenement.rest.RESTEvenementGetAll;
import physique.dataOut.evenement.rest.RESTEvenementGetAllByRange;
import physique.dataOut.evenement.rest.RESTEvenementGetByID;

/**
 *
 * @author damien
 */
public class EvenementServiceWebImpl implements EvenementServiceWeb {

    public List<Evenement> getAll(Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTEvenementGetAll().execute(context);
        List<Evenement> retour = (List<Evenement>) ret.get();
        return retour;
    }

    public List<Evenement> getAll(Context context, int index, int nbResultat) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTEvenementGetAllByRange().execute(context, index, nbResultat);
        List<Evenement> retour = (List<Evenement>) ret.get();
        return retour;
    }

    public int count(Context c) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTEvenementCount().execute(c);
        Integer count = (Integer) ret.get();
        return count;
    }

    public Evenement getById(Context context, Long id) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTEvenementGetByID().execute(context, id);
        Evenement retour = (Evenement) ret.get();
        return retour;
    }
}