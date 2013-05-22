/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Intrusion;
import metier.entitys.Photo;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class HistoriquePhoto extends TemplateActivity {

    private Long id;
    private EvenementService evenementSrv = MetierFactory.getEvenementSrv();
    private Photo photo;
    private TextView textViewPhoto;
    private TextView textViewDate;
    private ImageView imageView;
    private TextView textViewCamera;
    private Button buttonretour;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_historique_photo);
        Bundle extras = getIntent().getExtras();
        this.id = extras.getLong("id");
        new HistoriquePhoto.RESTEvenementGetById().execute();
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.buttonretour = (Button) findViewById(R.id.buttonPhotoRetour);
        this.textViewDate = (TextView) findViewById(R.id.textViewPhotoDate);
        this.textViewPhoto = (TextView) findViewById(R.id.textViewPhoto);
        this.imageView = (ImageView) findViewById(R.id.ImageViewPhoto);
        this.textViewCamera = (TextView) findViewById(R.id.textViewCamera);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonretour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
        FileOutputStream fos = null;
        try {
            this.textViewDate.setText(this.photo.getDateEvt().toLocaleString());

            byte[] bytes = this.photo.getImage();
            File f = File.createTempFile("tmp", ".jpg");
            fos = new FileOutputStream(f);
            System.out.println(f.getAbsolutePath());
            fos.write(bytes);
            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
            f.delete();
            this.imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HistoriquePhoto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HistoriquePhoto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(HistoriquePhoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.textViewPhoto.setText("Connectez vous sur le site pour visionner la photo");
        this.textViewCamera.setText(this.photo.getCamera().toString());
    }
    
    private class RESTEvenementGetById extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des informations ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                photo = (Photo) result;
                setThisActivityOn();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else if (result instanceof SAXException) {
                throwSAXException();
            } else if (result instanceof ParseException) {
                throwParseException();
            } else if (result instanceof ParserConfigurationException) {
                throwParseException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = evenementSrv.getById(activityContext, id);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur = true;
                ret = new SAXException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                erreur = true;
                ret = new ParseException(" ", 1);
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }
}
