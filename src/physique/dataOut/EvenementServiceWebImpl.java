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
import metier.entitys.Acces;
import metier.entitys.BorneAcces;
import metier.entitys.Camera;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Evenement;
import metier.entitys.Intrusion;
import metier.entitys.Photo;
import metier.entitys.Position;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
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
public class EvenementServiceWebImpl implements EvenementServiceWeb {

    public List<Evenement> getAll(Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new EvenementServiceWebImpl.RESTGetAll().execute(context);
        List<Evenement> retour = (List<Evenement>) ret.get();
        return retour;
    }

    public List<Evenement> getAll(Context context, int index, int nbResultat) throws Exception {
        AsyncTask<Object, Void, Object> ret = new EvenementServiceWebImpl.RESTGetAllByRange().execute(context, index, nbResultat);
        List<Evenement> retour = (List<Evenement>) ret.get();
        return retour;
    }

    public int count(Context c) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTCount().execute(c);
        Integer count = (Integer) ret.get();
        return count;
    }

    public Evenement getById(Context context, Long id) throws Exception {
        AsyncTask<Object, Void, Object> ret = new EvenementServiceWebImpl.RESTGetByID().execute(context, id);
        Evenement retour = (Evenement) ret.get();
        return retour;
    }

    private class RESTGetByID extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[0];
            Long id = (Long) params[1];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Evenement evenement = null;
            Acces acces = null;
            Photo photo = null;
            Intrusion intrusion = null;

            InputStream fluxLecture = null;
            URL url = null;
            try {
                url = new URL(ressource.getPathToAccesWebService() + "evenement/" + id);
            } catch (MalformedURLException ex) {
                Logger.getLogger(EvenementServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fluxLecture = url.openStream();
            } catch (IOException ex) {
                Logger.getLogger(EvenementServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(EvenementServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(fluxLecture);
            } catch (SAXException ex) {
                Logger.getLogger(EvenementServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EvenementServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("acces");
            acces = new Acces();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    acces.setId(Long.parseLong(getTagValue("id", eElement)));
                    Long idborne = null;
                    Boolean entre = null;
                    String nom = null;
                    Long idPos = null;
                    Double latitude = null;
                    Double longitude = null;
                    NodeList nodeBorneAcces = doc.getElementsByTagName("borneAcces");
                    for (int temp2 = 0; temp2 < nodeBorneAcces.getLength(); temp2++) {
                        Node nNodeBorneAcces = nodeBorneAcces.item(temp2);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementBorneAcces = (Element) nNodeBorneAcces;
                            idborne = Long.parseLong(getTagValue("id", eElementBorneAcces));
                            entre = Boolean.parseBoolean(getTagValue("entrer", eElementBorneAcces));
                            nom = getTagValue("nom", eElementBorneAcces);
                            NodeList nodePosition = doc.getElementsByTagName("position");
                            for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
                                Node nNodePosition = nodePosition.item(tempPosition);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElementPosition = (Element) nNodePosition;
                                    latitude = Double.parseDouble(getTagValue("latitude", eElementPosition));
                                    longitude = Double.parseDouble(getTagValue("longitude", eElementPosition));
                                    idPos = Long.parseLong(getTagValue("id", eElementPosition));
                                }
                            }
                        }
                    }
                    acces.setPassage(Boolean.parseBoolean(getTagValue("passage", eElement)));
                    BorneAcces b = new BorneAcces();
                    b.setId(idborne);
                    b.setEntrer(entre);
                    b.setNom(nom);
                    Position pos = new Position();
                    pos.setLongitude(longitude);
                    pos.setLatitude(latitude);
                    pos.setId(id);
                    b.setPosition(pos);
                    acces.setBorneAcces(b);
                    NodeList nodeUtilisateur = doc.getElementsByTagName("utilisateur");
                    String adresse = null;
                    Integer codePostale = null;
                    Boolean homme = null;
                    Long idUtilisateur = null;
                    String nomUtilisateur = null;
                    String prenom = null;
                    String ville = null;
                    for (int tempUtilisateur = 0; tempUtilisateur < nodeUtilisateur.getLength(); tempUtilisateur++) {
                        Node nNodeUtilisateur = nodeUtilisateur.item(tempUtilisateur);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementUtilisateur = (Element) nNodeUtilisateur;
                            idUtilisateur = Long.parseLong(getTagValue("id", eElementUtilisateur));
                            homme = Boolean.parseBoolean(getTagValue("homme", eElementUtilisateur));
                            adresse = getTagValue("adresse", eElementUtilisateur);
                            codePostale = Integer.parseInt(getTagValue("codePostale", eElementUtilisateur));
                            nomUtilisateur = getTagValue("nom", eElementUtilisateur);
                            prenom = getTagValue("prenom", eElementUtilisateur);
                            ville = getTagValue("ville", eElementUtilisateur);

                        }
                    }
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setAdresse(adresse);
                    utilisateur.setCodePostale(codePostale);
                    utilisateur.setHomme(homme);
                    utilisateur.setId(idUtilisateur);
                    utilisateur.setNom(nomUtilisateur);
                    utilisateur.setPrenom(prenom);
                    utilisateur.setVille(ville);
                    acces.setUtilisateur(utilisateur);

                    if (getTagValue("dateEvt", eElement) != null) {
                        String dateStr = getTagValue("dateEvt", eElement);
                        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date d = new Date();
                        try {
                            d = dateFormat.parse(dateStr);
                        } catch (ParseException ex) {
                            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        acces.setDateEvt(d);
                    }
                    evenement = acces;
                }
            }
            if (evenement == null) {
                NodeList nListP = doc.getElementsByTagName("photo");
                Photo p = new Photo();
                for (int temp = 0; temp < nListP.getLength(); temp++) {
                    Node nNode = nListP.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        p.setId(Long.parseLong(getTagValue("id", eElement)));
                        String img = getTagValue("image", eElement);
                        p.setImage(img.getBytes());
                        NodeList nodeBorneAcces = doc.getElementsByTagName("camera");
                        Long idCamera = null;
                        String ip = null;
                        String nom = null;
                        String type = null;
                        Double latitude = null;
                        Double longitude = null;
                        Long idPos = null;
                        for (int temp2 = 0; temp2 < nodeBorneAcces.getLength(); temp2++) {
                            Node nNodeBorneAcces = nodeBorneAcces.item(temp2);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElementBorneAcces = (Element) nNodeBorneAcces;
                                idCamera = Long.parseLong(getTagValue("id", eElementBorneAcces));
                                ip = getTagValue("ip", eElementBorneAcces);
                                nom = getTagValue("nom", eElementBorneAcces);
                                type = getTagValue("type", eElementBorneAcces);
                                NodeList nodePosition = doc.getElementsByTagName("position");
                                for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
                                    Node nNodePosition = nodePosition.item(tempPosition);
                                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElementPosition = (Element) nNodePosition;
                                        latitude = Double.parseDouble(getTagValue("latitude", eElementPosition));
                                        longitude = Double.parseDouble(getTagValue("longitude", eElementPosition));
                                        idPos = Long.parseLong(getTagValue("id", eElementPosition));
                                    }
                                }
                            }
                        }
                        Camera camera = new Camera();
                        camera.setId(idCamera);
                        camera.setIp(ip);
                        camera.setNom(nom);
                        camera.setType(type);
                        Position position = new Position();
                        position.setLatitude(latitude);
                        position.setLatitude(latitude);
                        camera.setPosition(position);
                        p.setCamera(camera);
                        if (getTagValue("dateEvt", eElement) != null) {
                            String dateStr = getTagValue("dateEvt", eElement);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            p.setDateEvt(d);
                        }
                        evenement = p;
                    }
                }
            }
            if (evenement == null) {
                NodeList nListP = doc.getElementsByTagName("intrusion");
                Intrusion p = new Intrusion();
                for (int temp = 0; temp < nListP.getLength(); temp++) {
                    Node nNode = nListP.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        p.setId(Long.parseLong(getTagValue("id", eElement)));
                        String img = getTagValue("image", eElement);
                        
                        NodeList nodeDetecteur = doc.getElementsByTagName("detecteurIntrusion");
                        Long idDetecteur = null;
                        String ip = null;
                        String nom = null;
                        Double latitude = null;
                        Double longitude = null;
                        Long idPos = null;
                        for (int temp2 = 0; temp2 < nodeDetecteur.getLength(); temp2++) {
                            Node nNodeBorneAcces = nodeDetecteur.item(temp2);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElementBorneAcces = (Element) nNodeBorneAcces;
                                idDetecteur = Long.parseLong(getTagValue("id", eElementBorneAcces));
                                ip = getTagValue("ip", eElementBorneAcces);
                                nom = getTagValue("nom", eElementBorneAcces);
                                NodeList nodePosition = doc.getElementsByTagName("position");
                                for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
                                    Node nNodePosition = nodePosition.item(tempPosition);
                                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElementPosition = (Element) nNodePosition;
                                        latitude = Double.parseDouble(getTagValue("latitude", eElementPosition));
                                        longitude = Double.parseDouble(getTagValue("longitude", eElementPosition));
                                        idPos = Long.parseLong(getTagValue("id", eElementPosition));
                                    }
                                }
                            }
                        }
                        DetecteurIntrusion detecteurIntrusion = new DetecteurIntrusion();
                        detecteurIntrusion.setId(idDetecteur);
                        detecteurIntrusion.setNom(nom);
                        Position position = new Position();
                        position.setLatitude(latitude);
                        position.setLatitude(latitude);
                        detecteurIntrusion.setPosition(position);
                        p.setDetecteurIntrusion(detecteurIntrusion);
                        if (getTagValue("dateEvt", eElement) != null) {
                            String dateStr = getTagValue("dateEvt", eElement);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            p.setDateEvt(d);
                        }
                        evenement = p;                       
                    }
                }
            } return evenement;
        }
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

    private class RESTGetAllByRange extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[0];
            Integer index = (Integer) params[1];
            Integer nbResult = (Integer) params[2];
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
                URL url = new URL(ressource.getPathToAccesWebService() + "evenement/" + index + "/" + nbResult);
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
