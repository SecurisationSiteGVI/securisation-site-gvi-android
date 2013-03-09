package fr.securisation_site_gvi.client;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;

public class AccueilActivity extends Activity {

 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilactivity);
        

    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_accueil, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_accueil, menu);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ActionBar actionBar = getActionBar();
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            case R.id.menu_about:
                // Comportement du bouton "A Propos"
                return true;
//		case R.id.menu_help:
//			// Comportement du bouton "Aide"
//			return true;
//		case R.id.menu_refresh:
//			// Comportement du bouton "Rafraichir"
//			return true;
//		case R.id.menu_search:
//			// Comportement du bouton "Recherche"
//			return true;
            case R.id.menu_settings:
                // Comportement du bouton "Param�tres"
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
