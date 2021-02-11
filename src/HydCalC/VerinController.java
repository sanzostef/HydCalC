package HydCalC;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    @FXML TextField txtDFond, txtDTige, txtCourse;
    @FXML TextField txtSFond, txtSAnn, txtR, txtrInv, txtVFond, txtVTige;
    @FXML TextField txtForceSortie, txtForceRentree, txtForceDiff, txtVitSortie, txtVitRentree, txtVitDiff, txtTpsSortie, txtTpsRentree, txtTpsDiff;
    @FXML private void modifDFond(){
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
}
