/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;

/**
 *
 * @author damien
 */
public interface AttributionUtilisateurBadgeService {
    public List<Badge> getBadgesNotAssign(Context context,  int debut, int nbResult);
    public List<Badge> getBadgesNotAssignByNumero(Context context,  String numero,int debut, int nbResult);
    public List<Utilisateur> getUtilisateurNotAssign(Context context, int debut,int nbResult);
    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context, String nom,int debut,int nbResult);
    public boolean attribuer(Context context, Utilisateur utilisateur, Badge badge);
}
