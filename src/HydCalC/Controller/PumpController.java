package HydCalC.Controller;

import HydCalC.MainController;
import javafx.fxml.FXML;

import java.lang.reflect.InvocationTargetException;

public class PumpController {

    private static final int debit = 18, rendement = 24;

    private MainController mainController = new MainController();
    private VerinController dspVerin = new VerinController();

    public void injection(MainController controller) { this.mainController = controller; }
    public void injectionDspVerin(VerinController controller) { this.dspVerin = controller; }
    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException{
        System.out.println(" Entre dans les calculs sans paramètres Pompe:");
        System.out.println();
        double valeurDuParametre;
        for (int i = debit; i < rendement + 1; i++) { // POMPE
            valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.pompe);
            if (valeurDuParametre == 0.0d) {
                mainController.lstCalcul.get(i).invoke(mainController.pompe);
                valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.pompe);
                System.out.println("je calcul : " + mainController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML protected void modifDebit(){
        mainController.listeDesTextfield.get(MainController.cyl).setText("");
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifPression(){
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
    }
    @FXML private void modifCyl(){
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
        mainController.listeDesTextfield.get(MainController.debit).setText("");
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifVitDeRot(){
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
        mainController.listeDesTextfield.get(MainController.cyl).setText("");
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifPwrHyd(){
        mainController.listeDesTextfield.get(MainController.pression).setText("");
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
        mainController.listeDesTextfield.get(MainController.debit).setText("");
        mainController.listeDesTextfield.get(MainController.cyl).setText("");
        mainController.listeDesTextfield.get(MainController.vitDeRot).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
    }
    @FXML private void modifPwrMeca(){
        mainController.listeDesTextfield.get(MainController.pression).setText("");
        mainController.listeDesTextfield.get(MainController.pwrHyd).setText("");
        mainController.listeDesTextfield.get(MainController.debit).setText("");
        mainController.listeDesTextfield.get(MainController.cyl).setText("");
        mainController.listeDesTextfield.get(MainController.vitDeRot).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
    }
    @FXML private void modifRendement(){
        mainController.listeDesTextfield.get(MainController.pwrMeca).setText("");
    }
}
