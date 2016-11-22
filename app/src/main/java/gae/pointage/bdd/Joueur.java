package gae.pointage.bdd;

import com.orm.SugarRecord;

/**
 * Created by Res260 on 08/11/2016.
 */
public class Joueur extends SugarRecord {
	protected String nom;
	protected String numero;
	protected boolean EstGardien;
	protected Equipe equipe;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public boolean isEstGardien() {
		return EstGardien;
	}

	public void setEstGardien(boolean estGardien) {
		EstGardien = estGardien;
	}
}
