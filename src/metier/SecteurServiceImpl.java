/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.secteur.SecteurServiceWeb;

/**
 *
 * @author damien
 */
public class SecteurServiceImpl implements SecteurService {

    private RessourcesServiceDataIn ressourcesSrv;
    private SecteurServiceWeb secteurSrv = PhysiqueDataOutFactory.getSecteurServiceWeb();

    public boolean ajouter(Context context, Secteur secteur) throws Exception {
        Boolean b = null;
        if ((context != null)&&(secteur!=null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                ressource = this.ressourcesSrv.getRessource();
                b = this.secteurSrv.ajouter(ressource, secteur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Secteur> getAll(Context context, int index, int nbResutltat) throws Exception {
        List<Secteur> b = null;
        if (context != null) {
            if (context instanceof Context) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                ressource = this.ressourcesSrv.getRessource();
                b = this.secteurSrv.getAll(ressource, index, nbResutltat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean remove(Context context, Secteur secteur) throws Exception {
        Boolean b = null;
        if ((context != null)&&(secteur!=null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)) {
                this.ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(context);
                Ressource ressource = null;
                ressource = this.ressourcesSrv.getRessource();
                b = this.secteurSrv.remove( ressource, secteur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }
}
