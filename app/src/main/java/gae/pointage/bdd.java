package gae.pointage;

import gae.pointage.bdd.*;
import gae.pointage.bdd.Joueur;

/**
 * Created by Alexandre on 2016-11-21.
 */
public class BDD {

    public static void init(){

        Equipe uneEquipe = new Equipe();
        uneEquipe.setNom("Les noobs");
        uneEquipe.save();
        Equipe uneAutreEquipe = new Equipe();
        uneAutreEquipe.setNom("Les fixeurs");
        uneAutreEquipe.save();
        gae.pointage.bdd.Joueur unJoueur = new gae.pointage.bdd.Joueur();
        unJoueur.setNumero("260");
        unJoueur.setNom("Gonzo");
        unJoueur.setEquipe(uneEquipe);
        unJoueur.save();
        gae.pointage.bdd.Joueur unAutreJoueur = new gae.pointage.bdd.Joueur();
        unAutreJoueur.setNumero("42");
        unAutreJoueur.setNom("Caron");
        unAutreJoueur.setEquipe(uneEquipe);
        unAutreJoueur.save();
        Partie unePartie = new Partie();
        unePartie.setEquipeLocale(uneEquipe);
        unePartie.setEquipeVisiteur(uneAutreEquipe);
        unePartie.setClasse("classe1");
        unePartie.save();
        unePartie.setClasse("classe2");
        unePartie.save();
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
    public static void ajouterBut(Partie partie, gae.pointage.bdd.Joueur compteur, gae.pointage.bdd.Joueur assistant1,
                                  gae.pointage.bdd.Joueur assistant2, int temps, int periode) {
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
    public static void ajouterPenalite(Partie partie, Infraction infraction, Equipe equipe,
                                       Joueur joueur, int tempsDebut, int periode) {
        Penalite penalite = new Penalite();
        penalite.setPartie(partie);
        penalite.setInfraction(infraction);
        penalite.setEquipe(equipe);
        penalite.setJoueur(joueur);
        penalite.setTempsDebut(tempsDebut);
        penalite.setPeriode(periode);
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
        infraction1.setNom("Être pas fin");
        infraction1.setDescription("hihi");
        infraction1.setTemps(5*60*1000);
        infraction1.save();
    }
}
