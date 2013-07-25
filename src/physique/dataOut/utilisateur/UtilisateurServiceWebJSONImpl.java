/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;

import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;
import physique.dataOut.utilisateur.restXML.RESTUtilisateurVerificationConnexion;

/**
 *
 * @author damien
 */
public class UtilisateurServiceWebJSONImpl implements UtilisateurServiceWeb {

    private File jsonFile = new File("json");

    public List<Utilisateur> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Utilisateur> getAll(int from, int nbResut, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/" + from + "/" + nbResut);
        InputStream fluxLecture = url.openStream();
        JsonParserFactory factory = Json.createParserFactory(null);
        InputStreamReader inputStreamReader = new InputStreamReader(fluxLecture);
        javax.json.stream.JsonParser parser = factory.createParser(fluxLecture);
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        int i = 0;
        Utilisateur utilisateur = null;
        String flag = null;
        while (parser.hasNext()) {
            Event event = parser.next();
            switch (event) {
                case START_OBJECT: {
                    if (i != 1) {
                        utilisateurs.add(utilisateur);
                    }
                    utilisateur = new Utilisateur();
                    break;
                }
                case KEY_NAME: {
                    flag = parser.getString();
                    break;
                }
                case VALUE_NUMBER: {
                    if (flag != null) {
                        if (flag.equals("id")) {
                            utilisateur.setId(parser.getLong());
                        }
                    }
                    break;
                }
                case VALUE_FALSE: {
                    if (flag != null) {
                        if (flag.equals("homme")) {
                            utilisateur.setHomme(Boolean.parseBoolean(parser.getString()));
                        }
                    }
                    break;
                }
                case VALUE_TRUE: {
                    if (flag != null) {
                        if (flag.equals("homme")) {
                            utilisateur.setHomme(Boolean.parseBoolean(parser.getString()));
                        }
                    }
                    break;
                }
                case VALUE_NULL: {
                    if (flag != null) {
                        if (flag.equals("id")) {
                            utilisateur.setId(Long.valueOf(parser.getString()));
                        } else if (flag.equals("nom")) {
                            utilisateur.setNom(parser.getString());
                        } else if (flag.equals("prenom")) {
                            utilisateur.setPrenom(parser.getString());
                        } else if (flag.equals("ville")) {
                            utilisateur.setVille(parser.getString());
                        } else if (flag.equals("codePostale")) {
                            utilisateur.setCodePostale(parser.getInt());
                        } else if (flag.equals("adresse")) {
                            utilisateur.setAdresse(parser.getString());
                        } else if (flag.equals("telephonePortable")) {
                            utilisateur.setTelephonePortable(parser.getString());
                        } else if (flag.equals("telephoneFixe")) {
                            utilisateur.setTelephoneFixe(parser.getString());
                        } else if (flag.equals("homme")) {
                            //utilisateur.setTelephoneFixe(parser.getString());
                        }
                    }
                    break;
                }
                case VALUE_STRING: {
                    if (flag != null) {
                        if (flag.equals("id")) {
                            utilisateur.setId(Long.valueOf(parser.getString()));
                        } else if (flag.equals("nom")) {
                            utilisateur.setNom(parser.getString());
                        } else if (flag.equals("prenom")) {
                            utilisateur.setPrenom(parser.getString());
                        } else if (flag.equals("ville")) {
                            utilisateur.setVille(parser.getString());
                        } else if (flag.equals("codePostale")) {
                            utilisateur.setCodePostale(parser.getInt());
                        } else if (flag.equals("adresse")) {
                            utilisateur.setAdresse(parser.getString());
                        } else if (flag.equals("telephonePortable")) {
                            utilisateur.setTelephonePortable(parser.getString());
                        } else if (flag.equals("telephoneFixe")) {
                            utilisateur.setTelephoneFixe(parser.getString());
                        } else if (flag.equals("homme")) {
                            //utilisateur.setTelephoneFixe(parser.getString());
                        }
                    }
                    break;
                }
            }
            i++;
        }
        return utilisateurs;
    }

    private String getValues() {

        return null;
    }

    public boolean add(Utilisateur utilisateur, Ressource ressource) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean remove(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean loginIsUse(String login, Ressource ressource) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Technicien verificationConnexion(Technicien utilisateur, Ressource ressource) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException {
        Technicien technicien = (Technicien) new RESTUtilisateurVerificationConnexion().execute(utilisateur, ressource);
        return technicien;
    }

    public Utilisateur getById(Long id, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int count(Ressource ressource) throws MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addTechnicien(Technicien technicien, Ressource ressource) throws MalformedURLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
