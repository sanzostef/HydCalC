package HydCalC;

import javafx.fxml.FXML;
import java.lang.reflect.InvocationTargetException;

public class VerinController {

    private static final int dfond = 0, tpsdiff = 17;
    private MainController mainController = new MainController();
    void injection(MainController controller) { this.mainController = controller; }

    void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println();
        System.out.println(" Entre dans les calculs sans paramètres verin:");
        System.out.println();
        double valeurDuParametre;
        for (int i = dfond; i < tpsdiff + 1; i++) { // VERIN
            valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.verin);
            if (valeurDuParametre == 0.0d) {
                mainController.lstCalcul.get(i).invoke(mainController.verin);
                valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.verin);
                System.out.println("je calcul : " + mainController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }
    @FXML private void modifDFond(){
        mainController.listeDesTextfield.get(MainController.sfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifDTige(){
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifCourse(){
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifSFond(){
        mainController.listeDesTextfield.get(MainController.dfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifSAnn(){
        mainController.listeDesTextfield.get(MainController.dtige).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifVFond(){
        mainController.listeDesTextfield.get(MainController.dfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.sfond).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifVTige(){
        mainController.listeDesTextfield.get(MainController.dtige).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.course).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifForceSortie(){
        mainController.listeDesTextfield.get(MainController.dfond).setText("");
        mainController.listeDesTextfield.get(MainController.sfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifForceRentree(){
        mainController.listeDesTextfield.get(MainController.dtige).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifVitSortie(){
        mainController.listeDesTextfield.get(MainController.dfond).setText("");
        mainController.listeDesTextfield.get(MainController.sfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifVitRentree(){
        mainController.listeDesTextfield.get(MainController.dtige).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifTpsSortie(){
        mainController.listeDesTextfield.get(MainController.dfond).setText("");
        mainController.listeDesTextfield.get(MainController.sfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void modifTpsRentree(){
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.dtige).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
    @FXML private void RAZverin(){
        mainController.listeDesTextfield.get(MainController.dfond).setText("");
        mainController.listeDesTextfield.get(MainController.dtige).setText("");
        mainController.listeDesTextfield.get(MainController.sfond).setText("");
        mainController.listeDesTextfield.get(MainController.sann).setText("");
        mainController.listeDesTextfield.get(MainController.vfond).setText("");
        mainController.listeDesTextfield.get(MainController.vtige).setText("");
        mainController.listeDesTextfield.get(MainController.r).setText("");
        mainController.listeDesTextfield.get(MainController.rinv).setText("");
        mainController.listeDesTextfield.get(MainController.forcesortie).setText("");
        mainController.listeDesTextfield.get(MainController.forcerentree).setText("");
        mainController.listeDesTextfield.get(MainController.forcediff).setText("");
        mainController.listeDesTextfield.get(MainController.vitsortie).setText("");
        mainController.listeDesTextfield.get(MainController.vitrentree).setText("");
        mainController.listeDesTextfield.get(MainController.vitdiff).setText("");
        mainController.listeDesTextfield.get(MainController.tpssortie).setText("");
        mainController.listeDesTextfield.get(MainController.tpsrentree).setText("");
        mainController.listeDesTextfield.get(MainController.tpsdiff).setText("");
    }
}
