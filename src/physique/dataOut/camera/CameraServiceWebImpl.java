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

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public List<Camera> getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTCameraGetAll.execute(ressource);
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
    public List<Camera> getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return RESTCameraGetAllByRange.execute(ressource, index, nbResultat);
    }

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException {
        return RESTCameraCount.execute(ressource);
    }

    /**
     *
     * @param ressource
     * @param camera
     * @throws MalformedURLException
     * @throws IOException
     */
    public void add(Ressource ressource, Camera camera) throws MalformedURLException, IOException {
        RESTCameraAdd.execute(ressource, camera);
    }

    /**
     *
     * @param ressource
     * @param camera
     * @throws MalformedURLException
     * @throws IOException
     */
    public void remove(Ressource ressource, Camera camera) throws MalformedURLException, IOException {
        RESTCameraRemove.execute(ressource, camera);
    }
    
}
