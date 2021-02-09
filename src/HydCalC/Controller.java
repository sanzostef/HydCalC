package HydCalC;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Controller {

    //DecimalFormat df = new DecimalFormat("###.##");
    NumberFormat df = NumberFormat.getInstance();

    private static final int dfond = 0, dtige = 1, course = 2;
    private static final int sfond = 3, sann = 4, r = 5, rinv = 6;
    private static final int vfond = 7, vtige = 8;
    private static final int forcesortie = 9, forcerentree = 10, forcediff = 11;
    private static final int vitsortie = 12, vitrentree = 13, vitdiff = 14;
    private static final int tpssortie = 15, tpsrentree = 16, tpsdiff = 17;
    private static final int debit = 18, pression = 19, cyl = 20, vitDeRot = 21;
    private static final int pwrHyd = 22, pwrMeca = 23, rendement = 24;
    private static final int V0 = 25, V1 = 26, V2 = 27;
    private static final int P0 = 28, P1 = 29, P2 = 30;
    private static final int DeltaV = 31, n = 32;

    @FXML
    private Button btCalculer;
    private boolean erreurDeSaisie = false;
    @FXML
    private RadioButton instant, rap20, lent1, lent2, lent3, perso;

    private final Verin verin = new Verin();
    private final Pump pompe = new Pump();
    private final Accu accu = new Accu();
    @FXML
    private void buttonCalculer() {
        try {
                int count = 0;
        listeDesTextfield.clear();
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

    private final ArrayList<Method> lstSet = new ArrayList<>();
    private final ArrayList<Method> lstGet = new ArrayList<>();
    private final ArrayList<Method> lstCalcul = new ArrayList<>();

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

    private final ArrayList<TextField> listeDesTextfield = new ArrayList<>();

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
   /* private void recopTextfieldDansCompo() throws IllegalAccessException, InvocationTargetException {

        for (int i = dfond; i < n+1; i++) {  // Textfields IMPORTANTS
            if (!listeDesTextfield.get(i).getText().isEmpty()) {     // Si pas vide
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replaceAll("\\h",""));
                if (i < course + 1) {                                // 3 parametres du verin diam.
                    ComparerValeurEntreEtExistant(i, verin);
                    EcrireDansCoposant(i, verin);
                }
                if (i > debit-1 & i < cyl + 1) {                     // 3 parametres de la pompe
                    ComparerValeurEntreEtExistant(i, pompe);
                    EcrireDansCoposant(i, pompe);
                }
                if (i > V0-1 & i<n+1) {
                    EcrireDansCoposant(i, accu);
                }
                 /*if (i == V0) {                            // accu
                     ComparerValeurEntreEtExistant(i, accu);
                     if (modifAccu)
                         accu.setDeltaV(0);
                     modifAccu = false;
                     EcrireDansCoposant(i, accu);
                 }
                 if (i == P1) {                            // accu
                     ComparerValeurEntreEtExistant(i, accu);
                     if (modifAccu)
                         accu.setDeltaV(0);
                     modifAccu = false;
                     EcrireDansCoposant(i, accu);
                 }
                 if (i == P2) {                            // accu
                     ComparerValeurEntreEtExistant(i, accu);
                     if (modifAccu)
                         accu.setDeltaV(0);
                     modifAccu = false;
                     EcrireDansCoposant(i, accu);
                 }
                 if (i == DeltaV) {                            // accu
                     ComparerValeurEntreEtExistant(i, accu);
                     if (modifAccu)
                         accu.setV0(0);
                     modifAccu = false;
                     if (accu.getDeltaV()!=0)
                     EcrireDansCoposant(i, accu);
                 }
            }
            else {
                if (i < vitdiff + 1) {
                    initialiserCoposant(i, verin);
                }
                if (i > debit-1 & i < rendement + 1) {
                    initialiserCoposant(i, pompe);
                }
                if (i > V0-1 & i<n+1) {
                    initialiserCoposant(i, accu);
                }
            }
        }
        for (int i = dfond; i < n; i++) {            // Textfields SECONDAIRES
            if (!listeDesTextfield.get(i).getText().isEmpty()) {
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replaceAll("\\h",""));
                if (i > sfond-1 & i < tpsdiff + 1) {
                    if (modifVerin) initialiserCoposant(i, verin);
                    else EcrireDansCoposant(i, verin);
                }
                if (i > vitDeRot-1 & i < rendement + 1) {
                    if (modifPompe) initialiserCoposant(i, pompe);
                    else EcrireDansCoposant(i, pompe);
                }
                if (i > V1-1 & i < P0+1) {
                    initialiserCoposant(i, accu);
                }
            }
        }
        modifVerin = false;
        modifPompe = false;
    }*/
    private void recopTextfieldDansCompo() throws IllegalAccessException, InvocationTargetException {

        for (int i = dfond; i < n+1; i++) {  // Textfields IMPORTANTS
            if (!listeDesTextfield.get(i).getText().isEmpty()) {     // Si pas vide
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replaceAll("\\h",""));
                if (i < tpsdiff+1) {
                    EcrireDansCoposant(i, verin);
                }
                if (i > debit-1 & i < rendement + 1) {
                    EcrireDansCoposant(i, pompe);
                }
                if (i > V0-1 & i<n+1) {
                    EcrireDansCoposant(i, accu);
                }
            }
            else {
                if (i < tpsdiff + 1) {
                    initialiserCoposant(i, verin);
                }
                if (i > debit-1 & i < rendement + 1) {
                    initialiserCoposant(i, pompe);
                }
                if (i > V0-1 & i<n+1) {
                    initialiserCoposant(i, accu);
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
    private void EcrireDansCoposant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        lstSet.get(i).invoke(composant, valeurDuParametre);
        System.out.println("j'ecris " + valeurDuParametre + " dans: " + listeDesTextfield.get(i).getId());
    }
    private void initialiserCoposant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        switch (i) {
            case (rendement) -> {
                lstSet.get(i).invoke(composant, 80);
                System.out.println("j'ecris " + 80.0d + " dans: " + listeDesTextfield.get(i).getId());
            }
            case (n) -> {
                lstSet.get(i).invoke(composant, 1.4d);
                System.out.println("j'ecris " + 1.4d + " dans: " + listeDesTextfield.get(i).getId());
            }
            default -> {
                lstSet.get(i).invoke(composant, 0.0d);
                System.out.println("j'ecris " + 0.0d + " dans: " + listeDesTextfield.get(i).getId());
            }
        }
    }

    private void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres");
        System.out.println();
        double valeurDuParametre;
        for (int i = dfond; i < tpsdiff + 1; i++) { // VERIN
                valeurDuParametre = (double) lstGet.get(i).invoke(verin);
                if (valeurDuParametre == 0.0d) {
                    lstCalcul.get(i).invoke(verin);
                    valeurDuParametre = (double) lstGet.get(i).invoke(verin);
                    System.out.println("je calcul : " + listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
                }
        }
        for (int i = debit; i < rendement + 1; i++) { // POMPE
                valeurDuParametre = (double) lstGet.get(i).invoke(pompe);
                if (valeurDuParametre == 0.0d) {
                    lstCalcul.get(i).invoke(pompe);
                    valeurDuParametre = (double) lstGet.get(i).invoke(pompe);
                    System.out.println("je calcul : " + listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
                }
        }
        for (int i = V0; i < n + 1; i++) { // Accu
                valeurDuParametre = (double) lstGet.get(i).invoke(accu);
                if (valeurDuParametre == 0.0d) {
                    lstCalcul.get(i).invoke(accu);
                    valeurDuParametre = (double) lstGet.get(i).invoke(accu);
                    System.out.println("je calcul : " + listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
                }
        }
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

    @FXML
    private TextField txtDFond, txtDTige, txtCourse;
    @FXML
    private TextField txtSFond, txtSAnn, txtR, txtrInv, txtVFond, txtVTige;
    @FXML
    private TextField txtForceSortie, txtForceRentree, txtForceDiff, txtVitSortie, txtVitRentree, txtVitDiff, txtTpsSortie, txtTpsRentree, txtTpsDiff;
    @FXML
    private void modifDFond(){
        txtSFond.setText(""); txtSAnn.setText("");
        txtVFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifDTige(){
        txtSAnn.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifCourse(){
        txtVTige.setText(""); txtVFond.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifSFond(){
        txtDFond.setText(""); txtSAnn.setText("");
        txtVFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifSAnn(){
        txtDTige.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifR(){
        txtDTige.setText(""); txtDFond.setText(""); txtSFond.setText(""); txtSAnn.setText("");
        txtrInv.setText(""); txtVFond.setText(""); txtVTige.setText(""); txtrInv.setText("");
        txtForceRentree.setText(""); txtForceDiff.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifRinv(){
        txtDTige.setText(""); txtDFond.setText(""); txtSFond.setText(""); txtSAnn.setText("");
        txtR.setText(""); txtVFond.setText(""); txtVTige.setText(""); txtrInv.setText("");
        txtForceRentree.setText(""); txtForceDiff.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifVFond(){
        txtDFond.setText(""); txtSAnn.setText("");
        txtSFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifVTige(){
        txtDTige.setText(""); txtSAnn.setText(""); txtCourse.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifForceSortie(){
        txtDFond.setText(""); txtSAnn.setText("");
        txtSFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtVFond.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifForceRentree(){
        txtSAnn.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtDTige.setText(""); txtForceDiff.setText("");
        txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifVitSortie(){
        txtDFond.setText(""); txtSAnn.setText("");
        txtSFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtVFond.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtForceSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifVitRentree(){
        txtSAnn.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtDTige.setText(""); txtForceDiff.setText("");
        txtForceRentree.setText(""); txtVitDiff.setText("");
        txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifTpsSortie(){
        txtDFond.setText(""); txtSAnn.setText("");
        txtSFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtVFond.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtForceSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifTpsRentree(){
        txtSAnn.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtDTige.setText(""); txtForceDiff.setText("");
        txtForceRentree.setText(""); txtVitDiff.setText("");
        txtVitRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void RAZverin(){
        txtDFond.setText(""); txtDTige.setText("");
        txtSFond.setText(""); txtSAnn.setText("");
        txtVFond.setText(""); txtVTige.setText(""); txtR.setText(""); txtrInv.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }

    @FXML
    private TextField txtDebit, txtPression, txtCyl, txtVitDeRotation, txtPwrHyd, txtPwrMeca, txtRendement;
    @FXML
    private void modifDebit(){
        txtCyl.setText(""); txtPwrHyd.setText(""); txtPwrMeca.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifPression(){
        txtPwrHyd.setText(""); txtPwrMeca.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
    }
    @FXML
    private void modifCyl(){
        txtPwrHyd.setText(""); txtPwrMeca.setText(""); txtDebit.setText("");
        txtPwrHyd.setText(""); txtPwrMeca.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifVitDeRot(){
        txtPwrHyd.setText(""); txtPwrMeca.setText(""); txtCyl.setText(""); txtPwrHyd.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
    }
    @FXML
    private void modifPwrHyd(){
        txtPression.setText(""); txtPwrMeca.setText(""); txtDebit.setText("");
        txtCyl.setText(""); txtVitDeRotation.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
    }
    @FXML
    private void modifPwrMeca(){
        txtPression.setText(""); txtPwrHyd.setText(""); txtDebit.setText("");
        txtCyl.setText(""); txtVitDeRotation.setText("");
        txtVitSortie.setText(""); txtVitRentree.setText(""); txtVitDiff.setText("");
        txtTpsSortie.setText(""); txtTpsRentree.setText(""); txtTpsDiff.setText("");
        txtForceSortie.setText(""); txtForceRentree.setText(""); txtForceDiff.setText("");
    }
    @FXML
    private void modifRendement(){
        txtPwrMeca.setText("");
    }

    @FXML
    private TextField txtV0, txtV1, txtV2, txtP0, txtDeltaV, txtn;
    @FXML
    private void gestionDesRadioButton() throws IllegalAccessException, InvocationTargetException{
        resetDeltaV();
        if (instant.isSelected()) {
            accu.setn(1.6);
            txtn.setText("1.6");
            System.out.println("Radio button selectionné: Instantanée _ donc n = 1.6");
            System.out.println();
        }
        if (rap20.isSelected()) {
            accu.setn(1.35);
            txtn.setText("1.35");
            System.out.println("Radio button selectionné: 20S _ donc n = 1.35");
            System.out.println();
        }
        if (lent1.isSelected()) {
            accu.setn(1.25);
            txtn.setText("1.25");
            System.out.println("Radio button selectionné: 1min _ donc n = 1.25");
            System.out.println();
        }
        if (lent2.isSelected()) {
            accu.setn(1.20);
            txtn.setText("1.2");
            System.out.println("Radio button selectionné: 2min _ donc n = 1.20");
            System.out.println();
        }
        if (lent3.isSelected()) {
            accu.setn(1.10);
            txtn.setText("1.1");
            System.out.println("Radio button selectionné: 4min _ donc n = 1.10");
            System.out.println();
        }
    }
    @FXML
    private void resetDeltaV(){
        txtDeltaV.setText("");
        txtV1.setText("");
        txtV2.setText("");
        txtP0.setText("");
    }
    @FXML
    private void resetV0(){
        txtV0.setText("");
        txtV1.setText("");
        txtV2.setText("");
        txtP0.setText("");
    }
}
