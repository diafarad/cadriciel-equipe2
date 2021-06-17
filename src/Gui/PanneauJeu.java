package Gui;

/**
 *
 */

import Framework.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PanneauJeu extends JPanel implements ActionListener {

    private static final String TEXT_BOUTTON_BRASSAGE = "LANCER DES";
    private static final String TEXT_DEBUT = "JOUEUR 1 VEUILLEZ LANCER LES DES";
    private static final String TEXT_PARTIE = "C'EST AU TOUR DU JOUEUR ";
    private static final String TEXT_TABLEAU = "POINTAGE";
    private static final String[] NOM_COLONNES = {"JOUEUR", "POINTS"};

    private static final int BOUTTON_HAUTEUR = 80;
    private static final int BOUTTON_LARGEUR = 110;
    private static final int ESPACE_BOUTTON = 55;

    private static final int TEXT_HAUTEUR = 30;
    private static final int TEXT_LARGEUR = 110;

    private static final int TABLEAU_HAUTEUR = 150;
    private static final int TABLEAU_LARGEUR = 300;

    private static final int NOMBRE_SOUS_PANNEAUX_V = 2;

    private static final String PATH_IMAGE_DES_UN = "src/Ressources/desFaceUn" + ".jpg";
    private static final String PATH_IMAGE_DES_DEUX = "src/Ressources" + "/desFaceDeux" + ".jpg";
    private static final String PATH_IMAGE_DES_TROIS = "src/Ressources" + "/desFaceTrois" + ".jpg";
    private static final String PATH_IMAGE_DES_QUATRE = "src/Ressources" + "/desFaceQuatre" + ".jpg";
    private static final String PATH_IMAGE_DES_CINQ = "src/Ressources" + "/desFaceCinq" + ".jpg";
    private static final String PATH_IMAGE_DES_SIX = "src/Ressources" + "/desFaceSix" + ".jpg";

    private ArrayList<ImageIcon> imageDesListe = new ArrayList<>();

    private JButton boutonBrassage = new JButton(TEXT_BOUTTON_BRASSAGE);
    private JLabel textIndication = new JLabel(TEXT_DEBUT);
    private JTable tableValeurs;

    private int nombreJoueur;

    private Jeu jeu = Jeu.getInstance();

    public PanneauJeu(int nombreJoueur) {

        this.nombreJoueur = nombreJoueur;

        ajoutImageDesListe();

        //Separation en deux sous panneaux
        setLayout(new GridLayout(1, NOMBRE_SOUS_PANNEAUX_V));

        panneauJeuDes();
        panneauJeuJoueur();
    }

    /**
     * Panneau pour afficher les valeurs des points et nom(1,2,3,4) des
     * joueurs.Ce panneau est à droite.
     */
    public void panneauJeuJoueur() {

        JPanel panneauJeuJoueur = new JPanel();
        panneauJeuJoueur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), TEXT_TABLEAU, TitledBorder.CENTER, TitledBorder.TOP));

        ajoutValeursTableauDefaut();

        JScrollPane scrollPane = new JScrollPane(this.tableValeurs);
        scrollPane.setPreferredSize(new Dimension(TABLEAU_LARGEUR, TABLEAU_HAUTEUR));
        panneauJeuJoueur.add(scrollPane);

        panneauJeuJoueur.add(textIndication);

        add(panneauJeuJoueur);
    }

    /**
     * Panneau pour lancer les dés.Ce panneau est à gauche.
     */
    public void panneauJeuDes() {

        JPanel panneauJeuDes = new JPanel(new BorderLayout());

        boutonBrassage.setPreferredSize(new Dimension(BOUTTON_LARGEUR, BOUTTON_HAUTEUR));
        panneauJeuDes.add(boutonBrassage, BorderLayout.SOUTH);
        panneauJeuDes.setOpaque(false);

        //Ajout au panneau principal
        add(panneauJeuDes);

        this.boutonBrassage.addActionListener(this);
    }

    /**
     *
     *
     */
    public void ajoutValeursTableauDefaut() {

        DefaultTableModel model = new DefaultTableModel(this.nombreJoueur, NOM_COLONNES.length);

        this.tableValeurs = new JTable();
        this.tableValeurs.setModel(model);
        this.tableValeurs.getColumnModel().getColumn(0).setHeaderValue(NOM_COLONNES[0]);
        this.tableValeurs.getColumnModel().getColumn(1).setHeaderValue(NOM_COLONNES[1]);

        int compteurJoueur = 1;

        for (int i = 0; i < this.tableValeurs.getRowCount(); i++) {

            this.tableValeurs.setValueAt(compteurJoueur++, i, 0);
        }
    }

    /**
     *
     *
     */
    public void changementValeursTableau(ArrayList<Integer> valeurs) {

        int compteurJoueur = 1;

        //TODO - Faire la modification en fonction de comment les points
        // seront "stockee" dans le reste des classes
        for (int i = 0; i < this.tableValeurs.getRowCount(); i++) {

            this.tableValeurs.setValueAt(compteurJoueur++, i, 0);
            this.tableValeurs.setValueAt(valeurs.get(i), i, 1);
        }
    }

    /**
     *
     */
    public void ajoutImageDesListe() {

        this.imageDesListe.add(new ImageIcon(PATH_IMAGE_DES_UN) {
        });
        this.imageDesListe.add(new ImageIcon(PATH_IMAGE_DES_DEUX));
        this.imageDesListe.add(new ImageIcon(PATH_IMAGE_DES_TROIS));
        this.imageDesListe.add(new ImageIcon(PATH_IMAGE_DES_QUATRE));
        this.imageDesListe.add(new ImageIcon(PATH_IMAGE_DES_CINQ));
        this.imageDesListe.add(new ImageIcon(PATH_IMAGE_DES_SIX));
    }

    /**
     * Dessiner les dés
     * @param g
     */
    public void paintComponent(Graphics g) {

        for (ImageIcon img : this.imageDesListe) {

            img.paintIcon(this,g,0,0);
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.boutonBrassage) {

            this.jeu.desLancement(true);
            System.out.println("Lancement dés");

            //TODO - Test
            this.textIndication.setText(TEXT_PARTIE + this.jeu.nomJoueurTest());
            changementValeursTableau(this.jeu.nouvelleValeursTest());
        }
    }
}
