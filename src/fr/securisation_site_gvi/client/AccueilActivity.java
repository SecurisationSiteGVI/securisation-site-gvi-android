package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;
import java.net.URI;

public class AccueilActivity extends Activity {

    private VideoView videoView;
    private static ProgressDialog progressDialog;
    private String videoPath = "http://172.16.79.214/videostream.cgi?user=admin&pwd=marvin&resolution=32";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
//        this.videoView = (VideoView) findViewById(R.id.videoView1);
//MediaController mediaController = new MediaController(this);
//    mediaController.setAnchorView(videoView);
//    videoView.setMediaController(mediaController);
////    videoView.setOnPreparedListener(videoPreparedListener);
////    videoView.setOnErrorListener(videoErrorListener);
//    Uri video = Uri.parse(videoPath);
//    videoView.setVideoURI(video);

//        videoView.setMediaController(new MediaController(this))
//        progressDialog = ProgressDialog.show(AccueilActivity.this, "", "Buffering video...", true);
//        progressDialog.setCancelable(true);
//        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_accueil, menu);
        return true;
    }

    private void PlayVideo() {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(AccueilActivity.this);
            mediaController.setAnchorView(videoView);

            Uri video = Uri.parse(videoPath);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });


        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.toString());
            finish();
        }

    }
}
