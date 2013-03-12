/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataIn;

import android.content.Context;

/**
 *
 * @author damien
 */
public class PhysiqueDataInFactory {
    private static RessourcesServiceDataIn ressourceSrv ;

    /**
     * @return the ressourceSrv
     */
    public static RessourcesServiceDataIn getRessourceSrv(Context context) {
        ressourceSrv = new RessourceServiceDataInServiceImpl(context);
        return ressourceSrv;
    }
    
}
