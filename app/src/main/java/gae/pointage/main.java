package gae.pointage;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import gae.pointage.bdd.Equipe;
import gae.pointage.bdd.Joueur;
import gae.pointage.bdd.Partie;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class main extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private ListView listviewEquipe1, listviewEquipe2;
    private JoueurAdapter joueurAdapterEquipe1, joueurAdapterEquipe2;
    private static final String FORMAT = "%02d:%02d";
    private TextView chronoTexte;
    private Equipe equipe1, equipe2;
    private ImageButton boutonPlayPause;
    private Chrono chrono;
    private Joueur[] assists = {null, null};
    private int Periode = 1;
    private TextView pointageTexteEquipe1, pointageTexteEquipe2;
    private int pointageEquipe1 = 0, pointageEquipe2 = 0;
    private Partie partie = new Partie();

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mVisible = true;
        this.chronoTexte = (TextView) findViewById(R.id.chrono);
        this.boutonPlayPause = (ImageButton) findViewById(R.id.play_pause);
        BDD.init();
        this.equipe1 = (Equipe) Equipe.find(Equipe.class, "id=?", "1").get(0);
        this.equipe2 = (Equipe) Equipe.find(Equipe.class, "id=?", "2").get(0);

        this.pointageTexteEquipe1 = (TextView)findViewById(R.id.textview_pointage_equipe_1);
        this.pointageTexteEquipe2 = (TextView)findViewById(R.id.textview_pointage_equipe_2);

        this.pointageTexteEquipe1.setText(String.valueOf(this.pointageEquipe1));
        this.pointageTexteEquipe2.setText(String.valueOf(this.pointageEquipe2));

        this.joueurAdapterEquipe1 = new JoueurAdapter(this, equipe1.getJoueurs(), R.layout.joueur_element_listview_inverse);
        this.joueurAdapterEquipe2 = new JoueurAdapter(this, equipe2.getJoueurs(), R.layout.joueur_element_listview);

        this.listviewEquipe1 = (ListView)findViewById(R.id.listview_equipe_1);
        this.listviewEquipe1.setAdapter(this.joueurAdapterEquipe1);

        this.listviewEquipe2 = (ListView)findViewById(R.id.listview_equipe_2);
        this.listviewEquipe2.setAdapter(this.joueurAdapterEquipe2);

        this.chrono = new Chrono(1200000){
            @Override
            public void onTick() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chronoTexte.setText(chrono.getTempsRestantFormate());
                    }
                });
            }
        };

        ImageButton button = (ImageButton) findViewById(R.id.play_pause);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!chrono.estEnMarche()){
                   System.out.println("Bouton Play appuyé.");
                   playChrono();
               }else{
                   System.out.println("Bouton Pause appuyé.");
                   pauseChrono();
               }
            }
        });
    }

    private void playChrono(){
        boutonPlayPause.setImageResource(R.drawable.pause);
        chrono.commencer();
    }

    private void pauseChrono(){
        if (this.chrono.estEnMarche()){
            this.boutonPlayPause.setImageResource(R.drawable.play);
            this.chrono.pause();
        }
    }

    public void penalite(Joueur joueur){
        pauseChrono();
        System.out.println("Bouuuhhhh: " + joueur.getNom());
    }

    public void assist(Joueur joueur){
        pauseChrono();
        System.out.println("Cet assist provient de " + joueur.getNom());
    }

    public void ajouterAssist(Joueur joueur) {
        this.assists[1] = this.assists[0];
        this.assists[0] = joueur;
    }

    public void retirerAssist(Joueur joueur) {
        if (this.assists[0] == joueur) {
            this.assists[0] = this.assists[1];
        }
        this.assists[1] = null;
    }

    public void viderAssists() {
        this.assists[0] = null;
        this.assists[1] = null;
    }

    public void but(Joueur joueur) {
        pauseChrono();
        System.out.println("Ce but vous est présenté par: " + joueur.getNom());
        Joueur[] assistsCopie = this.assists.clone();
        this.viderAssists();
        //TODO: comptabiliser le but
        BDD.ajouterBut(this.partie, joueur, this.assists[1], this.assists[0], this.chrono.tempsRestant, this.Periode);
        if (this.equipe1 == joueur.getEquipe()) {
            this.pointageEquipe1 += 1;
            this.pointageTexteEquipe1.setText(String.valueOf(this.pointageEquipe1));
        } else {
            this.pointageEquipe2 += 1;
            this.pointageTexteEquipe2.setText(String.valueOf(this.pointageEquipe2));
        }
        System.out.println("Ce but vous est présenté par: " + joueur.getNom());
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }
}