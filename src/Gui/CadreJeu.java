package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CadreJeu extends JFrame implements Runnable {

    //Constante
    private static final int CADRE_LARGEUR = 700;
    private static final int CADRE_HAUTEUR = 500;
    private static final String NOM_JEU = "BUNCO+";

    private int nombreJoueur;

    public CadreJeu(int nombreJoueur){

        this.nombreJoueur = nombreJoueur;
    }

    /**
     *
     */
    @Override
    public void run() {

        configurerFrame();
    }

    /**
     * Configure le frame
     */
    private void configurerFrame() {

        //Dimension cadre
        setPreferredSize(new Dimension(CADRE_LARGEUR, CADRE_HAUTEUR));
        pack();
        setLocationRelativeTo(null);
        setTitle(NOM_JEU);

        //Ajout d'un Jpanel avec les composants
        add(new PanneauJeu(this.nombreJoueur));

        // ajoute une gestion du EXIT par confirmation pop-up
        this.addWindowListener(new WindowAdapter() {

            // gestionnaire d'événement
            public void windowClosing(WindowEvent we) {

                int result = JOptionPane.showConfirmDialog(null, "Quitter " + "Bunco+" + " ?", "Confirmation : ", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }

        });

        // rend visible
        setVisible(true);
    }
}
