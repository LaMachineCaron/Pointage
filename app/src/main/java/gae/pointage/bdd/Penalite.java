package gae.pointage.bdd;

import com.orm.SugarRecord;

/**
 * Created by Res260 on 14/11/2016.
 */

public class Penalite extends SugarRecord {
	protected Partie partie;
	protected Joueur joueur;
	protected Equipe equipe;
	protected Infraction infraction;
	protected int tempsDebut;
	protected int periode;

	public Penalite() {}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public int getTempsDebut() {
		return tempsDebut;
	}

	public void setTempsDebut(int tempsDebut) {
		this.tempsDebut = tempsDebut;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Infraction getInfraction() {
		return infraction;
	}

	public void setInfraction(Infraction infraction) {
		this.infraction = infraction;
	}
}
