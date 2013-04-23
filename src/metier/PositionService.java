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
import metier.entitys.Position;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface PositionService {
        /**
     *
     * @param context
     * @param position
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void add(Context context, Position position)throws MalformedURLException, IOException,Exception;
    /**
     *
     * @param context
     * @param position
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void remove(Context context,Position position)throws MalformedURLException, IOException,Exception;
    /**
     *
     * @param context
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public List<Position> getAll(Context context)throws SAXException, ParserConfigurationException, MalformedURLException, IOException,Exception;
    /**
     *
     * @param context
     * @param index
     * @param nbResult
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public List<Position> getAll(Context context,int index, int nbResult)throws SAXException, ParserConfigurationException, MalformedURLException, IOException,Exception;
    /**
     *
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public int count(Context context)throws MalformedURLException, IOException,Exception;
}
