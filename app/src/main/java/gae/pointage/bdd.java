package gae.pointage;

import gae.pointage.bdd.*;
import gae.pointage.bdd.Joueur;

/**
 * Created by Alexandre on 2016-11-21.
 */
public class BDD {

    public static void init(){

        BDD.viderTables();
        Ligue ligue1 = new Ligue();
        ligue1.setNom("Ligue AA");
        ligue1.save();
        Ligue ligue2 = new Ligue();
        ligue2.setNom("Ligue BB");
        ligue2.save();
        Equipe uneEquipe = new Equipe();
        uneEquipe.setNom("Tigres");
        uneEquipe.setLigue(ligue1);
        uneEquipe.save();
        Equipe uneAutreEquipe = new Equipe();
        uneAutreEquipe.setNom("Cougars");
        uneAutreEquipe.setLigue(ligue1);
        uneAutreEquipe.save();
        gae.pointage.bdd.Joueur unJoueur = new gae.pointage.bdd.Joueur();
        unJoueur.setNumero("260");
        unJoueur.setNom("Gonzo");
        unJoueur.setEquipe(uneEquipe);
        unJoueur.save();
        gae.pointage.bdd.Joueur unAutreJoueur = new gae.pointage.bdd.Joueur();
        unAutreJoueur.setNumero("42");
        unAutreJoueur.setNom("Caron");
        unAutreJoueur.setEquipe(uneAutreEquipe);
        unAutreJoueur.save();
        gae.pointage.bdd.Joueur unGardien = new gae.pointage.bdd.Joueur();
        unGardien.setNumero("420");
        unGardien.setNom("Jombo");
        unGardien.setEquipe(uneEquipe);
        unGardien.setEstGardien(true);
        unGardien.save();
        gae.pointage.bdd.Joueur unAutreGardien = new gae.pointage.bdd.Joueur();
        unAutreGardien.setNumero("421");
        unAutreGardien.setNom("Jambo");
        unAutreGardien.setEquipe(uneAutreEquipe);
        unAutreGardien.setEstGardien(true);
        unAutreGardien.save();
        BDD.ajouterInfractions();
    }

    private static void viderTables() {
        Equipe.deleteAll(Equipe.class);
        Joueur.deleteAll(Joueur.class);
        Partie.deleteAll(Partie.class);
        Infraction.deleteAll(Infraction.class);
        But.deleteAll(But.class);
        Penalite.deleteAll(Penalite.class);
        Ligue.deleteAll(Ligue.class);

    }

    /**
     * Sauvegarde un but dans la base de données.
     * @param partie La partie en cours
     * @param compteur Le joueur qui a compté le but
     * @param assistant1 Le joueur qui a la dernière passe
     * @param assistant2 Le joueur qui a la première passe
     * @param temps Le temps en ms de la période en cours
     * @param periode La numéro de la période
     */
    public static void ajouterBut(Partie partie, Joueur compteur, Joueur assistant1,
                                  Joueur assistant2, int temps, int periode) {
        But but = new But();
        but.setPartie(partie);
        but.setCompteur(compteur);
        but.setAssistant1(assistant1);
        but.setAssistant2(assistant2);
        but.setTemps(temps);
        but.setPeriode(periode);
        but.save();
    }

    /**
     * Ajoute une equipe à la BDD.
     * @param nom Le nom de l'équipe
     */
    public static void ajouterEquipe(String nom) {

        Equipe equipe = new Equipe();
        equipe.setNom(nom);
        equipe.save();
    }

    /**
     * Ajoute un joueur à la BDD.
     * @param nom		 Le nom du joueur
     * @param numero	 Le numéro du joueur
     * @param equipe	 L'équipe du joueur
     * @param estGardien true si c'est un gardien
     */
    public static void ajouterJoueur(String nom, String numero, Equipe equipe, boolean estGardien) {
        gae.pointage.bdd.Joueur joueur = new gae.pointage.bdd.Joueur();
        joueur.setNom(nom);
        joueur.setNumero(numero);
        joueur.setEquipe(equipe);
        joueur.setEstGardien(estGardien);
        joueur.save();
    }

    /**
     * Ajoute une pénalité à la BDD.
     * @param partie	 La partie en cours
     * @param infraction L'infraction qui a causé la pénalité
     * @param equipe	 L'équipe du joueur
     * @param joueur	 Le joueur qui a reçu la pénalité
     * @param tempsDebut Le temps en ms du début de la pénalité
     * @param periode	 Le numéro de la période de la pénalité
     */
    public static Penalite ajouterPenalite(Partie partie, Infraction infraction, Equipe equipe,
                                       Joueur joueur, int tempsDebut, int periode) {
        Penalite penalite = new Penalite();
        penalite.setPartie(partie);
        penalite.setInfraction(infraction);
        penalite.setEquipe(equipe);
        penalite.setJoueur(joueur);
        penalite.setTempsDebut(tempsDebut);
        penalite.setPeriode(periode);
        return penalite;
    }

    /**
     * Ajoute les types d'infractions à la BDD.
     */
    public static void ajouterInfractions() {
        Infraction infraction1 = new Infraction();
        infraction1.setNom("Bâton élevé ;)");
        infraction1.setDescription("u know what I mean");
        infraction1.setTemps(2*60*1000);
        infraction1.save();

        Infraction infraction2 = new Infraction();
        infraction2.setNom("Être pas fin");
        infraction2.setDescription("hihi");
        infraction2.setTemps(5*60*1000);
        infraction2.save();
    }
}
