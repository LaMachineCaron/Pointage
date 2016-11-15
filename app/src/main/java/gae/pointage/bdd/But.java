package gae.pointage.bdd;

import com.orm.SugarRecord;

/**
 * Created by Res260 on 14/11/2016.
 */

public class But extends SugarRecord {

	protected Partie partie;
	protected Joueur compteur;
	protected Joueur assistant1;
	protected Joueur assistant2;
	protected int temps;
	protected int periode;

	/**
	 * Constructeur pour un but.
	 */
	public But() {}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Joueur getCompteur() {
		return compteur;
	}

	public void setCompteur(Joueur compteur) {
		this.compteur = compteur;
	}

	public Joueur getAssistant1() {
		return assistant1;
	}

	public void setAssistant1(Joueur assistant1) {
		this.assistant1 = assistant1;
	}

	public Joueur getAssistant2() {
		return assistant2;
	}

	public void setAssistant2(Joueur assistant2) {
		this.assistant2 = assistant2;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}
}
