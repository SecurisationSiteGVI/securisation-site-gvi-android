package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ModifierUtilisateur extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_utilisateur);
        Bundle extras = getIntent().getExtras();
        Long id = extras.getLong("id");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
