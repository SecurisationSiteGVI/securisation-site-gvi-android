/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import physique.dataOut.AttributionSecteurCamera.AttributionSecteurCameraServiceWeb;
import physique.dataOut.AttributionSecteurCamera.AttributionSecteurCameraServiceWebImpl;
import physique.dataOut.attributionSecteurBorneAcces.AttributionSecteurBorneAccesServiceWeb;
import physique.dataOut.attributionSecteurBorneAcces.AttributionSecteurBorneAccesServiceWebImpl;
import physique.dataOut.attributionSecteurDetecteurIntrusion.AttributionSecteurDetecteurIntrusionServiceWeb;
import physique.dataOut.attributionSecteurDetecteurIntrusion.AttributionSecteurDetecteurIntrusionServiceWebImpl;
import physique.dataOut.attributionUtilisateurBadge.AttributionUtilisateurBadgeServiceWeb;
import physique.dataOut.attributionUtilisateurBadge.AttributionUtilisateurBadgeServiceWebImpl;
import physique.dataOut.authorisationAcces.AuthorisationAccesServiceWeb;
import physique.dataOut.authorisationAcces.AuthorisationAccesServiceWebImpl;
import physique.dataOut.utilisateur.UtilisateurServiceWeb;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;
import physique.dataOut.evenement.EvenementServiceWeb;
import physique.dataOut.evenement.EvenementServiceWebImpl;
import physique.dataOut.badge.BadgeServiceWeb;
import physique.dataOut.badge.BadgeServiceWebImpl;
import physique.dataOut.borneAcces.BorneAccesServiceWeb;
import physique.dataOut.borneAcces.BorneAccesServiceWebImpl;
import physique.dataOut.camera.CameraServiceWeb;
import physique.dataOut.camera.CameraServiceWebImpl;
import physique.dataOut.detecteurIntrusion.DetecteurIntrusionServiceWeb;
import physique.dataOut.detecteurIntrusion.DetecteurIntrusionServiceWebImpl;
import physique.dataOut.numeroPredefini.NumeroPredefiniServiceWeb;
import physique.dataOut.numeroPredefini.NumeroPredefiniServiceWebImpl;
import physique.dataOut.position.PositionServiceWeb;
import physique.dataOut.position.PositionServiceWebImpl;
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
    private static AttributionSecteurBorneAccesServiceWeb attributionSecteurBorneAccesSrv;
    private static AttributionSecteurCameraServiceWeb attributionSecteurCameraSrv;
    private static AttributionSecteurDetecteurIntrusionServiceWeb attributionSecteurDetecteurIntrusionSrv;
    private static BorneAccesServiceWeb borneAccesSrv;
    private static CameraServiceWeb cameraSrv;
    private static DetecteurIntrusionServiceWeb detecteurIntrusionSrv;
    private static PositionServiceWeb positionSrv;
    private static NumeroPredefiniServiceWeb numeroPredefiniSrv;
    private static AuthorisationAccesServiceWeb authorisationAccesSrv;

    public static AuthorisationAccesServiceWeb getAuthorisationAccesServiceWeb() {
        if (authorisationAccesSrv == null) {
            authorisationAccesSrv = new AuthorisationAccesServiceWebImpl();
        }
        return authorisationAccesSrv;
    }

    public static NumeroPredefiniServiceWeb getNumeroPredefiniServiceWeb() {
        if (numeroPredefiniSrv == null) {
            numeroPredefiniSrv = new NumeroPredefiniServiceWebImpl();
        }
        return numeroPredefiniSrv;
    }

    /**
     *
     * @return
     */
    public static PositionServiceWeb getPositionServiceWeb() {
        if (positionSrv == null) {
            positionSrv = new PositionServiceWebImpl();
        }
        return positionSrv;
    }

    /**
     *
     * @return
     */
    public static CameraServiceWeb getCameraServiceWeb() {
        if (cameraSrv == null) {
            cameraSrv = new CameraServiceWebImpl();
        }
        return cameraSrv;
    }

    /**
     *
     * @return
     */
    public static DetecteurIntrusionServiceWeb getDetecteurIntrusionServiceWeb() {
        if (detecteurIntrusionSrv == null) {
            detecteurIntrusionSrv = new DetecteurIntrusionServiceWebImpl();
        }
        return detecteurIntrusionSrv;
    }

    /**
     *
     * @return
     */
    public static BorneAccesServiceWeb getBorneAccesServiceWeb() {
        if (borneAccesSrv == null) {
            borneAccesSrv = new BorneAccesServiceWebImpl();
        }
        return borneAccesSrv;
    }

    /**
     *
     * @return
     */
    public static UtilisateurServiceWeb getPersonneClientServiceWeb() {
        if (personneSrv == null) {
            personneSrv = new UtilisateurServiceWebImpl();
        }
        return personneSrv;
    }

    /**
     *
     * @return
     */
    public static SecteurServiceWeb getSecteurServiceWeb() {
        if (secteurSrv == null) {
            secteurSrv = new SecteurServiceWebImpl();
        }
        return secteurSrv;
    }

    /**
     *
     * @return
     */
    public static EvenementServiceWeb getEvenementServiceWeb() {
        if (evenementSrv == null) {
            evenementSrv = new EvenementServiceWebImpl();
        }
        return evenementSrv;
    }

    /**
     *
     * @return
     */
    public static BadgeServiceWeb getBadgeServiceWeb() {
        if (badgeSrv == null) {
            badgeSrv = new BadgeServiceWebImpl();
        }
        return badgeSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionUtilisateurBadgeServiceWeb getAttributionUtilisateurBadgeSrv() {
        if (attributionUtilisateurBadgeSrv == null) {
            attributionUtilisateurBadgeSrv = new AttributionUtilisateurBadgeServiceWebImpl();
        }
        return attributionUtilisateurBadgeSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionSecteurBorneAccesServiceWeb getAttributionSecteurBorneAccesSrv() {
        if (attributionSecteurBorneAccesSrv == null) {
            attributionSecteurBorneAccesSrv = new AttributionSecteurBorneAccesServiceWebImpl();
        }
        return attributionSecteurBorneAccesSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionSecteurCameraServiceWeb getAttributionSecteurCameraSrv() {
        if (attributionSecteurCameraSrv == null) {
            attributionSecteurCameraSrv = new AttributionSecteurCameraServiceWebImpl();
        }
        return attributionSecteurCameraSrv;
    }

    /**
     *
     * @return
     */
    public static AttributionSecteurDetecteurIntrusionServiceWeb getAttributionSecteurDetecteurIntrusionSrv() {
        if (attributionSecteurDetecteurIntrusionSrv == null) {
            attributionSecteurDetecteurIntrusionSrv = new AttributionSecteurDetecteurIntrusionServiceWebImpl();
        }
        return attributionSecteurDetecteurIntrusionSrv;
    }
}
