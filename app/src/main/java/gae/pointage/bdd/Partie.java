package gae.pointage.bdd;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Res260 on 14/11/2016.
 */
public class Partie extends SugarRecord {

	protected Equipe equipeLocale;
	protected Equipe equipeVisiteur;

	protected int scoreEquipeLocale;
	protected int scoreEquipeVisiteur;

	protected int tempsDebut;
	protected int tempsFin;

	protected String endroit;
	protected String ville;
	protected int date;
	protected String division;
	protected String classe;
	protected String numeroPartie;
	protected String ligue;

	protected String nomJugeDeBut1;
	protected String nomJugeDeBut2;
	protected String nomChronometreur;
	protected String nomAnnonceur;
	protected String nomMarqueur;
	protected String nomJugeDeLigne1;
	protected String nomJugeDeLigne2;
	protected String nomArbitre;

	/**
	 * Constructeur pour une partie.
	 */
	public Partie() {}

	public List<Penalite> getPenalites() {
		return Penalite.find(Penalite.class, "partie = ?", this.getId().toString());
	}

	public List<Penalite> getPenalitesEquipeLocale() {
		return Penalite.find(Penalite.class, "partie = ? AND equipe = ?",
				this.getId().toString() + " " + this.equipeLocale.getId().toString());
	}

	public List<Penalite> getPenalitesEquipeVisiteur() {
		return Penalite.find(Penalite.class, "partie = ? AND equipe = ?",
				this.getId().toString() + " " + this.equipeVisiteur.getId().toString());
	}

	public List<But> getButs() {
		return But.find(But.class, "partie = ?", this.getId().toString());
	}

	public Equipe getEquipeLocale() {
		return equipeLocale;
	}

	public void setEquipeLocale(Equipe equipeLocale) {
		this.equipeLocale = equipeLocale;
	}

	public Equipe getEquipeVisiteur() {
		return equipeVisiteur;
	}

	public void setEquipeVisiteur(Equipe equipeVisiteur) {
		this.equipeVisiteur = equipeVisiteur;
	}

	public int getScoreEquipeLocale() {
		return scoreEquipeLocale;
	}

	public void setScoreEquipeLocale(int scoreEquipeLocale) {
		this.scoreEquipeLocale = scoreEquipeLocale;
	}

	public int getScoreEquipeVisiteur() {
		return scoreEquipeVisiteur;
	}

	public void setScoreEquipeVisiteur(int scoreEquipeVisiteur) {
		this.scoreEquipeVisiteur = scoreEquipeVisiteur;
	}

	public int getTempsDebut() {
		return tempsDebut;
	}

	public void setTempsDebut(int tempsDebut) {
		this.tempsDebut = tempsDebut;
	}

	public int getTempsFin() {
		return tempsFin;
	}

	public void setTempsFin(int tempsFin) {
		this.tempsFin = tempsFin;
	}

	public String getNomJugeDeBut1() {
		return nomJugeDeBut1;
	}

	public void setNomJugeDeBut1(String nomJugeDeBut1) {
		this.nomJugeDeBut1 = nomJugeDeBut1;
	}

	public String getNomChronometreur() {
		return nomChronometreur;
	}

	public void setNomChronometreur(String nomChronometreur) {
		this.nomChronometreur = nomChronometreur;
	}

	public String getNomAnnonceur() {
		return nomAnnonceur;
	}

	public void setNomAnnonceur(String nomAnnonceur) {
		this.nomAnnonceur = nomAnnonceur;
	}

	public String getEndroit() {
		return endroit;
	}

	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNumeroPartie() {
		return numeroPartie;
	}

	public void setNumeroPartie(String numeroPartie) {
		this.numeroPartie = numeroPartie;
	}

	public String getLigue() {
		return ligue;
	}

	public void setLigue(String ligue) {
		this.ligue = ligue;
	}

	public String getNomJugeDeBut2() {
		return nomJugeDeBut2;
	}

	public void setNomJugeDeBut2(String nomJugeDeBut2) {
		this.nomJugeDeBut2 = nomJugeDeBut2;
	}

	public String getNomMarqueur() {
		return nomMarqueur;
	}

	public void setNomMarqueur(String nomMarqueur) {
		this.nomMarqueur = nomMarqueur;
	}

	public String getNomJugeDeLigne1() {
		return nomJugeDeLigne1;
	}

	public void setNomJugeDeLigne1(String nomJugeDeLigne1) {
		this.nomJugeDeLigne1 = nomJugeDeLigne1;
	}

	public String getNomJugeDeLigne2() {
		return nomJugeDeLigne2;
	}

	public void setNomJugeDeLigne2(String nomJugeDeLigne2) {
		this.nomJugeDeLigne2 = nomJugeDeLigne2;
	}

	public String getNomArbitre() {
		return nomArbitre;
	}

	public void setNomArbitre(String nomArbitre) {
		this.nomArbitre = nomArbitre;
	}
}
