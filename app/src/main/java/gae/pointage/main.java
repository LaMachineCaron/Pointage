package gae.pointage;

import android.annotation.SuppressLint;
<<<<<<< HEAD
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
=======
import android.media.Image;
import android.os.CountDownTimer;
>>>>>>> wip_feuille_pointage
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

import java.util.HashMap;
import java.util.Map;

import gae.pointage.bdd.But;
import gae.pointage.bdd.Equipe;
import gae.pointage.bdd.Infraction;
import gae.pointage.bdd.Joueur;
import gae.pointage.bdd.Partie;
import gae.pointage.bdd.Penalite;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class main extends AppCompatActivity {

	// MA SHIT
	/**
	 * The bdd object for the application.
	 */
	SQLiteDatabase bdd;
	//FIN MA SHIT

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
    private ArrayList<Joueur> equipe1, equipe2;
    private ImageButton boutonPlayPause;
    private Chrono chrono;

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

        this.equipe1 = new ArrayList<>();
        this.equipe2 = new ArrayList<>();
        this.equipe1.add(new Joueur("Alexandre", 45)); this.equipe1.add(new Joueur("Guillaume", 42));
        this.equipe2.add(new Joueur("Jacques", 4)); this.equipe2.add(new Joueur("Mathieu", 2));

        this.joueurAdapterEquipe1 = new JoueurAdapter(this, equipe1, R.layout.joueur_element_listview_inverse);
        this.joueurAdapterEquipe2 = new JoueurAdapter(this, equipe2, R.layout.joueur_element_listview);

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

<<<<<<< HEAD
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        //-----------------MON CODE--------------------
		Equipe uneEquipe = new Equipe();
		uneEquipe.setNom("Les tigres");
		uneEquipe.save();
        Joueur unJoueur = new Joueur();
        unJoueur.setNumero("260");
        unJoueur.setNom("Gonzo");
        unJoueur.setEquipe(uneEquipe);
        unJoueur.save();
        Partie unePartie = new Partie();
        unePartie.setEquipeLocale(uneEquipe);
        unePartie.setEquipeVisiteur(uneEquipe);
		unePartie.setClasse("classe1");
        unePartie.save();
		unePartie.setClasse("classe2");
		unePartie.save();
		//-----------------FIN MON CODE--------------------

    }

	/**
     * Sauvegarde un but dans la base de données.
     * @param partie La partie en cours
     * @param compteur Le joueur qui a compté le but
     * @param assistant1 Le joueur qui a la dernière passe
     * @param assistant2 Le joueur qui a la première passe
     * @param temps Le temps en ms de la période en cours
     * @param periode La numéro de la période
     */
    public static void ajouterBut(Partie partie, Joueur compteur, Joueur assistant1,
                                  Joueur assistant2, int temps, int periode) {
        But but = new But();
        but.setPartie(partie);
        but.setCompteur(compteur);
        but.setAssistant1(assistant1);
        but.setAssistant2(assistant2);
        but.setTemps(temps);
        but.setPeriode(periode);
        but.save();
    }

	/**
	 * Ajoute une equipe à la BDD.
	 * @param nom Le nom de l'équipe
	 */
	public static void ajouterEquipe(String nom) {
		Equipe equipe = new Equipe();
		equipe.setNom(nom);
		equipe.save();
	}

	/**
	 * Ajoute un joueur à la BDD.
	 * @param nom		 Le nom du joueur
	 * @param numero	 Le numéro du joueur
	 * @param equipe	 L'équipe du joueur
	 * @param estGardien true si c'est un gardien
	 */
	public static void ajouterJoueur(String nom, String numero, Equipe equipe, boolean estGardien) {
		Joueur joueur = new Joueur();
		joueur.setNom(nom);
		joueur.setNumero(numero);
		joueur.setEquipe(equipe);
		joueur.setEstGardien(estGardien);
		joueur.save();
	}

	/**
	 * Ajoute une pénalité à la BDD.
	 * @param partie	 La partie en cours
	 * @param infraction L'infraction qui a causé la pénalité
	 * @param equipe	 L'équipe du joueur
	 * @param joueur	 Le joueur qui a reçu la pénalité
	 * @param tempsDebut Le temps en ms du début de la pénalité
	 * @param periode	 Le numéro de la période de la pénalité
	 */
	public static void ajouterPenalite(Partie partie, Infraction infraction, Equipe equipe,
									   Joueur joueur, int tempsDebut, int periode) {
		Penalite penalite = new Penalite();
		penalite.setPartie(partie);
		penalite.setInfraction(infraction);
		penalite.setEquipe(equipe);
		penalite.setJoueur(joueur);
		penalite.setTempsDebut(tempsDebut);
		penalite.setPeriode(periode);
	}

	/**
	 * Ajoute les types d'infractions à la BDD.
	 */
    public static void ajouterInfractions() {
		Infraction infraction1 = new Infraction();
		infraction1.setNom("Bâton élevé ;)");
		infraction1.setDescription("u know what I mean");
		infraction1.setTemps(2*60*1000);
		infraction1.save();

		Infraction infraction2 = new Infraction();
		infraction1.setNom("Être pas fin");
		infraction1.setDescription("hihi");
		infraction1.setTemps(5*60*1000);
		infraction1.save();
	}

	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
=======
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
>>>>>>> wip_feuille_pointage

    public void penalite(Joueur joueur){
        pauseChrono();
        System.out.println("Bouuuhhhh: " + joueur.nom);
    }

    public void assist(Joueur joueur){
        pauseChrono();
        System.out.println("Cet assist provient de " + joueur.nom);
    }

    public void but(Joueur joueur){
        pauseChrono();
        System.out.println("Ce but vous est présenté par: " + joueur.nom);
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