package gae.pointage.bdd;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Res260 on 08/11/2016.
 */
public class Equipe extends SugarRecord {
	protected String nom;

	/**
	 * Constructeur pour une équipe
	 */
	public Equipe() {}

	public List<Joueur> getJoueurs() {
		return Joueur.find(Joueur.class, "equipe = ?", this.getId().toString());
	}

	public List<Partie> getPartiesLocales() {
		return Partie.find(Partie.class, "equipeLocale = ?", this.getId().toString());
	}

	public List<Partie> getPartiesVisiteur() {
		return Partie.find(Partie.class, "equipeVisiteur = ?", this.getId().toString());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



}
