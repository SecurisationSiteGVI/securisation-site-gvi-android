/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Evenement;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.evenement.rest.RESTEvenementCount;
import physique.dataOut.evenement.rest.RESTEvenementGetAll;
import physique.dataOut.evenement.rest.RESTEvenementGetAllByRange;
import physique.dataOut.evenement.rest.RESTEvenementGetByID;

/**
 *
 * @author damien
 */
public class EvenementServiceWebImpl implements EvenementServiceWeb {

    /**
     *
     * @param ressource
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Evenement> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException {
        List<Evenement> retour = (List<Evenement>) RESTEvenementGetAll.execute(ressource);
        return retour;
    }

    /**
     *
     * @param ressource
     * @param index
     * @param nbResultat
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Evenement> getAll(Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException {
        List<Evenement> retour = (List<Evenement>) RESTEvenementGetAllByRange.execute(ressource, index, nbResultat);
        return retour;
    }

    /**
     *
     * @param ressource
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    public int count(Ressource ressource) throws IOException, MalformedURLException {
        Integer count = (Integer) RESTEvenementCount.execute(ressource);
        return count;
    }

    /**
     *
     * @param ressource
     * @param id
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParseException
     * @throws ParserConfigurationException
     */
    public Evenement getById(Ressource ressource, Long id) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException {
        Evenement retour = (Evenement)RESTEvenementGetByID.execute(ressource, id);
        return retour;
    }
}