/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.text.Annotation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author damien
 */
public class BoiteAOutils {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Path{
        String ressourceName();
    }

  
    public static String getTagValue(String sTag, Element eElement) {
        String ret = null;

        try {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            ret = nValue.getNodeValue();
        } catch (NullPointerException e) {
            // System.t.println("Null pour l'item : " + sTag);
        }
        return ret;
    }
    
    protected static RessourceType getPathInMyMethod(String d , Class s ) throws NoSuchMethodException{
        return s.getMethod(d, null).getAnnotation(RessourceType.class);
    }
}
