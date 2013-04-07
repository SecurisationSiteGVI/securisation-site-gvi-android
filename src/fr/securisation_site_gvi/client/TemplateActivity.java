/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.ActionBar;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author damien
 */
public class TemplateActivity extends Activity {

    /**
     * Toujours surcharger les methodes :initGraphicalObjects et
     * addActionListnerForAllGraphicalObjects
     */
    protected Context activityContext = TemplateActivity.this;

    public void setThisActivityOn() {
        this.initGraphicalObjects();
        this.addActionListnerForAllGraphicalObjects();
        this.addInitialValueForGraphicalObjects();
    }

    public void initGraphicalObjects() {
    }

    public void addActionListnerForAllGraphicalObjects() {
    }

    public void addInitialValueForGraphicalObjects() {
    }

    public  void throwConnectException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Celui-ci est surement éteint.", Toast.LENGTH_SHORT);
        toast.show();
    }
    public  void throwIOException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Le réseau est défaillant, vérifier les paramétres d'accès.", Toast.LENGTH_SHORT);
        toast.show();
    }
    public  void throwSAXException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de récupérer les informations du serveur. Le document xml comporte des érreurs.", Toast.LENGTH_SHORT);
        toast.show();
    }
    public  void throwParserConfigurationException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de récupérer les informations du serveur. L'application ne peut pas récuperer les informations.", Toast.LENGTH_SHORT);
        toast.show();
    }
    public  void throwMalformedURLException() {
        Toast toast = Toast.makeText(this.activityContext, "Impossible de se connecter au serveur. Veuillez verifier l'url que vous avez saisie.", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Deprecated
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.activityContext.getClass() != Parametres.class) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Deprecated
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_settings:
                Intent intent = new Intent(this.activityContext, Parametres.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Deprecated
    public int getHeight() {
        int height = 0;
        height = this.getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    @Deprecated
    public int getWidth() {// nexus 7 : 800dp // s3 720dp
        int height = 0;
        height = this.getWindowManager().getDefaultDisplay().getWidth();
        return height;
    }

    public boolean isTablette7() {
        int width = 0;
        width = this.getWindowManager().getDefaultDisplay().getWidth();
        boolean ret = false;
        if (width > 799) {
            ret = true;
        }
        return ret;
    }

    public boolean isTelephone() {
        int width = 0;
        this.getWindowManager().getDefaultDisplay().getWidth();
        boolean ret = false;
        if (width > 700 && width < 750) {
            ret = true;
        }
        return ret;
    }

    public void addNotification(String content, int id) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_action_about)
                .setContentTitle("Sécurisation site gvi")
                .setContentText(content);
        Intent resultIntent = new Intent(this, AccueilActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(AccueilActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }

    public void removeNotification(int id) {
        NotificationManager o = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        o.cancel(id);
    }
}
