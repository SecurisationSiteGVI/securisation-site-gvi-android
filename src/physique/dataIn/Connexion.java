/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataIn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author damien
 */
public class Connexion extends SQLiteOpenHelper {

    public static final String RESSOURCE_KEY = "id";
    public static final String RESSOURCE_PROTOCOL = "protocol";
    public static final String RESSOURCE_SERVEUR_URL = "serveurUrl";
    public static final String RESSOURCE_PORT = "port";
    public static final String RESSOURCE_APPLICATION_NAME = "appicationName";
    public static final String RESSOURCE_RESSOURCES_PATH = "ressourcesPath";
    public static final String RESSOURCE_TABLE_NAME = "Ressource";
    public static final String RESSOURCE_TABLE_CREATE =
            "CREATE TABLE " + RESSOURCE_TABLE_NAME + " ("
            + RESSOURCE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RESSOURCE_PROTOCOL + " TEXT, "
            + RESSOURCE_SERVEUR_URL + " TEXT, "
            + RESSOURCE_APPLICATION_NAME + " TEXT, "
            + RESSOURCE_RESSOURCES_PATH + " TEXT, "
            + RESSOURCE_PORT + " INTEGER);";
    public static final String RESSOURCE_TABLE_DROP = "DROP TABLE IF EXISTS " + RESSOURCE_TABLE_NAME + ";";
    protected final static int VERSION = 1;
    protected SQLiteDatabase mDb = null;

    public Connexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //name nom de la base
        super(context, name, factory, version);
    }

    public void open() {
        mDb = this.getWritableDatabase();
    }

    @Override
    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getBDD() {
        return mDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RESSOURCE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RESSOURCE_TABLE_DROP);
        onCreate(db);
    }
}
