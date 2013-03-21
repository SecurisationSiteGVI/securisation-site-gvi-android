/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;

/**
 *
 * @author damien
 */
public class TemplateActivity extends Activity {

    /**
     * Toujours surcharger les methodes :initGraphicalObjects et
     * addActionListnerForAllGraphicalObjects
     */
    protected Context activityContext;

    public void setThisActivityOn(Context context) {
        this.activityContext = context;
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
}
