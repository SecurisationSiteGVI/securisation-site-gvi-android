/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.camera;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Camera;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.camera.rest.RESTCameraAdd;
import physique.dataOut.camera.rest.RESTCameraCount;
import physique.dataOut.camera.rest.RESTCameraGetAll;
import physique.dataOut.camera.rest.RESTCameraGetAllByRange;
import physique.dataOut.camera.rest.RESTCameraRemove;

/**
 *
 * @author damien
 */
public class CameraServiceWebImpl implements CameraServiceWeb{

    public List<Camera> getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTCameraGetAll.execute(ressource);
    }

    public List<Camera> getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTCameraGetAllByRange.execute(ressource, index, nbResultat);
    }

    public int count(Ressource ressource) throws MalformedURLException, IOException {
        return RESTCameraCount.execute(ressource);
    }

    public void add(Ressource ressource, Camera camera) throws MalformedURLException, IOException {
        RESTCameraAdd.execute(ressource, camera);
    }

    public void remove(Ressource ressource, Camera camera) throws MalformedURLException, IOException {
        RESTCameraRemove.execute(ressource, camera);
    }
    
}
