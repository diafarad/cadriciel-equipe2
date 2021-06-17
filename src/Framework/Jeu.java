package Framework;

import java.util.ArrayList;
import java.util.Random;

/**
 * Initialisation avec le patron Singleton
 *
 */

public class Jeu {

    private static Jeu instance = new Jeu();

    private boolean etat = false;

    private String joueurActuel;
    private int compteur; //Pour test
    /**
     *
     */
    private Jeu(){

    }

    /**
     *
     * @return
     */
    public static Jeu getInstance(){
        return instance;
    }

    //TODO - Les autres méthodes de gestion du jeu selon le UML dans l'énoncé
    // et celui fait par nous

    /**
     *
     * @param etat
     */
    public void desLancement(boolean etat){
        this.etat = etat;
        this.joueurActuel = "Test " + compteur++; //Pour test
    }

    /**
     *
     * @return
     */
    public String nomJoueurTest(){

        return this.joueurActuel;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> nouvelleValeursTest(){

        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        list.add(rand.nextInt(100));
        list.add(rand.nextInt(100));
        list.add(rand.nextInt(100));
        list.add(rand.nextInt(100));

        return list;
    }
}
