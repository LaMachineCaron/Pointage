package gae.pointage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EntreeDonneesActivity extends AppCompatActivity {

    private EditText editTextEndroit, editTextVille, editTextDivision, editTextClasse,
            editTextNumPartie, editTextLigue, editTextEquipeLocale, editTextEquipeVisiteur,
            editTextJugeBut1, editTextJugeBut2, editTextJugeLigne1, editTextJugeLigne2,
            editTextAnnonceur, editTextChronometreur, editTextMarqueur, editTextArbitre;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_entree_donnees);

        sharedPreferences = this.getSharedPreferences("Pointage", 0);

       Button boutonTermine = (Button) findViewById(R.id.button_suivant);
        boutonTermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                termine();
            }
        });

        editTextEndroit = (EditText) findViewById(R.id.edittext_endroit);
        editTextEndroit.setText(sharedPreferences.getString("Endroit", ""));
        editTextVille = (EditText) findViewById(R.id.edittext_ville);
        editTextVille.setText(sharedPreferences.getString("Ville", ""));
        editTextDivision = (EditText) findViewById(R.id.edittext_division);
        editTextDivision.setText(sharedPreferences.getString("Division", ""));
        editTextClasse = (EditText) findViewById(R.id.edittext_classe);
        editTextClasse.setText(sharedPreferences.getString("Classe", ""));
        editTextNumPartie = (EditText) findViewById(R.id.edittext_num_partie);
        editTextNumPartie.setText(sharedPreferences.getString("NumPartie", ""));
        editTextLigue = (EditText) findViewById(R.id.edittext_ligue);
        editTextLigue.setText(sharedPreferences.getString("Ligue", ""));
        editTextEquipeLocale = (EditText) findViewById(R.id.edittext_equipe_loc);
        editTextEquipeLocale.setText(sharedPreferences.getString("NomEquipeLocale", ""));
        editTextEquipeVisiteur = (EditText) findViewById(R.id.edittext_equipe_vis);
        editTextEquipeVisiteur.setText(sharedPreferences.getString("NomEquipeVisiteur", ""));
        editTextJugeBut1 = (EditText) findViewById(R.id.edittext_juge_but1);
        editTextJugeBut1.setText(sharedPreferences.getString("NomJugeBut1", ""));
        editTextJugeBut2 = (EditText) findViewById(R.id.edittext_juge_but2);
        editTextJugeBut2.setText(sharedPreferences.getString("NomJugeBut2", ""));
        editTextJugeLigne1 = (EditText) findViewById(R.id.edittext_juge_ligne1);
        editTextJugeLigne1.setText(sharedPreferences.getString("NomJugeLigne1", ""));
        editTextJugeLigne2 = (EditText) findViewById(R.id.edittext_juge_ligne2);
        editTextJugeLigne2.setText(sharedPreferences.getString("NomJugeLigne2", ""));
        editTextAnnonceur = (EditText) findViewById(R.id.edittext_annonceur);
        editTextAnnonceur.setText(sharedPreferences.getString("NomAnnonceur", ""));
        editTextChronometreur = (EditText) findViewById(R.id.edittext_chrono);
        editTextChronometreur.setText(sharedPreferences.getString("NomChrono", ""));
        editTextMarqueur = (EditText) findViewById(R.id.edittext_marqueur);
        editTextMarqueur.setText(sharedPreferences.getString("NomMarqueur", ""));
        editTextArbitre = (EditText) findViewById(R.id.edittext_arbitre);
        editTextArbitre.setText(sharedPreferences.getString("NomArbitre", ""));
    }

    /**
     * Termine l'activité. Lance la prochaine activité.
     */
    private void termine() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateAujourdhui = cal.getTime();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Endroit", editTextEndroit.getText().toString());
        editor.putString("Ville", editTextVille.getText().toString());
        editor.putLong("Date", dateAujourdhui.getTime());
        editor.putString("Division", editTextDivision.getText().toString());
        editor.putString("Classe", editTextClasse.getText().toString());
        editor.putString("NumPartie", editTextNumPartie.getText().toString());
        editor.putString("Ligue", editTextLigue.getText().toString());
        editor.putString("NomEquipeLocale", editTextEquipeLocale.getText().toString());
        editor.putString("NomEquipeVisiteur", editTextEquipeVisiteur.getText().toString());
        editor.putString("NomJugeBut1", editTextJugeBut1.getText().toString());
        editor.putString("NomJugeBut2", editTextJugeBut2.getText().toString());
        editor.putString("NomJugeLigne1", editTextJugeLigne1.getText().toString());
        editor.putString("NomJugeLigne2", editTextJugeLigne2.getText().toString());
        editor.putString("NomAnnonceur", editTextAnnonceur.getText().toString());
        editor.putString("NomChrono", editTextChronometreur.getText().toString());
        editor.putString("NomMarqueur", editTextMarqueur.getText().toString());
        editor.putString("NomArbitre", editTextArbitre.getText().toString());
        editor.apply();

        Intent intent = new Intent(this, EntreeDonneesActivity.class);
        startActivity(intent);
    }
}
