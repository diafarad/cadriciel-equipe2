package LancementBunco;

/**
 *
 */

public class lancementBunco {

    public static void main(String[] args) {

        Thread t = new Thread(new Gui.CadreChoixJoueur());
        t.start();
    }
}
