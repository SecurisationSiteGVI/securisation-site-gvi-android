package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Position;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.position.PositionServiceWeb;

/**
 *
 * @author damien
 */
public class PositionServiceImpl implements PositionService {

    private PositionServiceWeb positionSrv = PhysiqueDataOutFactory.getPositionServiceWeb();

    /**
     *
     * @param context
     * @param position
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void add(Context context, Position position) throws MalformedURLException, IOException, Exception {
        if ((context != null) && (position != null)) {
            if ((context instanceof Context) && (position instanceof Position)) {
                this.positionSrv.add(this.getRessource(context), position);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    /**
     *
     * @param context
     * @param position
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void remove(Context context, Position position) throws MalformedURLException, IOException, Exception {
        if ((context != null) && (position != null)) {
            if ((context instanceof Context) && (position instanceof Position)) {
                this.positionSrv.remove(this.getRessource(context), position);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

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
    public List<Position> getAll(Context context) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception {
        List<Position> positions = null;
        if ((context != null)) {
            if ((context instanceof Context)) {
                positions = this.positionSrv.getAll(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return positions;
    }

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
    public List<Position> getAll(Context context, int index, int nbResult) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception {
        List<Position> positions = null;
        if ((context != null)) {
            if ((context instanceof Context)) {
                positions = this.positionSrv.getAll(this.getRessource(context), index, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return positions;
    }

    /**
     *
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public int count(Context context) throws MalformedURLException, IOException, Exception {
        Integer ret = null;
        if ((context != null)) {
            if ((context instanceof Context)) {
                ret = this.positionSrv.count(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
}
