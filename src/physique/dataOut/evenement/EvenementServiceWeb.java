/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement;

import android.content.Context;
import java.util.List;
import metier.entitys.Evenement;

/**
 *
 * @author damien
 */
public interface EvenementServiceWeb {

    public List<Evenement> getAll(Context context) throws Exception;

    public List<Evenement> getAll(Context context, int index, int nbResultat) throws Exception;

    public int count(Context c) throws Exception;

    public Evenement getById(Context context, Long id) throws Exception;
}
