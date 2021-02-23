package HydCalC.Controller;

import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import java.lang.reflect.InvocationTargetException;

public class VerinController {

    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println();
        System.out.println(" Entre dans les calculs sans paramètres verin:");
        System.out.println();
        double valeurDuParametre;
        for (int i = HydCalCController.dfond; i < HydCalCController.tpsdiff + 1; i++) { // VERIN
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.verin);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.verin);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.verin);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }
    @FXML private void modifDFond(){
        hydCalCController.listeDesTextfield.get(HydCalCController.sfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifDTige(){
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifCourse(){
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifSFond(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifSAnn(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifVFond(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifVTige(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.course).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifForceSortie(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifForceRentree(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifVitSortie(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifVitRentree(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifTpsSortie(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void modifTpsRentree(){
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.dtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
    @FXML private void RAZverin(){
        hydCalCController.listeDesTextfield.get(HydCalCController.dfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.dtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.sann).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vfond).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vtige).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.r).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.rinv).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcesortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcerentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.forcediff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitsortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.vitdiff).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpssortie).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsrentree).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.tpsdiff).setText("");
    }
}
