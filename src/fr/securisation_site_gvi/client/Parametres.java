package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierFactory;
import metier.RessourceService;
import metier.entitys.Ressource;

public class Parametres extends Activity {

    private RessourceService ressourceSrv;
    private Button valider;
    private EditText editTextProtocol;
    private EditText editTextServeurURL;
    private EditText editTextPort;
    private EditText editTextApplicationName;
    private EditText editTextRessourcesPath;
    private boolean IsinBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        this.ressourceSrv = MetierFactory.getRessourceSrv(Parametres.this);
        this.initGraphicalObjects();
        this.initEditText();
    }

    private void initEditText() {
        Ressource ressource = null;
        try {
            ressource = this.ressourceSrv.getRessource();
        } catch (Exception ex) {
            Logger.getLogger(Parametres.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ressource == null) {
            this.editTextApplicationName.setText("securisation-site-gvi-web");
            this.editTextPort.setText("8080");
            this.editTextProtocol.setText("http://");
            this.editTextRessourcesPath.setText("webresources");
            this.editTextServeurURL.setText("192.168.1.11");
            this.IsinBDD = false;
        } else {
            this.editTextApplicationName.setText(ressource.getApplicationName());
            this.editTextPort.setText(String.valueOf(ressource.getPort()));
            this.editTextProtocol.setText(ressource.getProtocol());
            this.editTextRessourcesPath.setText(ressource.getResourcesPath());
            this.editTextServeurURL.setText(ressource.getServeurURL());
            this.IsinBDD = true;
        }
    }

    private void initGraphicalObjects() {
        this.valider = (Button) findViewById(R.id.buttonValider);
        this.editTextProtocol = (EditText) findViewById(R.id.editTextProtocol);
        this.editTextServeurURL = (EditText) findViewById(R.id.EditTextServeurURL);
        this.editTextPort = (EditText) findViewById(R.id.editTextPort);
        this.editTextApplicationName = (EditText) findViewById(R.id.editTextApplicationName);
        this.editTextRessourcesPath = (EditText) findViewById(R.id.editTextRessoucesPath);
        this.addActionListnerForAllGraphicalObjects();
    }

    private void addActionListnerForAllGraphicalObjects() {
        this.valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ressource res = new Ressource();
                res.setApplicationName(editTextApplicationName.getText().toString());
                res.setPort(Integer.parseInt(editTextPort.getText().toString()));
                res.setProtocol(editTextProtocol.getText().toString());
                res.setResourcesPath(editTextRessourcesPath.getText().toString());
                res.setServeurURL(editTextServeurURL.getText().toString());
                if (IsinBDD) {
                    try {
                        ressourceSrv.update(res);
                    } catch (Exception ex) {
                        Logger.getLogger(Parametres.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        ressourceSrv.add(res);
                    } catch (Exception ex) {
                        Logger.getLogger(Parametres.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}