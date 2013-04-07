/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionUtilisateurBadge;

import android.content.Context;
import java.util.List;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;

/**
 *
 * @author damien
 */
public interface AttributionUtilisateurBadgeServiceWeb {
    public List<Badge> getBadgesNotAssign(Context context, Ressource ressource, int debut, int nbResult);
    public List<Badge> getBadgesNotAssignByNumero(Context context, Ressource ressource, String numero,int debut, int nbResult);
    public List<Utilisateur> getUtilisateurNotAssign(Context context, Ressource ressource,int debut,int nbResult);
    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context, Ressource ressource,String nom,int debut,int nbResult);
    public boolean attribuer(Context context, Ressource ressource, Utilisateur utilisateur, Badge badge);
}
