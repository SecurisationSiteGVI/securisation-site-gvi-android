/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.secteur;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;
import physique.dataOut.secteur.rest.RESTSecteurAjout;
import physique.dataOut.secteur.rest.RESTSecteurCount;
import physique.dataOut.secteur.rest.RESTSecteurGetAllByRange;
import physique.dataOut.secteur.rest.RESTSecteurRemove;

/**
 *
 * @author damien
 */
public class SecteurServiceWebImpl implements SecteurServiceWeb{

    public boolean ajouter( Ressource ressource, Secteur secteur) throws IOException  {
        RESTSecteurAjout.execute(ressource, secteur);
        return true;
    }

    public List<Secteur> getAll( Ressource ressource, int index, int nbResutltat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException  {
        return RESTSecteurGetAllByRange.execute(ressource, index, nbResutltat);
    }

    public boolean remove( Ressource ressource, Secteur secteur) throws MalformedURLException, IOException  {
        return RESTSecteurRemove.execute(ressource, secteur);
    }

    public int count(Ressource ressource) throws MalformedURLException, IOException {
        return RESTSecteurCount.execute(ressource);
    }
    
}
