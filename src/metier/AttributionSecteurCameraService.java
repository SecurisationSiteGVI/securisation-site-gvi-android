/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.Camera;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public interface AttributionSecteurCameraService {
    public void attribuer(Context context, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException,Exception;
    public void desattribuer(Context context, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException,Exception;
}
