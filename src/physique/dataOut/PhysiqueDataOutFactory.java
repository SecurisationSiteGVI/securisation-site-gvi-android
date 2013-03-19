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

    private static UtilisateurServiceWeb personneClientSrv = new UtilisateurServiceWebImpl();

    public static UtilisateurServiceWeb getPersonneClientServiceWeb() {
        return personneClientSrv;
    }
}
