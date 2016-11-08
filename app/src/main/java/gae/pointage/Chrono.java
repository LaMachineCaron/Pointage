package gae.pointage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandre on 2016-11-01.
 */
public class Chrono {

    private boolean enMarche;
    private boolean aCommence = false;
    public long tempsRestant;
    private long tempsPasse;
    private Thread thread;

    /**
     * Constructeur de la classe Chrono
     * Crée la procédure run() qui s'éxecute au démarrage du chrono.
     * @param tempsInitial Le temps en milliseconde que le chrono doit avoir initialement.
     */
    public Chrono(final long tempsInitial){
        this.tempsRestant = tempsInitial;
        this.thread = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    if (enMarche) {
                        System.out.println("Chrono en marche.");
                        System.out.println(getTempsRestantFormate());
                        tempsPasse += 1000;
                        tempsRestant = tempsInitial - tempsPasse;
                        onTick();                    }
                    try {
                        System.out.println("Chrono fait dodo.");
                        thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Code d'exécutant à chaque boucle du chorno lorsqu'il est en marche.
     * Doit être override pour permettre d'y mettre du code custom.
     */
    public void onTick(){

    }

    /**
     * Commence le chrono et le thread.
     */
    public void commencer(){
        if (aCommence){
            this.enMarche = true;
        } else {
            this.enMarche = true;
            this.aCommence = true;
            this.thread.start();
        }

    }

    /**
     * Donne le temps formatté qu'il reste au chrono.
     * @return Le temps en string formatté restant.
     */
    public String getTempsRestantFormate(){
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(this.tempsRestant),
                TimeUnit.MILLISECONDS.toSeconds(this.tempsRestant) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.tempsRestant)));
    }

    /**
     * Met le chrono sur pause.
     */
    public void pause(){
        this.enMarche = false;
    }

    /**
     * Indique si le chrono est en marche
     * @return un boolean indiquant si le chrono est en marche.
     */
    public boolean estEnMarche() {
        return this.enMarche;
    }
}
