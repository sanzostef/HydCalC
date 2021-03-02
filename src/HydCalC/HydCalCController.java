package HydCalC;

import HydCalC.Class.*;
import HydCalC.Controller.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HydCalCController implements Initializable {

    @FXML    private Pane dspVerin;
    @FXML    public VerinController dspVerinController;
    @FXML    private Pane dspPump;
    @FXML    public PumpController dspPumpController;
    @FXML    private Pane dspAccu;
    @FXML    public AccuController dspAccuController;
    @FXML    private Pane dspDeltaPSing;
    @FXML    public DeltaPSingController dspDeltaPSingController;
    @FXML    private Pane dspDeltaPLin;
    @FXML    public DeltaPLineaireController dspDeltaPLinController;
    @FXML    private Pane dspMoteur;
    @FXML    public MoteurController dspMoteurController;
    @FXML    private Pane dspGicleur;
    @FXML    public GicleurController dspGicleurController;
    //@FXML    private Pane dspClient;
    @FXML    public ClientController dspClientController;
    @FXML    public OutilController dspOutilController;

    //DecimalFormat df = new DecimalFormat("###.##");
    NumberFormat df = NumberFormat.getInstance();
    public static final int dfond = 0, dtige = 1, course = 2, sfond = 3, sann = 4, r = 5, rinv = 6, vfond = 7, vtige = 8;
    public static final int forcesortie = 9, forcerentree = 10, forcediff = 11, vitsortie = 12, vitrentree = 13, vitdiff = 14;
    public static final int tpssortie = 15, tpsrentree = 16, tpsdiff = 17;
    public static final int debit = 18, pression = 19, cyl = 20, vitDeRot = 21, pwrHyd = 22, pwrMeca = 23, rendement = 24;
    public static final int V0 = 25, V1 = 26, V2 = 27, P0 = 28, P1 = 29, P2 = 30, DeltaV = 31, n = 32;
    public static final int diamSing = 33, masseVol = 34, deltaPSing = 35;
    public static final int diamLin = 36, masseVolL = 37, l = 38, visco = 39, deltaPLin = 40;
    public static final int couple = 41, cylm = 42, VitDeRotationM = 43, pwrMecaM = 44, ηvol = 45, ηhm = 46, ηtot = 47;
    public static final int diamOrifice = 48, deltaPOrifice = 49;

    private boolean erreurDeSaisie = false;
    public final Verin verin = new Verin();
    public final Pump pompe = new Pump();
    public final Accu accu = new Accu();
    public final DeltaPSing deltaPSinguliere = new DeltaPSing();
    public final DeltaPLineaire deltaPLineaire = new DeltaPLineaire();
    public final Moteur moteur = new Moteur();
    public final Gicleur gicleur = new Gicleur();

    public ArrayList<TextField> listeDesTextfield = new ArrayList<>();
    final ArrayList<Method> lstSet = new ArrayList<>();
    public final ArrayList<Method> lstGet = new ArrayList<>();
    public final ArrayList<Method> lstCalcul = new ArrayList<>();

    @Override    public void initialize(URL url, ResourceBundle rb) {

        //Injection des controleurs les un dans les autres
        dspVerinController.injection(this);
        dspPumpController.injection(this);
        dspPumpController.injection(dspVerinController);
        dspAccuController.injection(this);
        dspDeltaPSingController.injection(this);
        dspDeltaPLinController.injection(this);
        dspMoteurController.injection(this);
        dspGicleurController.injection(this);
        dspClientController.injection(this);
        dspOutilController.injection(this);
        //Création de la liste des TextFields
        creationListeDesTextFields(dspVerin);
        creationListeDesTextFields(dspPump);
        creationListeDesTextFields(dspAccu);
        creationListeDesTextFields(dspDeltaPSing);
        creationListeDesTextFields(dspDeltaPLin);
        creationListeDesTextFields(dspMoteur);
        creationListeDesTextFields(dspGicleur);
        //Creation des listes des methodes
        creationDesListesDeMethodes(verin, dfond, tpsdiff);
        creationDesListesDeMethodes(pompe, debit, rendement);
        creationDesListesDeMethodes(accu, V0, n);
        creationDesListesDeMethodes(deltaPSinguliere, diamSing, deltaPSing);
        creationDesListesDeMethodes(deltaPLineaire, diamLin, deltaPLin);
        creationDesListesDeMethodes(moteur, couple, ηtot);
        creationDesListesDeMethodes(gicleur, diamOrifice, deltaPOrifice);
    }

    @FXML
    private void buttonCalculer() {
        try {
            int count = 0;
            erreurDeSaisie = false;
            verifConversion_StringToDouble_possible();
            if (erreurDeSaisie) {
                listeDesTextfield.clear();
                return;
            }

            recopTextfieldDansCompo();
            if ((double) lstGet.get(debit).invoke(pompe) != 0.0d) {
                gicleur.setDebit(pompe.getDebit());
            }
            accu.Cste(0);
            accu.Cste();
            dspVerinController.calculerSansParametreExterne();
            dspPumpController.calculerSansParametreExterne();
            dspAccuController.calculerSansParametreExterne();
            if (!listeDesTextfield.get(pression).getText().isEmpty() & listeDesTextfield.get(P2).getText().isEmpty())
                accu.setP2(pompe.getPression());
            if ((double) lstGet.get(pression).invoke(pompe) != 0.0d & (double) lstGet.get(sfond).invoke(verin) == 0.0d) {
                verin.calculerSFond((double) lstGet.get(pression).invoke(pompe));
            }
            if ((double) lstGet.get(pression).invoke(pompe) != 0.0d & (double) lstGet.get(sann).invoke(verin) == 0.0d) {
                verin.calculerSAnnulaire((double) lstGet.get(pression).invoke(pompe));
            }
            verin.calculerForceSortie((double) lstGet.get(pression).invoke(pompe));
            verin.calculerForceRentree((double) lstGet.get(pression).invoke(pompe));
            verin.calculerForceDiff((double) lstGet.get(pression).invoke(pompe));

            dspVerinController.calculerSansParametreExterne();
            dspPumpController.calculerSansParametreExterne();
            if ((double) lstGet.get(debit).invoke(pompe) != 0.0d & (double) lstGet.get(vfond).invoke(verin) == 0.0d) {
                verin.calculerVFond((double) lstGet.get(debit).invoke(pompe));
            }
            if ((double) lstGet.get(debit).invoke(pompe) != 0.0d & (double) lstGet.get(vtige).invoke(verin) == 0.0d) {
                verin.calculerVTige((double) lstGet.get(debit).invoke(pompe));
            }
            if ((double) lstGet.get(debit).invoke(pompe) != 0.0d) {
                deltaPSinguliere.calculerVitEcoulement((double) lstGet.get(debit).invoke(pompe));
                deltaPLineaire.calculerVitEcoulement((double) lstGet.get(debit).invoke(pompe));
                deltaPLineaire.calculerRE((double) lstGet.get(debit).invoke(pompe));
                verin.calculerVitSortie((double) lstGet.get(debit).invoke(pompe));
                verin.calculerVitRentree((double) lstGet.get(debit).invoke(pompe));
                verin.calculerVitDiff((double) lstGet.get(debit).invoke(pompe));
            }
            dspDeltaPSingController.calculerSansParametreExterne();
            dspDeltaPLinController.calculerSansParametreExterne();
            dspGicleurController.calculerSansParametreExterne();
            dspVerinController.calculerSansParametreExterne();
            dspPumpController.calculerSansParametreExterne();
            if ((double) lstGet.get(sfond).invoke(verin) != 0.0d & (double) lstGet.get(forcesortie).invoke(verin) != 0.0d) {
                pompe.calculerPression((double) lstGet.get(sfond).invoke(verin), (double) lstGet.get(forcesortie).invoke(verin));
            }
            if ((double) lstGet.get(sann).invoke(verin) != 0.0d & (double) lstGet.get(forcerentree).invoke(verin) != 0.0d) {
                pompe.calculerPression((double) lstGet.get(sann).invoke(verin), (double) lstGet.get(forcerentree).invoke(verin));
            }
            if (verin.getSDeLaTige() != 0.0d & (double) lstGet.get(forcediff).invoke(verin) != 0.0d) {
                pompe.calculerPression(verin.getSDeLaTige(), (double) lstGet.get(forcediff).invoke(verin));
            }
            dspVerinController.calculerSansParametreExterne();
            dspPumpController.calculerSansParametreExterne();
            if ((double) lstGet.get(sfond).invoke(verin) != 0.0d & (double) lstGet.get(vitsortie).invoke(verin) != 0.0d) {
                pompe.calculerDebit((double) lstGet.get(sfond).invoke(verin), (double) lstGet.get(vitsortie).invoke(verin));
            }
            if ((double) lstGet.get(sann).invoke(verin) != 0.0d & (double) lstGet.get(vitrentree).invoke(verin) != 0.0d) {
                pompe.calculerDebit((double) lstGet.get(sann).invoke(verin), (double) lstGet.get(vitrentree).invoke(verin));
            }
            if (verin.getSDeLaTige() != 0.0d & (double) lstGet.get(vitdiff).invoke(verin) != 0.0d) {
                pompe.calculerDebit(verin.getSDeLaTige(), (double) lstGet.get(vitdiff).invoke(verin));
            }
            dspVerinController.calculerSansParametreExterne();
            dspPumpController.calculerSansParametreExterne();
            dspVerinController.calculerSansParametreExterne();
            dspPumpController.calculerSansParametreExterne();
            dspAccuController.calculerSansParametreExterne();

            recopierComposantsDansTextField(verin, dfond, tpsdiff);
            recopierComposantsDansTextField(pompe, debit, rendement);
            recopierComposantsDansTextField(accu, V0, n);
            recopierComposantsDansTextField(deltaPSinguliere, diamSing, deltaPSing);
            recopierComposantsDansTextField(deltaPLineaire, diamLin, deltaPLin);
            recopierComposantsDansTextField(moteur, couple, ηtot);
            recopierComposantsDansTextField(gicleur, diamOrifice, deltaPOrifice);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonInit() {
        if (!(listeDesTextfield.size() == 0))
            for (TextField textfield : listeDesTextfield)
                textfield.setText("");
    }

    private void creationListeDesTextFields(Pane pane) {
        for (Node node : pane.getChildren()) {
            if (node instanceof TextField) {
                listeDesTextfield.add((TextField) node);
            }
        }
    }

    private void creationDesListesDeMethodes(Object object, int indexDebut, int indexFin) {
        Method[] tabMethod = object.getClass().getMethods();
        for (int i = indexDebut; i < indexFin + 1; i++) {
            for (Method methode : tabMethod) {
                if (methode.getParameterCount() == 0) {
                    if (methode.getName().contains("get" + listeDesTextfield.get(i).getId())) {
                        if (!lstGet.contains(methode) & lstGet.size() < indexFin + 1) {
                            if (i > lstGet.size() - 1) lstGet.add(methode);
                            else lstGet.add(i, methode);
                        }
                    }
                    if (methode.getName().contains("calculer" + listeDesTextfield.get(i).getId())) {
                        if (!lstCalcul.contains(methode) & lstCalcul.size() < indexFin + 1) {
                            if (i > lstCalcul.size() - 1) lstCalcul.add(methode);
                            else lstCalcul.add(i, methode);
                        }
                    }
                } else if (methode.getParameterCount() == 1) {
                    if (methode.getName().contains("set" + listeDesTextfield.get(i).getId())) {
                        if (!lstSet.contains(methode) & lstSet.size() < indexFin + 1) {
                            if (i > lstSet.size() - 1) lstSet.add(methode);
                            else lstSet.add(i, methode);
                        }
                    }
                }
            }
        }
        System.out.println("  Impression des listes de méthodes: " + object);
        System.out.println("  liste des Set    ");
        for (int i = indexDebut; i < indexFin + 1; i++)
            System.out.println(i + "   -   " + lstSet.get(i).toString());
        System.out.println("      ");
        System.out.println("  liste des Get    ");
        for (int i = indexDebut; i < indexFin + 1; i++)
            System.out.println(i + "   -   " + lstGet.get(i).toString());
        System.out.println("      ");
        System.out.println("  liste des Calculer    ");
        for (int i = indexDebut; i < indexFin + 1; i++)
            System.out.println(i + "   -   " + lstCalcul.get(i).toString());
        System.out.println("fini");
        System.out.println("      ");
    }

    /**
     * Vérifie que les champs sont biens remplis avec des nombres supérieurs a zéro
     * Elle crée en même temps la liste des Textfields
     */
    private void verifConversion_StringToDouble_possible() {
        if (!(listeDesTextfield.size() == 0)) {
            for (TextField textfield : listeDesTextfield) {
                double nb;
                String s = textfield.getText().replace(',', '.').replaceAll("\\h", "");
                if (!s.isEmpty()) {
                    try {
                        nb = Double.parseDouble(s);
                        if (nb < 0) throw new NumberFormatException();
                    } catch (Exception e) {
                        Alert dialogW = new Alert(Alert.AlertType.WARNING);
                        dialogW.setTitle("Erreur de Saisie");
                        dialogW.setHeaderText(null); // No header
                        dialogW.setContentText("Verifier la valeur entree en " + textfield.getId());
                        dialogW.showAndWait();
                        erreurDeSaisie = true;
                        return;
                    }
                }
            }
        }
        System.out.println("Impression de la liste des textfields et des valeurs entrées:");
        for (int i = dfond; i < listeDesTextfield.size(); i++)
            System.out.println(i + " - " + listeDesTextfield.get(i).getId() + " = " + listeDesTextfield.get(i).getText());
        System.out.println("fini");
        System.out.println();
    }

    private double valeurDuParametre;

    private void recopTextfieldDansCompo() throws IllegalAccessException, InvocationTargetException {

        for (int i = dfond; i < deltaPOrifice+ 1; i++) {
            if (!listeDesTextfield.get(i).getText().isEmpty()) {     // Si pas vide
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replaceAll("\\h", ""));
                if (i < tpsdiff + 1) {
                    EcrireDansComposant(i, verin);
                }
                if (i > debit - 1 & i < rendement + 1) {
                    EcrireDansComposant(i, pompe);
                }
                if (i > V0 - 1 & i < n + 1) {
                    EcrireDansComposant(i, accu);
                }
                if (i > diamSing - 1 & i < deltaPSing + 1) {
                    EcrireDansComposant(i, deltaPSinguliere);
                }
                if (i > diamLin - 1 & i < deltaPLin + 1) {
                    EcrireDansComposant(i, deltaPLineaire);
                }
                if (i > couple - 1 & i < ηtot + 1) {
                    EcrireDansComposant(i, moteur);
                }
            } else {
                if (i < tpsdiff + 1) {
                    initialiserComposant(i, verin);
                }
                if (i > debit - 1 & i < rendement + 1) {
                    initialiserComposant(i, pompe);
                }
                if (i > V0 - 1 & i < n + 1) {
                    initialiserComposant(i, accu);
                }
                if (i > diamSing - 1 & i < deltaPSing + 1) {
                    initialiserComposant(i, deltaPSinguliere);
                }
                if (i > diamLin - 1 & i < deltaPLin + 1) {
                    initialiserComposant(i, deltaPLineaire);
                }
                if (i > couple - 1 & i < ηtot + 1) {
                    initialiserComposant(i, moteur);
                }
            }
        }
    }

    private void EcrireDansComposant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        lstSet.get(i).invoke(composant, valeurDuParametre);
        System.out.println("j'écris " + valeurDuParametre + " dans: " + listeDesTextfield.get(i).getId());
    }

    private void initialiserComposant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        switch (i) {
            case (rendement) -> {
                lstSet.get(i).invoke(composant, 80);
                System.out.println("j'écris " + 80.0d + " dans: " + listeDesTextfield.get(i).getId());
            }
            case (n) -> {
                lstSet.get(i).invoke(composant, 1.4d);
                System.out.println("j'écris " + 1.4d + " dans: " + listeDesTextfield.get(i).getId());
            }

            case (masseVol) -> {
                lstSet.get(i).invoke(composant, 850);
                System.out.println("j'écris " + 850 + " dans: " + listeDesTextfield.get(i).getId());
            }            default -> {
                lstSet.get(i).invoke(composant, 0.0d);
                System.out.println("j'écris " + 0.0d + " dans: " + listeDesTextfield.get(i).getId());
            }
        }
    }

    private void recopierComposantsDansTextField(Object composant, int indexDebut, int indexFin) throws IllegalAccessException, InvocationTargetException {
        System.out.println("Recopie Valeurs de " + composant + " dans les textfields");
        double valeurDuParametre;
        for (int i = indexDebut; i < indexFin + 1; i++) { // NB de textfields Verin : 18
            valeurDuParametre = (double) lstGet.get(i).invoke(composant);
            if (valeurDuParametre != 0.0d) {
                listeDesTextfield.get(i).setText(df.format(valeurDuParametre));
            }
            System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
        }
        System.out.println();
    }
}
   /* private void recopierComposantsDansTextField() throws IllegalAccessException, InvocationTargetException {
        System.out.println("Recopie Valeurs des composants dans les textfields");
        double valeurDuParametre;
        for (int i = dfond; i < tpsdiff + 1; i++) { // NB de textfields Verin : 18
                valeurDuParametre = (double) lstGet.get(i).invoke(verin);
                if (valeurDuParametre != 0.0d) {
                    listeDesTextfield.get(i).setText(df.format(valeurDuParametre));
                }
                System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
        }
        for (int i = debit; i < rendement + 1; i++) { // 1er textfields Pompe: 18 => dernier: 25
                valeurDuParametre = (double) lstGet.get(i).invoke(pompe);
                if (valeurDuParametre != 0.0d) {
                    listeDesTextfield.get(i).setText(df.format(valeurDuParametre));
                }
                System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
        }
        for (int i = V0; i < n + 1; i++) { // 1er textfields Pompe: 18 => dernier: 25
                valeurDuParametre = (double) lstGet.get(i).invoke(accu);
                if (valeurDuParametre != 0.0d) {
                    listeDesTextfield.get(i).setText(df.format(valeurDuParametre));
                }
                System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
        }
        System.out.println("fini");
    }*/


