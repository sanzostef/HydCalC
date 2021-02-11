package HydCalC;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.lang.reflect.InvocationTargetException;

public class AccuController {

    @FXML private RadioButton instant, rap20, lent1, lent2, lent3, perso;
    @FXML private TextField txtV0, txtV1, txtV2, txtP0, txtDeltaV, txtn;
    private static final int V0 = 25, n = 32;

    private MainController mainController = new MainController();

    void injection(MainController controller) { this.mainController = controller; }

    void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres Accumulateur:");
        System.out.println();
        double valeurDuParametre;
        for (int i = V0; i < n + 1; i++) { // Accu
            valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.accu);
            if (valeurDuParametre == 0.0d) {
                mainController.lstCalcul.get(i).invoke(mainController.accu);
                valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.accu);
                System.out.println("je calcul : " + mainController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML
    private void gestionDesRadioButton() {
        resetDeltaV();
        if (instant.isSelected()) {
            mainController.accu.setn(1.6);
            txtn.setText("1.6");
            System.out.println("Radio button sélectionné: Instantanée _ donc n = 1.6");
            System.out.println();
        }
        if (rap20.isSelected()) {
            mainController.accu.setn(1.35);
            txtn.setText("1.35");
            System.out.println("Radio button sélectionné: 20S _ donc n = 1.35");
            System.out.println();
        }
        if (lent1.isSelected()) {
            mainController.accu.setn(1.25);
            txtn.setText("1.25");
            System.out.println("Radio button sélectionné: 1min _ donc n = 1.25");
            System.out.println();
        }
        if (lent2.isSelected()) {
            mainController.accu.setn(1.20);
            txtn.setText("1.2");
            System.out.println("Radio button sélectionné: 2min _ donc n = 1.20");
            System.out.println();
        }
        if (lent3.isSelected()) {
            mainController.accu.setn(1.10);
            txtn.setText("1.1");
            System.out.println("Radio button sélectionné: 4min _ donc n = 1.10");
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
