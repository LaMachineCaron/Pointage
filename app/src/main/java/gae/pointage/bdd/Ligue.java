package gae.pointage.bdd;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Res260 on 14/11/2016.
 */

public class Ligue extends SugarRecord {

    protected String nom;

    public Ligue() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
