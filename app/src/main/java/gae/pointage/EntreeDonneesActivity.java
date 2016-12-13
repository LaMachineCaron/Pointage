package gae.pointage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import gae.pointage.bdd.Equipe;
import gae.pointage.bdd.Infraction;
import gae.pointage.bdd.Joueur;
import gae.pointage.bdd.Ligue;
import gae.pointage.bdd.Partie;
import gae.pointage.bdd.Penalite;

public class EntreeDonneesActivity extends AppCompatActivity {

    private EditText editTextEndroit, editTextVille, editTextDivision, editTextClasse,
            editTextNumPartie, editTextLigue,
            editTextJugeBut1, editTextJugeBut2, editTextJugeLigne1, editTextJugeLigne2,
            editTextAnnonceur, editTextChronometreur, editTextMarqueur, editTextArbitre;

    private Spinner spinnerEquipeLoc, spinnerEquipeVis;
    private Ligue ligue;
    private Button boutonTermine, boutonLigue;
    private List<String> liste = new LinkedList<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_entree_donnees);

        sharedPreferences = this.getSharedPreferences("Pointage", 0);

        BDD.init();

        boutonTermine = (Button) findViewById(R.id.button_suivant);
        boutonTermine.setEnabled(false);

        long nombreLigue = Ligue.count(Ligue.class);
        final CharSequence[] ligues = new CharSequence[(int) nombreLigue];

        int i = 0;
        for(Ligue ligue : Ligue.listAll(Ligue.class)) {
            ligues[i] = ligue.getNom();
            i++;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisissez la ligue");
        builder.setItems(ligues, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               ligue = Ligue.find(Ligue.class, "nom=?", ligues[which].toString()).get(0);

                for (Equipe equipe : Equipe.listAll(Equipe.class)) {
                    if (ligue.getId() == equipe.getLigue().getId()) {
                        liste.add(equipe.getNom());
                    }
                }
                ((ArrayAdapter<String>) spinnerEquipeLoc.getAdapter()).notifyDataSetChanged();
                ((ArrayAdapter<String>) spinnerEquipeVis.getAdapter()).notifyDataSetChanged();
                boutonTermine.setEnabled(true);
            }
        }); // TODO: Finir la ligue
        builder.show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, liste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



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
        //editTextLigue = (EditText) findViewById(R.id.edittext_ligue);
        //editTextLigue.setText(sharedPreferences.getString("Ligue", ""));
        spinnerEquipeLoc = (Spinner) findViewById(R.id.spinner_equipe_loc);
        spinnerEquipeLoc.setAdapter(adapter);
        spinnerEquipeVis = (Spinner) findViewById(R.id.spinner_equipe_vis);
        spinnerEquipeVis.setAdapter(adapter);
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
        //editor.putString("Ligue", editTextLigue.getText().toString());
        editor.putString("NomEquipeLocale", spinnerEquipeLoc.getSelectedItem().toString());
        editor.putString("NomEquipeVisiteur", spinnerEquipeVis.getSelectedItem().toString());
        editor.putString("NomJugeBut1", editTextJugeBut1.getText().toString());
        editor.putString("NomJugeBut2", editTextJugeBut2.getText().toString());
        editor.putString("NomJugeLigne1", editTextJugeLigne1.getText().toString());
        editor.putString("NomJugeLigne2", editTextJugeLigne2.getText().toString());
        editor.putString("NomAnnonceur", editTextAnnonceur.getText().toString());
        editor.putString("NomChrono", editTextChronometreur.getText().toString());
        editor.putString("NomMarqueur", editTextMarqueur.getText().toString());
        editor.putString("NomArbitre", editTextArbitre.getText().toString());
        editor.apply();
        
        this.save();

        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }
    
    private void save()
    {
        Partie partie = new Partie();
        Equipe equipeLocale = this.findOrCreate(sharedPreferences.getString("NomEquipeLocale", ""));
        Equipe equipeVisiteur = this.findOrCreate(sharedPreferences.getString("NomEquipeVisiteur", ""));

        partie.setClasse(sharedPreferences.getString("Classe", ""));
        partie.setEndroit(sharedPreferences.getString("Endroit", ""));
        partie.setDivision(sharedPreferences.getString("Division", ""));
        partie.setDate((int) sharedPreferences.getLong("Date", 0));
        partie.setNomArbitre(sharedPreferences.getString("NomArbitre", ""));
        partie.setNomMarqueur(sharedPreferences.getString("NomMarqueur", ""));

        partie.setNumeroPartie(sharedPreferences.getString("NumPartie", ""));
        partie.setLigue(sharedPreferences.getString("Ligue", ""));

        partie.setNomJugeDeBut1(sharedPreferences.getString("NomJugeBut1", ""));
        partie.setNomJugeDeBut2(sharedPreferences.getString("NomJugeBut2", ""));
        partie.setNomJugeDeLigne1(sharedPreferences.getString("NomJugeLigne1", ""));
        partie.setNomJugeDeLigne2(sharedPreferences.getString("NomJugeLigne2", ""));
        partie.setNomAnnonceur(sharedPreferences.getString("NomAnnonceur", ""));
        partie.setNomChronometreur(sharedPreferences.getString("NomChrono", ""));
        partie.setNomMarqueur(sharedPreferences.getString("NomMarqueur", ""));
        partie.setNomArbitre(sharedPreferences.getString("NomArbitre", ""));
        partie.save();
    }

    private Equipe findOrCreate(String name)
    {
        List<Equipe> liste = Equipe.find(Equipe.class, "nom = ?", name);
        Equipe equipe;
        if (liste.isEmpty()) {
            equipe = new Equipe();
            equipe.setNom(name);
            equipe.save();
        } else {
            equipe = liste.get(0);
        }
        return equipe;
    }
}
