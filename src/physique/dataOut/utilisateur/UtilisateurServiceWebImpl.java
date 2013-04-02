/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur;

import android.content.Context;
import android.os.AsyncTask;
import metier.entitys.Technicien;
import java.util.List;
import metier.entitys.Utilisateur;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurAdd;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurAddTechniecien;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurCount;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurGetAll;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurGetAllByRange;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurGetById;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurLoginIsUse;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurRemove;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurUpdate;
import physique.dataOut.utilisateur.asynctask.RESTUtilisateurVerificationConnexion;

/**
 *
 * @author damien
 */
public class UtilisateurServiceWebImpl implements UtilisateurServiceWeb {

    @Override
    public List<Utilisateur> getAll(Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurGetAll().execute(context);
        List<Utilisateur> retour = (List<Utilisateur>) ret.get();
        return retour;
    }

    @Override
    public boolean add(Utilisateur utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurAdd().execute(utilisateur, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean remove(Utilisateur utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurRemove().execute(utilisateur, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean update(Utilisateur utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurUpdate().execute(utilisateur, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean loginIsUse(String loginn, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurLoginIsUse().execute(loginn, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurVerificationConnexion(context).execute(utilisateur, context);
        Technicien technicien = (Technicien) ret.get();
        return technicien;
    }

    @Override
    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurGetAllByRange().execute(from, nbResut, context);
        List<Utilisateur> utilisateurs = (List<Utilisateur>) ret.get();
        return utilisateurs;

    }

    public Utilisateur getById(Long id, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurGetById().execute(id, context);
        List<Utilisateur> utilisateurs = (List<Utilisateur>) ret.get();
        return utilisateurs.get(0);
    }

    public int count(Context c) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurCount().execute(c);
        Integer count = (Integer) ret.get();
        return count;
    }

    public boolean addTechnicien(Technicien technicien,Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurAddTechniecien().execute(technicien, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }
}
