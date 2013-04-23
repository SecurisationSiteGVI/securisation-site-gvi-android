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

    public static NumeroPredefiniService getNumeroPredefiniService() {
        if (numeroPredefiniSrv == null) {
            numeroPredefiniSrv = new NumeroPredefiniServiceImpl();
        }
        return numeroPredefiniSrv;
    }
    
    public static AttributionSecteurDetecteurIntrusionService getAttributionSecteurDetecteurIntrusionService() {
        if (attributionSecteurDetecteurIntrusionSrv == null) {
            attributionSecteurDetecteurIntrusionSrv = new AttributionSecteurDetecteurIntrusionServiceImpl();
        }
        return attributionSecteurDetecteurIntrusionSrv;
    }

    public static PositionService getPositionService() {
        if (positionSrv == null) {
            positionSrv = new PositionServiceImpl();
        }
        return positionSrv;
    }

    public static AttributionSecteurBorneAccesService getAttributionSecteurBorneAccesService() {
        if (attributionSecteurBorneAccesSrv == null) {
            attributionSecteurBorneAccesSrv = new AttributionSecteurBorneAccesServiceImpl();
        }
        return attributionSecteurBorneAccesSrv;
    }

    public static DetecteurIntrusionService getDetecteurIntrusionService() {
        if (detecteurIntrusionSrv == null) {
            detecteurIntrusionSrv = new DetecteurIntrusionServiceImpl();
        }
        return detecteurIntrusionSrv;
    }

    public static CameraService getCameraService() {
        if (cameraSrv == null) {
            cameraSrv = new CameraServiceImpl();
        }
        return cameraSrv;
    }

    public static BorneAccesService getBorneAccesService() {
        if (borneAccesSrv == null) {
            borneAccesSrv = new BorneAccesServiceImpl();
        }
        return borneAccesSrv;
    }

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
