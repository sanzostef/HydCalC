package HydCalC.Controller;

import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.lang.reflect.InvocationTargetException;

public class AccuController {

    @FXML private RadioButton instant, rap20, lent1, lent2, lent3;

    private HydCalCController hydCalCController = new HydCalCController();

    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres Accumulateur:");
        System.out.println();
        double valeurDuParametre;
        for (int i = HydCalCController.V0; i < HydCalCController.n + 1; i++) { // Accu
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.accu);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.accu);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.accu);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML
    private void gestionDesRadioButton() {
        resetDeltaV();
        if (instant.isSelected()) {
            hydCalCController.accu.setn(1.6);
            hydCalCController.listeDesTextfield.get(HydCalCController.n).setText("1.6");
            System.out.println("Radio button sélectionné: Instantanée _ donc n = 1.6");
            System.out.println();
        }
        if (rap20.isSelected()) {
            hydCalCController.accu.setn(1.35);
            hydCalCController.listeDesTextfield.get(HydCalCController.n).setText("1.35");
            System.out.println("Radio button sélectionné: 20S _ donc n = 1.35");
            System.out.println();
        }
        if (lent1.isSelected()) {
            hydCalCController.accu.setn(1.25);
            hydCalCController.listeDesTextfield.get(HydCalCController.n).setText("1.25");
            System.out.println("Radio button sélectionné: 1min _ donc n = 1.25");
            System.out.println();
        }
        if (lent2.isSelected()) {
            hydCalCController.accu.setn(1.20);
            hydCalCController.listeDesTextfield.get(HydCalCController.n).setText("1.2");
            System.out.println("Radio button sélectionné: 2min _ donc n = 1.20");
            System.out.println();
        }
        if (lent3.isSelected()) {
            hydCalCController.accu.setn(1.10);
            hydCalCController.listeDesTextfield.get(HydCalCController.n).setText("1.1");
            System.out.println("Radio button sélectionné: 4min _ donc n = 1.10");
            System.out.println();
        }
    }
    @FXML
    private void resetDeltaV(){
        hydCalCController.listeDesTextfield.get(HydCalCController.DeltaV).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.V1).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.V2).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.P0).setText("");
    }
    @FXML
    private void resetV0(){
        hydCalCController.listeDesTextfield.get(HydCalCController.V0).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.V1).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.V2).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.P0).setText("");
    }
}
