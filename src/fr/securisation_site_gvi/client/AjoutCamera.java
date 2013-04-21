/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import metier.entitys.TypeCamera;

/**
 *
 * @author damien
 */
public class AjoutCamera extends TemplateActivity {

    private Button buttonCree;
    private EditText editTextNom;
    private EditText editTextIp;
    private Spinner spinnerType;
    private TypeCamera typeCameraSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_camera);
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.editTextNom = (EditText) findViewById(R.id.AjoutCameraEditTextNom);
        this.editTextIp = (EditText) findViewById(R.id.AjoutCameraEditTextIp);
        this.buttonCree = (Button) findViewById(R.id.AjoutCamerabuttonCree);
        this.spinnerType = (Spinner) findViewById(R.id.AjoutCameraSpinnerType);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonCree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        this.spinnerType.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeCameraSelected = TypeCamera.values()[position];
            }
        });
    }

    @Override
    public void addInitialValueForGraphicalObjects() {
        String[] listeStrings = new String[TypeCamera.values().length];
        for (int i = 0; i < TypeCamera.values().length; i++) {
            listeStrings[i] = TypeCamera.values()[i].toString();
        }
        this.spinnerType.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
    }
}
