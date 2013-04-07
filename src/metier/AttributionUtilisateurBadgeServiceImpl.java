/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.attributionUtilisateurBadge.AttributionUtilisateurBadgeServiceWeb;

/**
 *
 * @author damien
 */
public class AttributionUtilisateurBadgeServiceImpl implements AttributionUtilisateurBadgeService {

    private RessourcesServiceDataIn ressourcesSrv;
    private AttributionUtilisateurBadgeServiceWeb attributionUtilisateurBadgeSrv = PhysiqueDataOutFactory.getAttributionUtilisateurBadgeSrv();
    public List<Badge> getBadgesNotAssign(Context context,  int debut, int nbResult) {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(AttributionUtilisateurBadgeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = this.attributionUtilisateurBadgeSrv.getBadgesNotAssign(context, ressource, debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Badge> getBadgesNotAssignByNumero(Context context, String numero, int debut, int nbResult) {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(AttributionUtilisateurBadgeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = this.attributionUtilisateurBadgeSrv.getBadgesNotAssignByNumero(context, ressource, numero, debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Utilisateur> getUtilisateurNotAssign(Context context,  int debut, int nbResult) {
        List<Utilisateur> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(AttributionUtilisateurBadgeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = this.attributionUtilisateurBadgeSrv.getUtilisateurNotAssign(context, ressource, debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context,  String nom, int debut, int nbResult) {
        List<Utilisateur> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(AttributionUtilisateurBadgeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = this.attributionUtilisateurBadgeSrv.getUtilisateurNotAssignByNom(context, ressource, nom, debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean attribuer(Context context, Utilisateur utilisateur, Badge badge) {
        Boolean b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(AttributionUtilisateurBadgeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = this.attributionUtilisateurBadgeSrv.attribuer(context, ressource, utilisateur, badge);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }
}
