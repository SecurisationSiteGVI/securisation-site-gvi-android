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
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionAdd;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionCount;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionGetAll;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionGetAllByRange;
import physique.dataOut.detecteurIntrusion.rest.RESTDetecteurIntrusionRemove;

/**
 *
 * @author damien
 */
public class DetecteurIntrusionServiceWebImpl implements DetecteurIntrusionServiceWeb{

    public List<DetecteurIntrusion> getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTDetecteurIntrusionGetAll.execute(ressource);
    }

    public List<DetecteurIntrusion> getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTDetecteurIntrusionGetAllByRange.execute(ressource, index, nbResultat);
    }

    public int count(Ressource ressource) throws MalformedURLException, IOException {
        return RESTDetecteurIntrusionCount.execute(ressource);
    }

    public void add(Ressource ressource, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException {
        RESTDetecteurIntrusionAdd.execute(ressource, detecteurIntrusion);
    }

    public void remove(Ressource ressource, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException {
        RESTDetecteurIntrusionRemove.execute(ressource, detecteurIntrusion);
    }
    
}
