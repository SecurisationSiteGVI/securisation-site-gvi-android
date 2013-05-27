package fr.securisation_site_gvi.client;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.securisation_site_gvi.client.fragments.GererAuthorisationAccesFragement;
import fr.securisation_site_gvi.client.fragments.HistoriqueFragment;
import fr.securisation_site_gvi.client.fragments.MenuAppareilFragment;
import fr.securisation_site_gvi.client.fragments.MenuBadgeFragment;
import fr.securisation_site_gvi.client.fragments.MenuNumeropredefiniFragment;
import fr.securisation_site_gvi.client.fragments.MenuSecteurFragment;
import fr.securisation_site_gvi.client.fragments.MenuUtilisateurFragement;

public class Slide extends TemplateActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle="Menu";
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        String[] truc = {"Historique", "Gérer les badges", "Gérer les utilisateurs",
            "Gérer les secteurs", "Gérer les appareils", "Gérer les numéros prédéfinis",
            "Gérer les authorisation d'accès", "Se déconnecter"};
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, truc));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, 
                mDrawerLayout,
                R.drawable.ic_drawer, 
                R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); 
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        boolean isDeco =false;
        switch (position) {
            case 0:
                fragment = new HistoriqueFragment();
                break;
            case 1:
                fragment = new MenuBadgeFragment();
                break;
            case 2:
                fragment = new MenuUtilisateurFragement();
                break;
            case 3:
                fragment = new MenuSecteurFragment();
                break;
            case 4: 
                fragment = new MenuAppareilFragment();
                break;
            case 5:
                fragment = new MenuNumeropredefiniFragment();
                break;
           case 6: 
               fragment = new GererAuthorisationAccesFragement();
               break;
           case 7:
               isDeco=true;
               this.removeNotification(12);
               Intent i = new Intent(Slide.this, MainActivity.class);
               startActivity(i);
               finish();
               break;
            default:
                fragment = new HistoriqueFragment();
                break;
        }if(isDeco==false){
            FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);
        }
        
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
     @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            removeNotification(12);
            Intent i = new Intent(Slide.this, MainActivity.class);
            startActivity(i);
            this.finish();
        }
        return false;
    }
}