/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import metier.entitys.Evenement;
import physique.dataOut.EvenementServiceWeb;
import physique.dataOut.PhysiqueDataOutFactory;

/**
 *
 * @author damien
 */
public class EvenementServiceImpl implements EvenementService {

    private EvenementServiceWeb evenementSrv = PhysiqueDataOutFactory.getEvenementServiceWeb();

    public List<Evenement> getAll(Context context) throws Exception {
        List<Evenement> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.getAll(context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Evenement> getAll(Context context, int index, int nbResultat) throws Exception {
        List<Evenement> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.getAll(context, index, nbResultat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public int count(Context context) throws Exception {
        int b = 0;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.count(context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public Evenement getById(Context context, Long id) throws Exception {
        Evenement b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.getById(context, id);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }
}
