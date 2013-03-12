/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataIn;

import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public interface RessourcesServiceDataIn {
    public void add(Ressource ressource) throws Exception;
    public Ressource getRessource() throws Exception;
    public void update(Ressource ressource) throws Exception;
}
