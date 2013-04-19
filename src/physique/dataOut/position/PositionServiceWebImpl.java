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

    public void add(Ressource ressource, Position position) throws MalformedURLException, IOException{
        RESTPositionAdd.execute(ressource, position);
    }

    public void remove(Ressource ressource, Position position) throws MalformedURLException, IOException{
        RESTPositionRemove.execute(ressource, position);
    }

    public List<Position> getAll(Ressource ressource)throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        return RESTPositionGetAll.execute(ressource);
    }

    public List<Position> getAll(Ressource ressource, int index, int nbResult)throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        return RESTPositionGetByRange.execute(ressource, index, nbResult);
    }

    public int count(Ressource ressource)throws MalformedURLException, IOException {
        return RESTPositionCount.execute(ressource);
    }
}
