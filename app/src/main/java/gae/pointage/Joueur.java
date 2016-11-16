package gae.pointage;

/**
 * Created by Alexandre on 2016-11-14.
 */
public class Joueur {

    public String nom;
    public int numero;
    public long temps_penalite_restant;

    public Joueur(String nom, int numero){
        this.nom = nom;
        this.numero = numero;
        this.temps_penalite_restant = 0;

    }
}
