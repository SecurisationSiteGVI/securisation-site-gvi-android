/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AttributionSecteurBorneAcces;
import metier.entitys.BorneAcces;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.attributionSecteurBorneAcces.AttributionSecteurBorneAccesServiceWeb;

/**
 *
 * @author damien
 */
public class AttributionSecteurBorneAccesServiceImpl implements AttributionSecteurBorneAccesService {

    private AttributionSecteurBorneAccesServiceWeb attributionSecteurBorneAccesSrv = PhysiqueDataOutFactory.getAttributionSecteurBorneAccesSrv();

    public void attribuer(Context context, Secteur secteur, BorneAcces borneAcces) throws MalformedURLException, IOException, RuntimeException, Exception {
        if ((context != null)&&(secteur != null)&&(borneAcces != null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)&&(borneAcces instanceof BorneAcces)) {
                this.attributionSecteurBorneAccesSrv.attribuer(this.getRessource(context), secteur, borneAcces);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void desattribuer(Context context, Secteur secteur, BorneAcces borneAcces) throws MalformedURLException, IOException, RuntimeException, Exception {
        if ((context != null)&&(secteur != null)&&(borneAcces != null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)&&(borneAcces instanceof BorneAcces)) {
                this.attributionSecteurBorneAccesSrv.desattribuer(this.getRessource(context), secteur, borneAcces);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
    public AttributionSecteurBorneAcces getBySecteur(Context context, Secteur secteur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception {
        AttributionSecteurBorneAcces  attributionSecteurBorneAcces = null;
        if ((context != null)&&(secteur != null)&&(secteur != null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)&&(secteur instanceof Secteur)) {
                attributionSecteurBorneAcces=this.attributionSecteurBorneAccesSrv.getBySecteur(this.getRessource(context), secteur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return attributionSecteurBorneAcces;
    }
}
