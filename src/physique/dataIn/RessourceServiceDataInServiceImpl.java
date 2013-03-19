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
public class RessourceServiceDataInServiceImpl implements RessourcesServiceDataIn {

    public static final String RESSOURCE_KEY = "id";
    public static final String RESSOURCE_PROTOCOL = "protocol";
    public static final String RESSOURCE_SERVEUR_URL = "serveurUrl";
    public static final String RESSOURCE_PORT = "port";
    public static final String RESSOURCE_APPLICATION_NAME = "appicationName";
    public static final String RESSOURCE_RESSOURCES_PATH = "ressourcesPath";
    public static final String RESSOURCE_TABLE_NAME = "Ressource";
    private Connexion connexion;
    private SQLiteDatabase db;
    private Context c;

    public RessourceServiceDataInServiceImpl(Context c) {
        this.c = c;
    }

    public void add(Ressource ressource) throws Exception {
        this.connexion = new Connexion(c, "fr.db", null, Connexion.VERSION);
        db = connexion.getBDD();
        this.connexion.open();
        ContentValues values = new ContentValues();
        values.put(RESSOURCE_KEY, 1);
        values.put(RESSOURCE_PROTOCOL, ressource.getProtocol());
        values.put(RESSOURCE_SERVEUR_URL, ressource.getServeurURL());
        values.put(RESSOURCE_PORT, ressource.getPort());
        values.put(RESSOURCE_APPLICATION_NAME, ressource.getApplicationName());
        values.put(RESSOURCE_RESSOURCES_PATH, ressource.getResourcesPath());
        this.connexion.mDb.insert(RESSOURCE_TABLE_NAME, null, values);
        this.connexion.close();
    }

    public Ressource getRessource() throws Exception {
        this.connexion = new Connexion(c, "fr.db", null, Connexion.VERSION);
        this.connexion.open();
        Cursor c = this.connexion.mDb.rawQuery("select * from " + RESSOURCE_TABLE_NAME + " where id = 1", null);
//        Cursor c = this.connexion.mDb.query(RESSOURCE_TABLE_NAME, new String[]{RESSOURCE_KEY, RESSOURCE_APPLICATION_NAME,
//                    RESSOURCE_PORT, RESSOURCE_PROTOCOL, RESSOURCE_RESSOURCES_PATH, RESSOURCE_SERVEUR_URL}, null, null, null, null, null);
//        
        Ressource ressource = new Ressource();
//        if (c.getCount()>0) {
        c.moveToNext();
        int columnIndex = c.getColumnIndex(RESSOURCE_PROTOCOL);
        String string = c.getString(columnIndex);
        ressource.setProtocol(string);
        ressource.setPort(c.getInt(c.getColumnIndex(RESSOURCE_PORT)));
        ressource.setServeurURL(c.getString(c.getColumnIndex(RESSOURCE_SERVEUR_URL)));
        ressource.setResourcesPath(c.getString(c.getColumnIndex(RESSOURCE_RESSOURCES_PATH)));
        ressource.setApplicationName(c.getString(c.getColumnIndex(RESSOURCE_APPLICATION_NAME)));
//        }
        this.connexion.close();
        return ressource;
    }

    public void update(Ressource ressource) throws Exception {
        this.connexion = new Connexion(c, "fr.db", null, Connexion.VERSION);
        connexion.open();

        ContentValues values = new ContentValues();
        values.put(RESSOURCE_KEY, 1);
        values.put(RESSOURCE_PROTOCOL, ressource.getProtocol());
        values.put(RESSOURCE_SERVEUR_URL, ressource.getServeurURL());
        values.put(RESSOURCE_PORT, ressource.getPort());
        values.put(RESSOURCE_APPLICATION_NAME, ressource.getApplicationName());
        values.put(RESSOURCE_RESSOURCES_PATH, ressource.getResourcesPath());
        this.connexion.mDb.update(RESSOURCE_TABLE_NAME, values, RESSOURCE_KEY + " = 1", null);
        connexion.close();
    }
}
