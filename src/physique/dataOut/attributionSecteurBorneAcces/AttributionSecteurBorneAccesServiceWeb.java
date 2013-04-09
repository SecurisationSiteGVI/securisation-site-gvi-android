/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionSecteurBorneAcces;

import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.BorneAcces;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public interface AttributionSecteurBorneAccesServiceWeb {
    public void attribuer(Ressource ressource, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException;
    public void desattribuer(Ressource ressource, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException;
}
