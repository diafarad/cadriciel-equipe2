package Gui;

/**
 *
 *
 *
 */

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadreChoixJoueur extends JFrame implements Runnable, AWTEventListener {

    //Constante
    private static final int CADRE_LARGEUR = 700;
    private static final int CADRE_HAUTEUR = 500;
    private static final String NOM_JEU = "BUNCO+";

     //Ajouter ouverture d'un panneau
     private PanneauChoixJoueur panneauChoixJoueur = null;

    /**
     *
     */
    public CadreChoixJoueur() {
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
        panneauChoixJoueur = new PanneauChoixJoueur();
        add(panneauChoixJoueur);

        //Ajoute une gestion du EXIT par confirmation pop-up
        this.addWindowListener(new WindowAdapter() {

            //Gestionnaire d'événement
            public void windowClosing(WindowEvent we) {

                int result = JOptionPane.showConfirmDialog(null, "Quitter " + "Bunco+" + " ?", "Confirmation : ", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        //Rend visible la fenetre
        setVisible(true);
    }

    /**
     *
     */
    @Override
    public void run() {

        //Permet de savoir s'il y a eu un changement dans le Frame ou Jpanel
        Toolkit.getDefaultToolkit().addAWTEventListener(this,
                AWTEvent.WINDOW_EVENT_MASK);

        //Configure la fenetre
        configurerFrame();
    }

    /**
     *
     * @param event
     */
    @Override
    public void eventDispatched(AWTEvent event) {

        //S'il y a eu un choix du nombre de joueur dans le JPanel, le  JFrame
        //se ferme
        if(this.panneauChoixJoueur.getChoixNombreJoueur() != 0){
            this.dispose();
        }
    }
}
