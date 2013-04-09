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
import physique.dataOut.attributionSecteurBorneAcces.rest.RESTAttributionSecteurBorneAccesAttribuer;
import physique.dataOut.attributionSecteurBorneAcces.rest.RESTAttributionSecteurBorneAccesDesattribuer;

/**
 *
 * @author damien
 */
public class AttributionSecteurBorneAccesServiceWebImpl implements AttributionSecteurBorneAccesServiceWeb{

    public void attribuer(Ressource ressource, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException{
        RESTAttributionSecteurBorneAccesAttribuer.execute(ressource, secteur, borneAcces);
    }

    public void desattribuer(Ressource ressource, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException {
        RESTAttributionSecteurBorneAccesDesattribuer.execute(ressource, secteur, borneAcces);
    }
    
}
