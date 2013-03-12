/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import metier.entitys.Ressource;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;

/**
 *
 * @author damien
 */
public class RessourceServiceImpl implements RessourceService{
    private RessourcesServiceDataIn ressourceSrv;
    public RessourceServiceImpl(Context context){
        this.ressourceSrv = PhysiqueDataInFactory.getRessourceSrv(context);
    }
    public void add(Ressource ressource) throws Exception {
        if(ressource!=null){
            if(ressource instanceof Ressource){
                this.ressourceSrv.add(ressource);
            }else{
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        }else{
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public Ressource getRessource() throws Exception {
        return this.ressourceSrv.getRessource();
    }

    public void update(Ressource ressource) throws Exception {
        if(ressource!=null){
            if(ressource instanceof Ressource){
                this.ressourceSrv.update(ressource);
            }else{
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        }else{
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }
    
}
