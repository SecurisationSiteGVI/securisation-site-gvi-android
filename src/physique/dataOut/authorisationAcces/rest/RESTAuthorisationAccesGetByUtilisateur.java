/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.authorisationAcces.rest;

import metier.entitys.AuthorisationAcces;
import java.io.IOException;
import java.io.InputStream;
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
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import metier.entitys.Utilisateur;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import physique.dataOut.BoiteAOutils;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTAuthorisationAccesGetByUtilisateur {

    public static AuthorisationAcces execute(Ressource ressource, Utilisateur utilisateur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        AuthorisationAcces authorisationAcces = null;
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "authorisationacces/byUtilisateur/" + utilisateur.getId());
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("authorisationAcces");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (BoiteAOutils.getTagValue("heureFermeture", eElement) != null) {
                    String dateFin = BoiteAOutils.getTagValue("heureFermeture", eElement);
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date d = new Date();
                    try {
                        d = dateFormat.parse(dateFin);
                    } catch (ParseException ex) {
                        Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    authorisationAcces.setHeureFermeture(d);
                }
                if (BoiteAOutils.getTagValue("heureOuverture", eElement) != null) {
                    String dateFin = BoiteAOutils.getTagValue("heureOuverture", eElement);
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date d = new Date();
                    try {
                        d = dateFormat.parse(dateFin);
                    } catch (ParseException ex) {
                        Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    authorisationAcces.setHeureOuverture(d);
                }
                if (BoiteAOutils.getTagValue("id", eElement) != null) {
                    Long id = Long.parseLong(BoiteAOutils.getTagValue("heureOuverture", eElement));
                    authorisationAcces.setId(id);
                }
                List<Secteur> secteurs = new ArrayList<Secteur>();
                NodeList nListSecteur = eElement.getElementsByTagName("secteurs");
                for (int tempSecteur = 0; tempSecteur < nListSecteur.getLength(); tempSecteur++) {
                    Secteur secteur = new Secteur();
                    Node nNodeSecteur = nListSecteur.item(tempSecteur);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementSecteur = (Element) nNodeSecteur;
                        secteur.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElementSecteur)));
                        secteur.setNom(BoiteAOutils.getTagValue("nom", eElementSecteur));
                        secteurs.add(secteur);
                    }
                }
                authorisationAcces.setSecteurs(secteurs);
                NodeList nListUtilisateur = eElement.getElementsByTagName("utilisateur");
                for (int tempUtilisateur = 0; tempUtilisateur < nListUtilisateur.getLength(); tempUtilisateur++) {
                    Utilisateur utilisateur2 = new Utilisateur();
                    Node nNodeUtilisateur = nListUtilisateur.item(tempUtilisateur);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementUtilisateur = (Element) nNodeUtilisateur;
                        utilisateur2.setEmail(BoiteAOutils.getTagValue("email", eElementUtilisateur));
                        utilisateur2.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElementUtilisateur)));
                        utilisateur2.setVille(BoiteAOutils.getTagValue("ville", eElementUtilisateur));
                        utilisateur2.setNom(BoiteAOutils.getTagValue("nom", eElementUtilisateur));
                        utilisateur2.setPrenom(BoiteAOutils.getTagValue("prenom", eElementUtilisateur));
                        utilisateur2.setCodePostale(Integer.parseInt(BoiteAOutils.getTagValue("codePostale", eElementUtilisateur)));
                        utilisateur2.setAdresse(BoiteAOutils.getTagValue("adresse", eElementUtilisateur));
                        utilisateur2.setTelephonePortable(BoiteAOutils.getTagValue("telephonePortable", eElementUtilisateur));
                        utilisateur2.setTelephoneFixe(BoiteAOutils.getTagValue("telephoneFixe", eElementUtilisateur));
                        utilisateur2.setHomme(Boolean.parseBoolean(BoiteAOutils.getTagValue("homme", eElementUtilisateur)));
                        if (BoiteAOutils.getTagValue("dateDeNaissance", eElementUtilisateur) != null) {
                            String dateStr = BoiteAOutils.getTagValue("dateDeNaissance", eElementUtilisateur);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            utilisateur2.setDateDeNaissance(d);

                        }
                        authorisationAcces.setUtilisateur(utilisateur);
                    }
                }
            }
        }
        return authorisationAcces;
    }
}
