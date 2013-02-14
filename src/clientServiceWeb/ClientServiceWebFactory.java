/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientServiceWeb;

import java.util.List;

/**
 *
 * @author damien
 */
public class ClientServiceWebFactory {

    private static UtilisateurServiceWeb personneClientSrv = new UtilisateurServiceWebImpl();
    
    public static UtilisateurServiceWeb getPersonneClientServiceWeb(){
        return personneClientSrv;
    }
}
