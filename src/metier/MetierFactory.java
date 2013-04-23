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
    private static AttributionSecteurBorneAccesService attributionSecteurBorneAccesSrv;
    private static BorneAccesService borneAccesSrv;
    private static CameraService cameraSrv;
    private static DetecteurIntrusionService detecteurIntrusionSrv;
    private static AttributionSecteurDetecteurIntrusionService attributionSecteurDetecteurIntrusionSrv;
    private static PositionService positionSrv;
    private static NumeroPredefiniService numeroPredefiniSrv;

    /**
     *
     * @return
     */
    public static NumeroPredefiniService getNumeroPredefiniService() {
        if (numeroPredefiniSrv == null) {
            numeroPredefiniSrv = new NumeroPredefiniServiceImpl();
        }
        return numeroPredefiniSrv;
    }
    
    /**
     *
     * @return
     */
    public static AttributionSecteurDetecteurIntrusionService getAttributionSecteurDetecteurIntrusionService() {
        if (attributionSecteurDetecteurIntrusionSrv == null) {
            attributionSecteurDetecteurIntrusionSrv = new AttributionSecteurDetecteurIntrusionServiceImpl();
        }
        return attributionSecteurDetecteurIntrusionSrv;
    }

    /**
     *
     * @return
     */
    public static PositionService getPositionService() {
        if (positionSrv == null) {
            positionSrv = new PositionServiceImpl();
        }
        return positionSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionSecteurBorneAccesService getAttributionSecteurBorneAccesService() {
        if (attributionSecteurBorneAccesSrv == null) {
            attributionSecteurBorneAccesSrv = new AttributionSecteurBorneAccesServiceImpl();
        }
        return attributionSecteurBorneAccesSrv;
    }

    /**
     *
     * @return
     */
    public static DetecteurIntrusionService getDetecteurIntrusionService() {
        if (detecteurIntrusionSrv == null) {
            detecteurIntrusionSrv = new DetecteurIntrusionServiceImpl();
        }
        return detecteurIntrusionSrv;
    }

    /**
     *
     * @return
     */
    public static CameraService getCameraService() {
        if (cameraSrv == null) {
            cameraSrv = new CameraServiceImpl();
        }
        return cameraSrv;
    }

    /**
     *
     * @return
     */
    public static BorneAccesService getBorneAccesService() {
        if (borneAccesSrv == null) {
            borneAccesSrv = new BorneAccesServiceImpl();
        }
        return borneAccesSrv;
    }

    /**
     *
     * @return
     */
    public static BadgeService getBadgeSrv() {
        if (badgeSrv == null) {
            badgeSrv = new BadgeServiceImpl();
        }
        return badgeSrv;
    }

    /**
     *
     * @return
     */
    public static SecteurService getSecteurServ() {
        if (secteurSrv == null) {
            secteurSrv = new SecteurServiceImpl();
        }
        return secteurSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionUtilisateurBadgeService getAttributionUtilisateurBadgeService() {
        if (attributionUtilisateurBadgeSrv == null) {
            attributionUtilisateurBadgeSrv = new AttributionUtilisateurBadgeServiceImpl();
        }
        return attributionUtilisateurBadgeSrv;
    }

    /**
     *
     * @return
     */
    public static EvenementService getEvenementSrv() {
        if (evenementSrv == null) {
            evenementSrv = new EvenementServiceImpl();
        }
        return evenementSrv;
    }

    /**
     *
     * @param context
     * @return
     */
    public static RessourceService getRessourceSrv(Context context) {
        MetierFactory.ressourceSrv = new RessourceServiceImpl(context);
        return ressourceSrv;
    }

    /**
     *
     * @return
     */
    public static UtilisateurService getUtilisateurSrv() {
        if (utilisateurSrv == null) {
            utilisateurSrv = new UtilisateurServiceImpl();
        }
        return utilisateurSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionSecteurCameraService getAttributionSecteurCameraSrv() {
        if (attributionSecteurCameraSrv == null) {
            attributionSecteurCameraSrv = new AttributionSecteurCameraServiceImpl();
        }
        return attributionSecteurCameraSrv;
    }
}
