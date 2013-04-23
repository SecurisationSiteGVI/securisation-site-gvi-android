/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.numeroPredefini;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.NumeroPredefinis;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.numeroPredefini.rest.RESTNumeroPredefiniAdd;
import physique.dataOut.numeroPredefini.rest.RESTNumeroPredefiniCount;
import physique.dataOut.numeroPredefini.rest.RESTNumeroPredefiniGetAllByRange;
import physique.dataOut.numeroPredefini.rest.RESTNumeroPredefiniRemove;

/**
 *
 * @author damien
 */
public class NumeroPredefiniServiceWebImpl implements NumeroPredefiniServiceWeb{

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public NumeroPredefinis getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        return null;
    }

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException {
        return RESTNumeroPredefiniCount.execute(ressource);
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
    public NumeroPredefinis getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
       return RESTNumeroPredefiniGetAllByRange.execute(ressource, index, nbResultat);
    }

    /**
     *
     * @param ressource
     * @param numero
     * @throws MalformedURLException
     * @throws IOException
     */
    public void add(Ressource ressource, String numero) throws MalformedURLException, IOException {
        RESTNumeroPredefiniAdd.execute(ressource, numero);
    }

    /**
     *
     * @param ressource
     * @param numero
     * @throws MalformedURLException
     * @throws IOException
     */
    public void remove(Ressource ressource, String numero) throws MalformedURLException, IOException {
        RESTNumeroPredefiniRemove.execute(ressource, numero);
    }


    
}
