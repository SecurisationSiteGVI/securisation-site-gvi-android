/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Acces;
import metier.entitys.Photo;

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
        try {
            this.photo = (Photo) this.evenementSrv.getById(this.activityContext, this.id);
        } catch (Exception ex) {
            Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setThisActivityOn();


    }

    @Override
    public void initGraphicalObjects() {
        this.buttonretour=(Button) findViewById(R.id.buttonPhotoRetour);
        this.textViewDate = (TextView) findViewById(R.id.textViewPhotoDate);
        this.textViewPhoto = (TextView) findViewById(R.id.textViewPhoto);
        this.imageView = (ImageView) findViewById(R.id.ImageViewPhoto);
        this.textViewCamera = (TextView)findViewById(R.id.textViewCamera);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonretour.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
    }

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
        }this.textViewPhoto.setText("Connectez vous sur le site pour visionner la photo");
        this.textViewCamera.setText(this.photo.getCamera().toString());
    }
}
