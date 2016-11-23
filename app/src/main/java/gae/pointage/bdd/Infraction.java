package gae.pointage.bdd;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Res260 on 14/11/2016.
 */

public class Infraction extends SugarRecord {

	protected String nom;
	protected String description;
	protected int temps;

	public Infraction() {}

	public List<Penalite> getPenalites() {
		return Penalite.find(Penalite.class, "infraction = ?", this.getId().toString());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}
}
