package HydCalC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btCalculer;
    @FXML
    private RadioButton instant, rap20, lent1, lent2, lent3, perso;
    @FXML
    private TextField txtV0, txtV1, txtV2, txtP0, txtDeltaV, txtn;

    @FXML private Pane dspVerin;
    @FXML private VerinController dspVerinController;
    @FXML private Pane dspPump;
    @FXML private PumpController dspPumpController;
    @FXML private Pane dspAccu;
    @FXML private AccuController dspAccuController;
    @FXML private Pane dspDeltaP;
    @FXML private DeltaPController dspDeltaPController;

    ArrayList<TextField> listeDesTextfield = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Injection des controleurs les un dans les autres
        dspVerinController.injection(this);
        dspPumpController.injection(this);
        dspPumpController.injectionDspVerin(dspVerinController);
        dspAccuController.injection(this);
        dspDeltaPController.injection(this);
        //Création de la liste des TextFields
        for (Node node : dspVerin.getChildren()) {
            if (node instanceof TextField) {listeDesTextfield.add((TextField) node);}
        }
        for (Node node : dspPump.getChildren()) {
            if (node instanceof TextField) {listeDesTextfield.add((TextField) node);}
        }
        for (Node node : dspAccu.getChildren()) {
            if (node instanceof TextField) {listeDesTextfield.add((TextField) node);}
        }
        for (Node node : dspDeltaP.getChildren()) {
            if (node instanceof TextField) {listeDesTextfield.add((TextField) node);}
        }
    }

    //DecimalFormat df = new DecimalFormat("###.##");
    NumberFormat df = NumberFormat.getInstance();
    private static final int dfond = 0, dtige = 1, course = 2, sfond = 3, sann = 4, r = 5, rinv = 6, vfond = 7, vtige = 8;
    private static final int forcesortie = 9, forcerentree = 10, forcediff = 11, vitsortie = 12, vitrentree = 13, vitdiff = 14;
    private static final int tpssortie = 15, tpsrentree = 16, tpsdiff = 17;
    private static final int debit = 18, pression = 19, cyl = 20, vitDeRot = 21, pwrHyd = 22, pwrMeca = 23, rendement = 24;
    private static final int V0 = 25, V1 = 26, V2 = 27, P0 = 28, P1 = 29, P2 = 30, DeltaV = 31, n = 32;

    private boolean erreurDeSaisie = false;

    final Verin verin = new Verin();
    final Pump pompe = new Pump();
    final Accu accu = new Accu();
    final DeltaP DeltaPSing = new DeltaP();
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
        creationDesListesDeMethodes();
        recopTextfieldDansCompo();
        accu.Cste(0);
        accu.Cste();
            do {
                calculerSansParametreExterne();
                if (!listeDesTextfield.get(debit).getText().isEmpty() & listeDesTextfield.get(P2).getText().isEmpty())
                        accu.setP2(pompe.getPression());

                if ((double) lstGet.get(pression).invoke(pompe) != 0.0d & (double) lstGet.get(sfond).invoke(verin) == 0.0d) {
                    verin.calculerSFond((double) lstGet.get(pression).invoke(pompe));}
                if ((double) lstGet.get(pression).invoke(pompe) != 0.0d & (double) lstGet.get(sann).invoke(verin) == 0.0d) {
                    verin.calculerSAnnulaire((double) lstGet.get(pression).invoke(pompe));
                }
                verin.calculerForceSortie((double) lstGet.get(pression).invoke(pompe));
                verin.calculerForceRentree((double) lstGet.get(pression).invoke(pompe));
                verin.calculerForceDiff((double) lstGet.get(pression).invoke(pompe));

                calculerSansParametreExterne();
                if ((double) lstGet.get(debit).invoke(pompe) != 0.0d & (double) lstGet.get(vfond).invoke(verin) == 0.0d) {
                    verin.calculerVFond((double) lstGet.get(debit).invoke(pompe));
                }
                if ((double) lstGet.get(debit).invoke(pompe) != 0.0d & (double) lstGet.get(vtige).invoke(verin) == 0.0d) {
                    verin.calculerVTige((double) lstGet.get(debit).invoke(pompe));
                }
                verin.calculerVitSortie((double) lstGet.get(debit).invoke(pompe));
                verin.calculerVitRentree((double) lstGet.get(debit).invoke(pompe));
                verin.calculerVitDiff((double) lstGet.get(debit).invoke(pompe));

                calculerSansParametreExterne();
                if ((double) lstGet.get(sfond).invoke(verin) != 0.0d & (double) lstGet.get(forcesortie).invoke(verin) != 0.0d) {
                    pompe.calculerPression((double) lstGet.get(sfond).invoke(verin), (double) lstGet.get(forcesortie).invoke(verin));
                }
                if ((double) lstGet.get(sann).invoke(verin) != 0.0d & (double) lstGet.get(forcerentree).invoke(verin) != 0.0d) {
                    pompe.calculerPression((double) lstGet.get(sann).invoke(verin), (double) lstGet.get(forcerentree).invoke(verin));
                }
                if (verin.getSDeLaTige() != 0.0d & (double) lstGet.get(forcediff).invoke(verin) != 0.0d) {
                    pompe.calculerPression(verin.getSDeLaTige(), (double) lstGet.get(forcediff).invoke(verin));
                }
                calculerSansParametreExterne();
                if ((double) lstGet.get(sfond).invoke(verin) != 0.0d & (double) lstGet.get(vitsortie).invoke(verin) != 0.0d) {
                    pompe.calculerDebit((double) lstGet.get(sfond).invoke(verin), (double) lstGet.get(vitsortie).invoke(verin));
                }
                if ((double) lstGet.get(sann).invoke(verin) != 0.0d & (double) lstGet.get(vitrentree).invoke(verin) != 0.0d) {
                    pompe.calculerDebit((double) lstGet.get(sann).invoke(verin), (double) lstGet.get(vitrentree).invoke(verin));
                }
                if (verin.getSDeLaTige() != 0.0d & (double) lstGet.get(vitdiff).invoke(verin) != 0.0d) {
                    pompe.calculerDebit(verin.getSDeLaTige(), (double) lstGet.get(vitdiff).invoke(verin));
                }
                calculerSansParametreExterne();
                pompe.calculerCyl();
                pompe.calculerVitDeRotation();
                pompe.calculerPwrHyd();
                pompe.calculerPwrMeca();
                pompe.calculerRendement();
                calculerSansParametreExterne();
                    
                count++;
            } while (count < 2);

            recopierComposantsDansTextField();
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

    final ArrayList<Method> lstSet = new ArrayList<>();
    final ArrayList<Method> lstGet = new ArrayList<>();
    final ArrayList<Method> lstCalcul = new ArrayList<>();

    private void creationDesListesDeMethodes() {
        Method[] tabMethod = Verin.class.getMethods();
        for (int i = dfond; i < tpsdiff + 1; i++) {
            for (Method methode : tabMethod) {
                if (methode.getParameterCount() == 0) {
                    if (methode.getName().contains("get" + listeDesTextfield.get(i).getId())) {
                        if (!lstGet.contains(methode) & lstGet.size() < tpsdiff + 1) {
                            if (i > lstGet.size() - 1) lstGet.add(methode);
                            else lstGet.add(i, methode);
                        }
                    }
                    if (methode.getName().contains("calculer" + listeDesTextfield.get(i).getId())) {
                        if (!lstCalcul.contains(methode) & lstCalcul.size() < tpsdiff + 1) {
                            if (i > lstCalcul.size() - 1) lstCalcul.add(methode);
                            else lstCalcul.add(i, methode);
                        }
                    }
                } else if (methode.getParameterCount() == 1) {
                    if (methode.getName().contains("set" + listeDesTextfield.get(i).getId())) {
                        if (!lstSet.contains(methode) & lstSet.size() < tpsdiff + 1) {
                            if (i > lstSet.size() - 1) lstSet.add(methode);
                            else lstSet.add(i, methode);
                        }
                    }
                }
            }
        }
        tabMethod = Pump.class.getMethods();               // récupère la liste des methode de la class dans un tableau
        for (int i = debit; i < rendement + 1; i++) {                                        //<= pour chaque textfields pompe 18 => 24
            for (Method methode : tabMethod) {                         // si une methode dans le tableau des methode de la class
                if (methode.getParameterCount() == 0) {                                        // n'a pas de parametre
                    if (methode.getName().contains("get" + listeDesTextfield.get(i).getId())) {  // a un nom qui contient "get"
                        if (!lstGet.contains(methode) & lstGet.size() < rendement + 1) {    // si la methode n'est pas deja ou que
                            if (i > lstGet.size() - 1) lstGet.add(methode);
                            else
                                lstGet.add(i, methode);                 //la liste n'ai pas deja remplie ajout a la liste des
                        }                                           //get a sa place ou a la dernière si sa place n'existe pas encore
                    }
                    if (methode.getName().contains("calculer" + listeDesTextfield.get(i).getId())) {
                        if (!lstCalcul.contains(methode) & lstCalcul.size() < rendement + 1) {
                            if (i > lstCalcul.size() - 1) lstCalcul.add(methode);
                            else lstCalcul.add(i, methode);
                        }
                    }
                } else if (methode.getParameterCount() == 1) {
                    if (methode.getName().contains("set" + listeDesTextfield.get(i).getId())) {
                        if (!lstSet.contains(methode) & lstSet.size() < rendement + 1) {
                            if (i > lstSet.size() - 1) lstSet.add(methode);
                            else lstSet.add(i, methode);
                        }
                    }
                }
            }
        }
        tabMethod = Accu.class.getMethods();               // récupère la liste des methode de la class dans un tableau
        for (int i = V0; i < n + 1; i++) {                                        
            for (Method methode : tabMethod) {                         
                if (methode.getParameterCount() == 0) {                                        
                    if (methode.getName().contains("get" + listeDesTextfield.get(i).getId())) {  
                        if (!lstGet.contains(methode) & lstGet.size() < n + 1) {    
                            if (i > lstGet.size() - 1) lstGet.add(methode);
                            else
                                lstGet.add(i, methode);          
                        }                                           
                    }
                    if (methode.getName().contains("calculer" + listeDesTextfield.get(i).getId())) {
                        if (!lstCalcul.contains(methode) & lstCalcul.size() < n + 1) {
                            if (i > lstCalcul.size() - 1) lstCalcul.add(methode);
                            else lstCalcul.add(i, methode);
                        }
                    }
                } else if (methode.getParameterCount() == 1) {
                    if (methode.getName().contains("set" + listeDesTextfield.get(i).getId())) {
                        if (!lstSet.contains(methode) & lstSet.size() < n + 1) {
                            if (i > lstSet.size() - 1) lstSet.add(methode);
                            else lstSet.add(i, methode);
                        }
                    }
                }
            }
        }
        System.out.println("  Impression des listes de méthodes vérin: ");
        System.out.println("  liste des Set    ");
        for (int i = dfond; i < lstSet.size(); i++)
            System.out.println(i + "   -   " + lstSet.get(i).toString());
        System.out.println("      ");
        System.out.println("  liste des Get    ");
        for (int i = dfond; i < lstGet.size(); i++)
            System.out.println(i + "   -   " + lstGet.get(i).toString());
        System.out.println("      ");
        System.out.println("  liste des Calculer    ");
        for (int i = dfond; i < lstCalcul.size(); i++)
            System.out.println(i + "   -   " + lstCalcul.get(i).toString());
        System.out.println("fini");
        System.out.println("      ");
    }

    /**
     * Vérifie que les champs sont biens remplis avec des nombres supérieurs a zéro
     * Elle crée en même temps la liste des Textfields
     */
    private void verifConversion_StringToDouble_possible() {
        Pane parent = (Pane) btCalculer.getParent();
        for (Node node : parent.getChildren()) {
            if (node instanceof TextField) {
                double nb;
                listeDesTextfield.add((TextField) node);
                String s = (((TextField) node).getText()).replace(',', '.').replaceAll("\\h","");
                if (!s.isEmpty()) {
                    try {
                        nb = Double.parseDouble(s);
                        if (nb < 0) throw new NumberFormatException();
                    } catch (Exception e) {
                        Alert dialogW = new Alert(Alert.AlertType.WARNING);
                        dialogW.setTitle("Erreur de Saisie");
                        dialogW.setHeaderText(null); // No header
                        dialogW.setContentText("Verifier la valeur entree en " + node.getId());
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

        for (int i = dfond; i < n+1; i++) {
            if (!listeDesTextfield.get(i).getText().isEmpty()) {     // Si pas vide
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replaceAll("\\h",""));
                if (i < tpsdiff+1) {
                    EcrireDansComposant(i, verin);
                }
                if (i > debit-1 & i < rendement + 1) {
                    EcrireDansComposant(i, pompe);
                }
                if (i > V0-1 & i<n+1) {
                    EcrireDansComposant(i, accu);
                }
            }
            else {
                if (i < tpsdiff + 1) {
                    initialiserComposant(i, verin);
                }
                if (i > debit-1 & i < rendement + 1) {
                    initialiserComposant(i, pompe);
                }
                if (i > V0-1 & i<n+1) {
                    initialiserComposant(i, accu);
                }
            }
        }
    }
  /*  private void ComparerValeurEntreEtExistant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        if (!(valeurDuParametre+0.1 >= (double) lstGet.get(i).invoke(composant) & valeurDuParametre-0.1 <= (double) lstGet.get(i).invoke(composant)))
            if (i < course + 1)
                modifVerin = true;
            if (i > debit-1 & i < cyl + 1)
                modifPompe = true;
    }*/
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
            default -> {
                lstSet.get(i).invoke(composant, 0.0d);
                System.out.println("j'écris " + 0.0d + " dans: " + listeDesTextfield.get(i).getId());
            }
        }
    }

    private void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres");
        System.out.println();
        double valeurDuParametre;
        dspVerinController.calculerSansParametreExterne();
        dspPumpController.calculerSansParametreExterne();
        dspAccuController.calculerSansParametreExterne();
        System.out.println("fini");
        System.out.println();
    }

    private void recopierComposantsDansTextField() throws IllegalAccessException, InvocationTargetException {
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
    }

}
