package HydCalC.Controller;

import HydCalC.HydCalCController;
import javafx.fxml.FXML;

import java.lang.reflect.InvocationTargetException;

public class PumpController {

    private static final int debit = 18, rendement = 24;

    private HydCalCController hydCalCController = new HydCalCController();
    private VerinController dspVerinController = new VerinController();

    public void injection(HydCalCController controller) { this.hydCalCController = controller; }
    public void injection(VerinController controller) { this.dspVerinController = controller; }
    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException{
        System.out.println(" Entre dans les calculs sans paramètres Pompe:");
        System.out.println();
        double valeurDuParametre;
        for (int i = debit; i < rendement + 1; i++) { // POMPE
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.pompe);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.pompe);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.pompe);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML protected void modifDebit(){
        hydCalCController.listeDesTextfield.get(HydCalCController.cyl).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifPression(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
    }
    @FXML private void modifCyl(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.debit).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifVitDeRot(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.cyl).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifPwrHyd(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pression).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.debit).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.cyl).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitDeRot).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
    }
    @FXML private void modifPwrMeca(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pression).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrHyd).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.debit).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.cyl).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitDeRot).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
    }
    @FXML private void modifRendement(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMeca).setText("");
    }
}
