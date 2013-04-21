/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Camera;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface CameraService {
    public List<Camera> getAll(Context context)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception;

    public List<Camera> getAll(Context context, int index, int nbResultat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception ;
    public int count(Context contex) throws MalformedURLException, IOException,Exception;

    public void add(Context contex, Camera camera) throws MalformedURLException, IOException,Exception;

    public void remove(Context contex, Camera camera) throws MalformedURLException, IOException,Exception;
}
