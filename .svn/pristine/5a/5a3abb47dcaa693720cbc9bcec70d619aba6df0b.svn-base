/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.detecteurIntrusion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusioniAdd;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionCount;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionGetAll;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionGetAllByRange;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionRemove;

/**
 *
 * @author damien
 */
public class DetecteurIntrusionServiceWebImpl implements DetecteurIntrusionServiceWeb{

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public List<DetecteurIntrusion> getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTDetecteurIntrusionGetAll.execute(ressource);
    }

    /**
     *
     * @param ressource
     * @param index
     * @param nbResultat
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public List<DetecteurIntrusion> getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTDetecteurIntrusionGetAllByRange.execute(ressource, index, nbResultat);
    }

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException {
        return RESTDetecteurIntrusionCount.execute(ressource);
    }

    /**
     *
     * @param ressource
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     */
    public void add(Ressource ressource, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException {
        RESTDetecteurIntrusioniAdd.execute(ressource, detecteurIntrusion);
    }

    /**
     *
     * @param ressource
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     */
    public void remove(Ressource ressource, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException {
        RESTDetecteurIntrusionRemove.execute(ressource, detecteurIntrusion);
    }
    
}
