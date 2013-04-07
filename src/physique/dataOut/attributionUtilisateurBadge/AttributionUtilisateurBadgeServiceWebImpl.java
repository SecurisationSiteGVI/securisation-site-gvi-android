/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionUtilisateurBadge;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeAttribuer;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeGetBadgeNotAssign;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeGetUtilisateurNotAssign;

/**
 *
 * @author damien
 */
public class AttributionUtilisateurBadgeServiceWebImpl implements AttributionUtilisateurBadgeServiceWeb {

    public List<Badge> getBadgesNotAssign(Context context, Ressource ressource, int debut, int nbResult) {
        AsyncTask<Object, Void, Object> ret = new RESTAttributionBadgeGetBadgeNotAssign().execute(context, ressource, debut, nbResult);
        List<Badge> retour = null;
        try {
            retour = (List<Badge>) ret.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    public List<Badge> getBadgesNotAssignByNumero(Context context, Ressource ressource, String numero, int debut, int nbResult) {
        AsyncTask<Object, Void, Object> ret = new RESTAttributionBadgeGetBadgeNotAssign().execute(context, ressource, debut, nbResult,numero);
        List<Badge> retour = null;
        try {
            retour = (List<Badge>) ret.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    public List<Utilisateur> getUtilisateurNotAssign(Context context, Ressource ressource, int debut, int nbResult) {
        AsyncTask<Object, Void, Object> ret = new RESTAttributionBadgeGetUtilisateurNotAssign().execute(context, ressource, debut, nbResult);
        List<Utilisateur> retour = null;
        try {
            retour = (List<Utilisateur>) ret.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context, Ressource ressource, String nom, int debut, int nbResult) {
        AsyncTask<Object, Void, Object> ret = new RESTAttributionBadgeGetUtilisateurNotAssign().execute(context, ressource, debut, nbResult,nom);
        List<Utilisateur> retour = null;
        try {
            retour = (List<Utilisateur>) ret.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    public boolean attribuer(Context context, Ressource ressource, Utilisateur utilisateur, Badge badge) {
        AsyncTask<Object, Void, Object> ret = new RESTAttributionBadgeAttribuer().execute(context, ressource, utilisateur, badge);
        Boolean retour = null;
        try {
            retour = (Boolean) ret.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AttributionUtilisateurBadgeServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
}
