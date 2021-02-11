package HydCalC;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.lang.reflect.InvocationTargetException;

public class PumpController {

    private static final int debit = 18, rendement = 24;

    private MainController mainController = new MainController();
    private VerinController dspVerin = new VerinController();

    void injection(MainController controller) { this.mainController = controller; }
    void injectionDspVerin(VerinController controller) { this.dspVerin = controller; }

    @FXML private TextField txtDebit, txtPression, txtCyl, txtVitDeRotation, txtPwrHyd, txtPwrMeca;

    void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException{
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

    @FXML
    protected void modifDebit(){
        txtCyl.setText(""); txtPwrHyd.setText(""); txtPwrMeca.setText("");
        dspVerin.txtVitSortie.setText(""); dspVerin.txtVitRentree.setText(""); dspVerin.txtVitDiff.setText("");
        dspVerin.txtTpsSortie.setText(""); dspVerin.txtTpsRentree.setText(""); dspVerin.txtTpsDiff.setText("");
    }
    @FXML
    private void modifPression(){
        txtPwrHyd.setText(""); txtPwrMeca.setText(""); dspVerin.txtForceSortie.setText("");
        dspVerin.txtForceRentree.setText(""); dspVerin.txtForceDiff.setText("");
    }
    @FXML
    private void modifCyl(){
        txtPwrHyd.setText(""); txtPwrMeca.setText(""); txtDebit.setText("");
        txtPwrHyd.setText(""); txtPwrMeca.setText("");
        dspVerin.txtVitSortie.setText(""); dspVerin.txtVitRentree.setText("");
        dspVerin.txtVitDiff.setText(""); dspVerin.txtTpsSortie.setText("");
        dspVerin.txtTpsRentree.setText(""); dspVerin.txtTpsDiff.setText("");
    }
    @FXML
    private void modifVitDeRot(){
        txtPwrHyd.setText(""); txtPwrMeca.setText(""); txtCyl.setText(""); txtPwrHyd.setText("");
        dspVerin.txtVitSortie.setText(""); dspVerin.txtVitRentree.setText("");
        dspVerin.txtVitDiff.setText(""); dspVerin.txtTpsSortie.setText("");
        dspVerin.txtTpsRentree.setText(""); dspVerin.txtTpsDiff.setText("");
    }
    @FXML
    private void modifPwrHyd(){
        txtPression.setText(""); txtPwrMeca.setText(""); txtDebit.setText("");
        txtCyl.setText(""); txtVitDeRotation.setText("");
        dspVerin.txtVitSortie.setText(""); dspVerin.txtVitRentree.setText("");
        dspVerin.txtVitDiff.setText(""); dspVerin.txtTpsSortie.setText("");
        dspVerin.txtTpsRentree.setText(""); dspVerin.txtTpsDiff.setText("");
        dspVerin.txtForceSortie.setText(""); dspVerin.txtForceRentree.setText("");
        dspVerin.txtForceDiff.setText("");
    }
    @FXML
    private void modifPwrMeca(){
        txtPression.setText(""); txtPwrHyd.setText(""); txtDebit.setText("");
        txtCyl.setText(""); txtVitDeRotation.setText("");
        dspVerin.txtVitSortie.setText(""); dspVerin.txtVitRentree.setText("");
        dspVerin.txtVitDiff.setText(""); dspVerin.txtTpsSortie.setText("");
        dspVerin.txtTpsRentree.setText(""); dspVerin.txtTpsDiff.setText("");
        dspVerin.txtForceSortie.setText(""); dspVerin.txtForceRentree.setText("");
        dspVerin.txtForceDiff.setText("");
    }
    @FXML
    private void modifRendement(){
        txtPwrMeca.setText("");
    }
}
