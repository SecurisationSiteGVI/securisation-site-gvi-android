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
    
    public static BadgeService getBadgeSrv() {
        if(badgeSrv==null){
            badgeSrv = new BadgeServiceImpl();
        }
        return badgeSrv;
    }
    public static EvenementService getEvenementSrv() {
        if(evenementSrv==null){
            evenementSrv = new EvenementServiceImpl();
        }
        return evenementSrv;
    }
    public static RessourceService getRessourceSrv(Context context) {
        MetierFactory.ressourceSrv = new RessourceServiceImpl(context);
        return ressourceSrv;
    }

    public static UtilisateurService getUtilisateurSrv() {
        if(utilisateurSrv==null){
            utilisateurSrv = new UtilisateurServiceImpl();
        }
        return utilisateurSrv;
    }
}
