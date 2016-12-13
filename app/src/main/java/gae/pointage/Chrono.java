package gae.pointage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandre on 2016-11-01.
 */
public class Chrono {

    private boolean enMarche;
    private boolean aCommence = false;
    public int periode = 1;
    public int tempsRestant;
    public int tempsPasse;
    public int tempsInitial;
    private Thread thread;

    /**
     * Constructeur de la classe Chrono
     * Crée la procédure run() qui s'éxecute au démarrage du chrono.
     * @param tempsInitial Le temps en milliseconde que le chrono doit avoir initialement.
     */
    public Chrono(final int tempsInitial){
        this.tempsInitial = tempsInitial;
        this.tempsRestant = tempsInitial;
        this.thread = new Thread(new Runnable() {
            public void run() {
                while(! thread.isInterrupted()) {
                    if (enMarche) {
                        if (tempsRestant > 0) {
                            System.out.println("Chrono en marche.");
                            System.out.println(getTempsRestantFormate());
                            tempsPasse += 1000;
                            tempsRestant = tempsInitial - tempsPasse;
                            onTick();
                        } else {
                            periodeTermine();
                        }
                    }
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

    private void periodeTermine()
    {
        if (this.periode == 3) {
            thread.interrupt();
        } else {
            this.periode = this.periode + 1;
            this.tempsRestant = this.tempsInitial;
        }
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
       return Utilities.formatterTemps(this.tempsRestant);
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
