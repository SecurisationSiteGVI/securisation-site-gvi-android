/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import physique.dataOut.attributionUtilisateurBadge.AttributionUtilisateurBadgeServiceWeb;
import physique.dataOut.attributionUtilisateurBadge.AttributionUtilisateurBadgeServiceWebImpl;
import physique.dataOut.utilisateur.UtilisateurServiceWeb;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;
import physique.dataOut.evenement.EvenementServiceWeb;
import physique.dataOut.evenement.EvenementServiceWebImpl;
import physique.dataOut.badge.BadgeServiceWeb;
import physique.dataOut.badge.BadgeServiceWebImpl;
import physique.dataOut.secteur.SecteurServiceWeb;
import physique.dataOut.secteur.SecteurServiceWebImpl;

/**
 *
 * @author damien
 */
public class PhysiqueDataOutFactory {

    private static UtilisateurServiceWeb personneSrv;
    private static EvenementServiceWeb evenementSrv;
    private static BadgeServiceWeb badgeSrv;
    private static AttributionUtilisateurBadgeServiceWeb attributionUtilisateurBadgeSrv;
    private static SecteurServiceWeb secteurSrv;
    public static UtilisateurServiceWeb getPersonneClientServiceWeb() {
        if (personneSrv == null) {
            personneSrv = new UtilisateurServiceWebImpl();
        }
        return personneSrv;
    }

    public static SecteurServiceWeb getSecteurServiceWeb() {
        if (secteurSrv == null) {
            secteurSrv = new SecteurServiceWebImpl();
        }
        return secteurSrv;
    }
    public static EvenementServiceWeb getEvenementServiceWeb() {
        if (evenementSrv == null) {
            evenementSrv = new EvenementServiceWebImpl();
        }
        return evenementSrv;
    }

    public static BadgeServiceWeb getBadgeServiceWeb() {
        if (badgeSrv == null) {
            badgeSrv = new BadgeServiceWebImpl();
        }
        return badgeSrv;
    }
    public static AttributionUtilisateurBadgeServiceWeb getAttributionUtilisateurBadgeSrv() {
        if (attributionUtilisateurBadgeSrv == null) {
            attributionUtilisateurBadgeSrv = new AttributionUtilisateurBadgeServiceWebImpl();
        }
        return attributionUtilisateurBadgeSrv;
    }
}
