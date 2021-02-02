package HydCalC;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Controller {

    //DecimalFormat df = new DecimalFormat("###.##");
    NumberFormat df = NumberFormat.getInstance();

    private static final int dfond = 0;
    private static final int dtige = 1;
    private static final int course = 2;
    private static final int sfond = 3;
    private static final int sann = 4;
    private static final int r = 5;
    private static final int rinv = 6;
    private static final int vfond = 7;
    private static final int vtige = 8;
    private static final int forcesortie = 9;
    private static final int forcerentree = 10;
    private static final int forcediff = 11;
    private static final int vitsortie = 12;
    private static final int vitrentree = 13;
    private static final int vitdiff = 14;
    private static final int tpssortie = 15;
    private static final int tpsrentree = 16;
    private static final int tpsdiff = 17;
    private static final int debit = 18;
    private static final int pression = 19;
    private static final int cyl = 20;
    private static final int vitDeRot = 21;
    private static final int pwrHyd = 22;
    private static final int pwrMeca = 23;
    private static final int rendement = 24;

    @FXML
    private Button btCalculer;
    private boolean erreurDeSaisie = false;

    public void buttonClicked() {
        int count = 0;
        listeDesTextfield.clear();
        erreurDeSaisie = false;
        verifConversion_StringToDouble_possible();
        if (erreurDeSaisie) {
            listeDesTextfield.clear();
            return;
        }

        creationDesListesDeMethodes();
        try {
        recopTextfieldDansCompo();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            do {
                calculerSansParametreExterne();

                if ((double) lstGet.get(pression).invoke(pompe) != 0.0d) {
                    verin.calculerSFond((double) lstGet.get(pression).invoke(pompe));
                    verin.calculerSAnnulaire((double) lstGet.get(pression).invoke(pompe));
                    verin.calculerForceSortie((double) lstGet.get(pression).invoke(pompe));
                    verin.calculerForceRentree((double) lstGet.get(pression).invoke(pompe));
                    verin.calculerForceDiff((double) lstGet.get(pression).invoke(pompe));
                }
                calculerSansParametreExterne();
                if ((double) lstGet.get(debit).invoke(pompe) != 0.0d) {
                    verin.calculerVFond((double) lstGet.get(debit).invoke(pompe));
                    verin.calculerVTige((double) lstGet.get(debit).invoke(pompe));
                    verin.calculerVitSortie((double) lstGet.get(debit).invoke(pompe));
                    verin.calculerVitRentree((double) lstGet.get(debit).invoke(pompe));
                    verin.calculerVitDiff((double) lstGet.get(debit).invoke(pompe));
                }
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
            } while (count < 1);
            recopierComposantsDansTextField();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private final Verin verin = new Verin();
    private final Pump pompe = new Pump();

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
                String s = (((TextField) node).getText()).replace(',', '.').replace("\\s+", "");
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

    private boolean modif = false;
    private double valeurDuParametre;

    private void recopTextfieldDansCompo() throws IllegalAccessException, InvocationTargetException {

         for (int i = dfond; i < rendement + 1; i++) {
             if (!listeDesTextfield.get(i).getText().isEmpty()) {
                 valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replace("\\s+", ""));
                 if (i < course + 1) {EcrireDansCoposant(i, verin);}
                 if (i > debit-1 & i < cyl + 1) { EcrireDansCoposant(i, pompe); }
             }
             else {
                  if (i < vitdiff + 1) {initialiserCoposant(i, verin);}
                  if (i > debit-1 & i < rendement + 1) {initialiserCoposant(i, pompe); }
             }
         }
         for (int i = dfond; i < rendement + 1; i++) {
             if (!listeDesTextfield.get(i).getText().isEmpty()) {
                 valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',', '.').replace("\\s+", ""));
                 if (i == sfond & i < tpsdiff + 1) {
                     if (modif) initialiserCoposant(i, verin);
                     else EcrireDansCoposant(i, verin);
                 }
                 if (i == vitDeRot & i < rendement + 1 & modif) {
                     if (modif) initialiserCoposant(i, pompe);
                     else EcrireDansCoposant(i, pompe);
                 }
             }
         }
         modif = false;
    }

    private void ComparerValeurEntreEtExistant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        if ((valeurDuParametre != (double) lstGet.get(i).invoke(composant)))
            modif = true;
    }

    private void EcrireDansCoposant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        lstSet.get(i).invoke(composant, valeurDuParametre);
        System.out.println("j'ecris " + valeurDuParametre + " dans: " + listeDesTextfield.get(i).getId());
    }

    private void initialiserCoposant(int i, Object composant) throws IllegalAccessException, InvocationTargetException {
        if (i == rendement) {
            lstSet.get(i).invoke(composant, 80);
            System.out.println("j'ecris " + 80.0d + " dans: " + listeDesTextfield.get(i).getId());
        } else {
            lstSet.get(i).invoke(composant, 0.0d);
            System.out.println("j'ecris " + 0.0d + " dans: " + listeDesTextfield.get(i).getId());
        }
    }

    private void calculerSansParametreExterne() {
        System.out.println(" Entre dans les calculs sans paramètres");
        System.out.println();
        double valeurDuParametre;
        for (int i = dfond; i < tpsdiff + 1; i++) { // VERIN
            try {
                valeurDuParametre = (double) lstGet.get(i).invoke(verin);
                if (valeurDuParametre == 0.0d) {
                    lstCalcul.get(i).invoke(verin);
                    valeurDuParametre = (double) lstGet.get(i).invoke(verin);
                    System.out.println("je calcul : " + listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (int i = debit; i < rendement + 1; i++) { // POMPE
            try {
                valeurDuParametre = (double) lstGet.get(i).invoke(pompe);
                if (valeurDuParametre == 0.0d) {
                    lstCalcul.get(i).invoke(pompe);
                    valeurDuParametre = (double) lstGet.get(i).invoke(pompe);
                    System.out.println("je calcul : " + listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    private void recopierComposantsDansTextField() {
        System.out.println("Recopie Valeurs des composants dans les textfields");
        double valeurDuParametre;
        for (int i = dfond; i < tpsdiff + 1; i++) { // NB de textfields Verin : 18
            try {
                valeurDuParametre = (double) lstGet.get(i).invoke(verin);
                if (valeurDuParametre != 0.0d) {
                    listeDesTextfield.get(i).setText(df.format(valeurDuParametre));
                }
                System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (int i = debit; i < rendement + 1; i++) { // 1er textfields Pompe: 18 => dernier: 25
            try {
                valeurDuParametre = (double) lstGet.get(i).invoke(pompe);
                if (valeurDuParametre != 0.0d) {
                    listeDesTextfield.get(i).setText(df.format(valeurDuParametre));
                }
                System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fini");
    }

}
   /* private void recopierTextfiedlsDansComposants() {
        System.out.println("Recopie valeurs des textfields dans les composants:");
        System.out.println();
        for (int i = 0; i < 18; i++) {
            double valeurDuParametre;
            if (!listeDesTextfield.get(i).getText().isEmpty()) {
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',','.').replace("\\s+",""));
                try {
                    lstSet.get(i).invoke(verin, valeurDuParametre);
                    System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            else {
                valeurDuParametre = 0.0d;
                try {
                    lstSet.get(i).invoke(verin, valeurDuParametre);
                    System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 18; i < 24; i++) {
            double valeurDuParametre;
            if (!listeDesTextfield.get(i).getText().isEmpty()) {
                valeurDuParametre = Double.parseDouble(listeDesTextfield.get(i).getText().replace(',','.').replace("\\s+",""));
                try {
                    lstSet.get(i).invoke(pompe, valeurDuParametre);
                    System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            else {
                valeurDuParametre = 0.0d;
                try {
                    lstSet.get(i).invoke(pompe, valeurDuParametre);
                    System.out.println(listeDesTextfield.get(i).getId() + " - " + valeurDuParametre);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("fini");
        System.out.println();
    } */

