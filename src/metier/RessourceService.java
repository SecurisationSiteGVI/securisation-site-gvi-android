/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public interface RessourceService {

    public void add(Ressource ressource) throws Exception;

    public Ressource getRessource() throws Exception;

    public void update(Ressource ressource) throws Exception;
}
