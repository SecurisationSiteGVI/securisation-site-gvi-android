/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import java.util.List;

/**
 *
 * @author damien
 */
public class PhysiqueDataOutFactory {

    private static UtilisateurServiceWeb personneSrv = new UtilisateurServiceWebImpl();
    private static EvenementServiceWeb evenementSrv = new EvenementServiceWebImpl();

    public static UtilisateurServiceWeb getPersonneClientServiceWeb() {
        return personneSrv;
    }

    public static EvenementServiceWeb getEvenementServiceWeb() {
        return evenementSrv;
    }
}
