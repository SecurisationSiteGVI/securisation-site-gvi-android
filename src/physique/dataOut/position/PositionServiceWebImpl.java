/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.position;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Position;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.position.rest.RESTPositionAdd;
import physique.dataOut.position.rest.RESTPositionCount;
import physique.dataOut.position.rest.RESTPositionGetAll;
import physique.dataOut.position.rest.RESTPositionGetByRange;
import physique.dataOut.position.rest.RESTPositionRemove;

/**
 *
 * @author damien
 */
public class PositionServiceWebImpl implements PositionServiceWeb{

    /**
     *
     * @param ressource
     * @param position
     * @throws MalformedURLException
     * @throws IOException
     */
    public void add(Ressource ressource, Position position) throws MalformedURLException, IOException{
        RESTPositionAdd.execute(ressource, position);
    }

    /**
     *
     * @param ressource
     * @param position
     * @throws MalformedURLException
     * @throws IOException
     */
    public void remove(Ressource ressource, Position position) throws MalformedURLException, IOException{
        RESTPositionRemove.execute(ressource, position);
    }

    /**
     *
     * @param ressource
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public List<Position> getAll(Ressource ressource)throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        return RESTPositionGetAll.execute(ressource);
    }

    /**
     *
     * @param ressource
     * @param index
     * @param nbResult
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public List<Position> getAll(Ressource ressource, int index, int nbResult)throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        return RESTPositionGetByRange.execute(ressource, index, nbResult);
    }

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource)throws MalformedURLException, IOException {
        return RESTPositionCount.execute(ressource);
    }
}
