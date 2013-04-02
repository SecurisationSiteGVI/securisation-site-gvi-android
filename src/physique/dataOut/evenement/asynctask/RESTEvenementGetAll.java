/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Evenement;
import metier.entitys.Ressource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.BoiteAOutils;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTEvenementGetAll extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[0];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Evenement> utilisateurs = new ArrayList<Evenement>();
            try {
                InputStream fluxLecture = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "evenement");
                fluxLecture = url.openStream();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fluxLecture);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("evenement");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Evenement evenement = new Evenement();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        evenement.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                        if (BoiteAOutils.getTagValue("dateEvt", eElement) != null) {
                            String dateStr = BoiteAOutils.getTagValue("dateEvt", eElement);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            evenement.setDateEvt(d);
                        }
                        utilisateurs.add(evenement);
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return utilisateurs;
        }
    }
