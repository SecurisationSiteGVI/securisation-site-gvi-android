/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.numeroPredefini.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.NumeroPredefinis;
import metier.entitys.Position;
import metier.entitys.Ressource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import physique.dataOut.BoiteAOutils;

/**
 *
 * @author damien
 */
public class RESTNumeroPredefiniGetAllByRange {

    /**
     *
     * @param ressource
     * @param index
     * @param nbResult
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static NumeroPredefinis execute(Ressource ressource, int index, int nbResult) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        NumeroPredefinis numeroPredefinis = new NumeroPredefinis();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "numeropredefinis/" + index + "/" + nbResult);
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("numeroPredefinis");
        List<String> strings = new ArrayList<String>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                numeroPredefinis.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                String numeo = BoiteAOutils.getTagValue("numeros", eElement);
                NodeList childNodes = eElement.getChildNodes();
                for (int temp2 = 1; temp2 < childNodes.getLength(); temp2++) {
                    Node node = childNodes.item(temp2);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement2 = (Element) node;
                        String ssr = eElement2.getNodeValue();
                        Node nValue = (Node) childNodes.item(temp2).getChildNodes().item(0);
                        String ret = nValue.getNodeValue();
                        strings.add(ret);
                    }
                }
                numeroPredefinis.setNumeros(strings);
            }
        }
        return numeroPredefinis;
    }
}
