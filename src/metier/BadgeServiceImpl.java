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
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.badge.BadgeServiceWeb;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class BadgeServiceImpl implements BadgeService {

    private RessourcesServiceDataIn ressourcesSrv;
    private BadgeServiceWeb badgeSrv = PhysiqueDataOutFactory.getBadgeServiceWeb();

    public List<Badge> getAll(Context context) throws Exception {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = badgeSrv.getAll(context, ressource);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Badge> getAll(Context context, int index, int nbResultat) throws Exception {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                b = badgeSrv.getAll(context, ressource, index, nbResultat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public int count(Context context) throws Exception {
        Integer count = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                count = badgeSrv.count(context, ressource);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return count;
    }

    public boolean remove(Context c, Badge badge) throws Exception {
        Boolean ret = null;
        if (c != null) {
            if (c instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                ret = badgeSrv.remove(c, ressource, badge);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    public boolean add(Context context, Badge badge) throws Exception {
        Boolean ret = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                try {
                    ressource = this.ressourcesSrv.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                ret = badgeSrv.add(context, ressource, badge);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }
}
