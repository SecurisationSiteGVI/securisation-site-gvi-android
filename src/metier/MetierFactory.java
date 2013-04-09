/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;

/**
 *
 * @author damien
 */
public class MetierFactory {

    private static RessourceService ressourceSrv;
    private static UtilisateurService utilisateurSrv;
    private static EvenementService evenementSrv;
    private static BadgeService badgeSrv;
    private static AttributionUtilisateurBadgeService attributionUtilisateurBadgeSrv;
    private static SecteurService secteurSrv;
    private static AttributionSecteurCameraService attributionSecteurCameraSrv;
    public static BadgeService getBadgeSrv() {
        if (badgeSrv == null) {
            badgeSrv = new BadgeServiceImpl();
        }
        return badgeSrv;
    }
    public static SecteurService getSecteurServ() {
        if (secteurSrv == null) {
            secteurSrv = new SecteurServiceImpl();
        }
        return secteurSrv;
    }

    public static AttributionUtilisateurBadgeService getAttributionUtilisateurBadgeService() {
        if (attributionUtilisateurBadgeSrv == null) {
            attributionUtilisateurBadgeSrv = new AttributionUtilisateurBadgeServiceImpl();
        }
        return attributionUtilisateurBadgeSrv;
    }

    public static EvenementService getEvenementSrv() {
        if (evenementSrv == null) {
            evenementSrv = new EvenementServiceImpl();
        }
        return evenementSrv;
    }

    public static RessourceService getRessourceSrv(Context context) {
        MetierFactory.ressourceSrv = new RessourceServiceImpl(context);
        return ressourceSrv;
    }

    public static UtilisateurService getUtilisateurSrv() {
        if (utilisateurSrv == null) {
            utilisateurSrv = new UtilisateurServiceImpl();
        }
        return utilisateurSrv;
    }

    public static AttributionSecteurCameraService getAttributionSecteurCameraSrv() {
        if (attributionSecteurCameraSrv == null) {
            attributionSecteurCameraSrv = new AttributionSecteurCameraServiceImpl();
        }
        return attributionSecteurCameraSrv;
    }
}
