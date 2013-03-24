/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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

/**
 *
 * @author damien
 */
public class EvenementServiceWebImpl implements EvenementServiceWeb{

    public List<Evenement> getAll(Context context) throws Exception{
        AsyncTask<Object, Void, Object> ret = new EvenementServiceWebImpl.RESTGetAll().execute(context);
        List<Evenement> retour = (List<Evenement>) ret.get();
        return retour;
    }

    public List<Evenement> getAll(Context context ,int index, int nbResultat) throws Exception{
        throw new UnsupportedOperationException("TO DO THAT ! ");
    }

    public int count(Context c) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTCount().execute(c);
        Integer count = (Integer) ret.get();
        return count;
    }
    private class RESTCount extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            int ret = 0;
            try {
                Context c = (Context) params[0];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedReader br = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "evenement/count");
                InputStream fluxLecture = url.openStream();
                br = new BufferedReader(new InputStreamReader((fluxLecture)));
                String output;
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    ret = Integer.parseInt(output);
                }
                br.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }

    
    private class RESTGetAll extends AsyncTask<Object, Void, Object> {

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
                        evenement.setId(Long.parseLong(getTagValue("id", eElement)));
                        if (getTagValue("dateEvt", eElement) != null) {
                            String dateStr = getTagValue("dateEvt", eElement);
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
    private static String getTagValue(String sTag, Element eElement) {
        String ret = null;

        try {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            ret = nValue.getNodeValue();
        } catch (NullPointerException e) {
            // System.out.println("Null pour l'item : " + sTag);
        }
        return ret;
    }

}
