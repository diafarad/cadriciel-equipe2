package Gui;

/**
 *
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PanneauChoixJoueur extends JPanel implements ActionListener{

    private static final String NOM_JEU = "BUNCO+";
    private static final String NOM_JEU_POLICE = "Algerian";
    private static final int NOM_JEU_GRANDEUR = 180;

    private static final String TEXT_BOUTTON_1 = "2 Joueurs";
    private static final String TEXT_BOUTTON_2 = "3 Joueurs";
    private static final String TEXT_BOUTTON_3 = "4 Joueurs";

    private static final int BOUTTON_HAUTEUR = 80;
    private static final int BOUTTON_LARGEUR = 110;
    private static final int ESPACE_BOUTTON = 55;

    private static final String SOURCE_IMAGE = "src/Ressources/diceRed2.png";
    private static final int NOMBRE_SOUS_PANNEAUX_H = 2;

    //JButton pour le choix du nombre de joueur
    private JButton boutonDeuxJoueur = new JButton(TEXT_BOUTTON_1);
    private JButton boutonTroisJoueur = new JButton(TEXT_BOUTTON_2);
    private JButton boutonQuatreJoueur = new JButton(TEXT_BOUTTON_3);

    //Label pour le text du nom du jeu
    private JLabel nomjeu = new JLabel(NOM_JEU);

    //L'image de l'arrière-plan
    private Image imageArriere;

    private int choixNombreJoueur = 0;

    /**
     *
     */
    public PanneauChoixJoueur() {

        //Separation en deux sous panneaux
        setLayout(new GridLayout(NOMBRE_SOUS_PANNEAUX_H, 1));

        try {
            imageArriere = ImageIO.read(new File(SOURCE_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ajoutComposants();
    }

    /**
     *
     * @return
     */
    public int getChoixNombreJoueur(){

        return this.choixNombreJoueur;
    }

    /**
     *
     */
    private void ajoutComposants() {

        //Label pour le nom du jeu
        nomjeu.setFont(new Font(NOM_JEU_POLICE, Font.ITALIC, NOM_JEU_GRANDEUR));

        //Les boutons pour choisir le nombre de joueurs
        boutonDeuxJoueur.setPreferredSize(new Dimension(BOUTTON_LARGEUR, BOUTTON_HAUTEUR));
        boutonTroisJoueur.setPreferredSize(new Dimension(BOUTTON_LARGEUR, BOUTTON_HAUTEUR));
        boutonQuatreJoueur.setPreferredSize(new Dimension(BOUTTON_LARGEUR, BOUTTON_HAUTEUR));

        JPanel nomPanel = new JPanel();
        nomPanel.setLayout(new GridBagLayout());
        nomPanel.add(nomjeu);
        nomPanel.setOpaque(false);

        //Ajout au panneau principal
        add(nomPanel);

        JPanel boutonsPanel = new JPanel();
        boutonsPanel.add(boutonDeuxJoueur);
        boutonsPanel.add(Box.createHorizontalStrut(ESPACE_BOUTTON));
        boutonsPanel.add(boutonTroisJoueur);
        boutonsPanel.add(Box.createHorizontalStrut(ESPACE_BOUTTON));
        boutonsPanel.add(boutonQuatreJoueur);
        boutonsPanel.setOpaque(false);

        //Ajout au panneau principal
        add(boutonsPanel);

        this.boutonDeuxJoueur.addActionListener(this);
        this.boutonTroisJoueur.addActionListener(this);
        this.boutonQuatreJoueur.addActionListener(this);
    }

    /**
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        g.drawImage(this.imageArriere, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.boutonDeuxJoueur || e.getSource() == this.boutonTroisJoueur || e.getSource() == this.boutonQuatreJoueur) {

            //Permet de trouver le chiffre dans le nom des boutons
            int nombreJoueur = Integer.parseInt(((JButton) e.getSource()).getText().substring(0, ((JButton) e.getSource()).getText().indexOf(" ")));
            this.choixNombreJoueur = nombreJoueur;

            //Lance la fenêtre de jeu
            Thread t = new Thread(new Gui.CadreJeu(nombreJoueur));
            t.start();
            System.out.println("Nombre de joueurs choisi : " + nombreJoueur);

        }
    }
}
