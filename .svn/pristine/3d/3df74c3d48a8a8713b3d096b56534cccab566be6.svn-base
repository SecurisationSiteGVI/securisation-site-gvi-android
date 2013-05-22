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
    private static UtilisateurService utilisateurSrv = new UtilisateurServiceImpl();
    private static EvenementService evenementSrv = new EvenementServiceImpl();
    
    public static EvenementService getEvenementSrv() {
        return evenementSrv;
    }
    public static RessourceService getRessourceSrv(Context context) {
        MetierFactory.ressourceSrv = new RessourceServiceImpl(context);
        return ressourceSrv;
    }

    public static UtilisateurService getUtilisateurSrv() {
        return utilisateurSrv;
    }
}
