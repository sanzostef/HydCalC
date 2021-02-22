package HydCalC.Controller;

import HydCalC.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.lang.reflect.InvocationTargetException;

public class AccuController {

    @FXML private RadioButton instant, rap20, lent1, lent2, lent3;

    private MainController mainController = new MainController();

    public void injection(MainController controller) { this.mainController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres Accumulateur:");
        System.out.println();
        double valeurDuParametre;
        for (int i = MainController.V0; i < MainController.n + 1; i++) { // Accu
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
            mainController.listeDesTextfield.get(MainController.n).setText("1.6");
            System.out.println("Radio button sélectionné: Instantanée _ donc n = 1.6");
            System.out.println();
        }
        if (rap20.isSelected()) {
            mainController.accu.setn(1.35);
            mainController.listeDesTextfield.get(MainController.n).setText("1.35");
            System.out.println("Radio button sélectionné: 20S _ donc n = 1.35");
            System.out.println();
        }
        if (lent1.isSelected()) {
            mainController.accu.setn(1.25);
            mainController.listeDesTextfield.get(MainController.n).setText("1.25");
            System.out.println("Radio button sélectionné: 1min _ donc n = 1.25");
            System.out.println();
        }
        if (lent2.isSelected()) {
            mainController.accu.setn(1.20);
            mainController.listeDesTextfield.get(MainController.n).setText("1.2");
            System.out.println("Radio button sélectionné: 2min _ donc n = 1.20");
            System.out.println();
        }
        if (lent3.isSelected()) {
            mainController.accu.setn(1.10);
            mainController.listeDesTextfield.get(MainController.n).setText("1.1");
            System.out.println("Radio button sélectionné: 4min _ donc n = 1.10");
            System.out.println();
        }
    }
    @FXML
    private void resetDeltaV(){
        mainController.listeDesTextfield.get(MainController.DeltaV).setText("");
        mainController.listeDesTextfield.get(MainController.V1).setText("");
        mainController.listeDesTextfield.get(MainController.V2).setText("");
        mainController.listeDesTextfield.get(MainController.P0).setText("");
    }
    @FXML
    private void resetV0(){
        mainController.listeDesTextfield.get(MainController.V0).setText("");
        mainController.listeDesTextfield.get(MainController.V1).setText("");
        mainController.listeDesTextfield.get(MainController.V2).setText("");
        mainController.listeDesTextfield.get(MainController.P0).setText("");
    }
}
