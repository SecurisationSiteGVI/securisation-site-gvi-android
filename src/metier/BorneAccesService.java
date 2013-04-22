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
import metier.entitys.BorneAcces;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface BorneAccesService {
    public List<BorneAcces> getAll(Context context)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception;

    public List<BorneAcces> getAll(Context context, int index, int nbResultat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception ;
    public int count(Context contex) throws MalformedURLException, IOException,Exception;

    public void add(Context contex, BorneAcces borneAcces) throws MalformedURLException, IOException,Exception;

    public void remove(Context contex, BorneAcces borneAcces) throws MalformedURLException, IOException,Exception;

}
