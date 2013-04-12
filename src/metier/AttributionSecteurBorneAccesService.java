/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.BorneAcces;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public interface AttributionSecteurBorneAccesService {
    public void attribuer(Context context, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException,Exception;
    public void desattribuer(Context context, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException,Exception;
    
}
