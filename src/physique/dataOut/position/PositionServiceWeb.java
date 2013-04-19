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

/**
 *
 * @author damien
 */
public interface PositionServiceWeb {
    public void add(Ressource ressource, Position position)throws MalformedURLException, IOException;
    public void remove(Ressource ressource,Position position)throws MalformedURLException, IOException;
    public List<Position> getAll(Ressource ressource)throws SAXException, ParserConfigurationException, MalformedURLException, IOException;
    public List<Position> getAll(Ressource ressource,int index, int nbResult)throws SAXException, ParserConfigurationException, MalformedURLException, IOException;
    public int count(Ressource ressource)throws MalformedURLException, IOException;
    
}
