/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.borneAcces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.BorneAcces;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeAttribuer;
import physique.dataOut.borneAcces.rest.RESTBorneAccesGetAll;
import physique.dataOut.borneAcces.rest.RESTBorneAccesGetAllByRange;

/**
 *
 * @author damien
 */
public class BorneAccesServiceWebImpl implements BorneAccesServiceWeb{

    public List<BorneAcces> getAll(Ressource ressource)throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTBorneAccesGetAll.execute(ressource);
    }

    public List<BorneAcces> getAll(Ressource ressource, int index, int nbResultat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTBorneAccesGetAllByRange.execute(ressource, index, nbResultat);
    }
    
}
