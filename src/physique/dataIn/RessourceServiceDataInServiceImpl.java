/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataIn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import metier.entitys.Ressource;

/**
 *
 * @author damien
 */
public class RessourceServiceDataInServiceImpl implements RessourcesServiceDataIn{

    public static final String RESSOURCE_KEY = "id";
    public static final String RESSOURCE_PROTOCOL = "protocol";
    public static final String RESSOURCE_SERVEUR_URL = "serveurUrl";
    public static final String RESSOURCE_PORT = "port";
    public static final String RESSOURCE_APPLICATION_NAME = "appicationName";
    public static final String RESSOURCE_RESSOURCES_PATH = "ressourcesPath";
    public static final String RESSOURCE_TABLE_NAME = "Ressource";
    private Connexion connexion;
    private SQLiteDatabase db;

    public RessourceServiceDataInServiceImpl(Context c) {
        this.connexion = new Connexion(c, "Ressource.db", null, Connexion.VERSION);
        db = this.connexion.getBDD();
    }

    public void add(Ressource ressource) throws Exception {
        connexion.open();
        ContentValues values = new ContentValues();
        values.put(RESSOURCE_KEY, 1);
        values.put(RESSOURCE_PROTOCOL, ressource.getProtocol());
        values.put(RESSOURCE_SERVEUR_URL, ressource.getServeurURL());
        values.put(RESSOURCE_PORT, ressource.getPort());
        values.put(RESSOURCE_APPLICATION_NAME, ressource.getApplicationName());
        values.put(RESSOURCE_RESSOURCES_PATH, ressource.getResourcesPath());
        db.insert(RESSOURCE_TABLE_NAME, null, values);
        this.connexion.close();
    }

    public Ressource getRessource() throws Exception {
        this.connexion.open();
        Cursor c = this.db.query(RESSOURCE_TABLE_NAME, new String[]{RESSOURCE_KEY, RESSOURCE_APPLICATION_NAME,
                    RESSOURCE_PORT, RESSOURCE_PROTOCOL, RESSOURCE_RESSOURCES_PATH, RESSOURCE_SERVEUR_URL}, 1 + " LIKE 1", null, null, null, null);
        this.connexion.close();
        Ressource ressource = new Ressource();
        ressource.setProtocol(c.getString(c.getColumnIndex(RESSOURCE_PROTOCOL)));
        ressource.setPort(c.getInt(c.getColumnIndex(RESSOURCE_PORT)));
        ressource.setServeurURL(c.getString(c.getColumnIndex(RESSOURCE_SERVEUR_URL)));
        ressource.setResourcesPath(c.getString(c.getColumnIndex(RESSOURCE_RESSOURCES_PATH)));
        ressource.setApplicationName(c.getString(c.getColumnIndex(RESSOURCE_APPLICATION_NAME)));
        return ressource;
    }

    public void update(Ressource ressource) throws Exception {
        connexion.open();
        ContentValues values = new ContentValues();
        values.put(RESSOURCE_KEY, 1);
        values.put(RESSOURCE_PROTOCOL, ressource.getProtocol());
        values.put(RESSOURCE_SERVEUR_URL, ressource.getServeurURL());
        values.put(RESSOURCE_PORT, ressource.getPort());
        values.put(RESSOURCE_APPLICATION_NAME, ressource.getApplicationName());
        values.put(RESSOURCE_RESSOURCES_PATH, ressource.getResourcesPath());
        db.update(RESSOURCE_TABLE_NAME, values, RESSOURCE_KEY + " = 1", null);
        connexion.close();
    }
}
