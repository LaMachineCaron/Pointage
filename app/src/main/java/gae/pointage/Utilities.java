package gae.pointage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandre on 2016-11-14.
 */
public class Utilities {

    /**
     * Donne le temps formatté selon le temps passé en argument.
     * @return Le temps en string formatté restant.
     */
    static public String formatterTemps(long tempsRestant){
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(tempsRestant),
                TimeUnit.MILLISECONDS.toSeconds(tempsRestant) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempsRestant)));
    }
}
