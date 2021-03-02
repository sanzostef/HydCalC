package HydCalC.Controller;


import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class GicleurController {

    @FXML TextField masseVol;

    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println("Entre dans les calculs sans paramètres Pertes de charges Gicleur");
        System.out.println();
        double valeurDuParametre;
        for (int i = HydCalCController.diamOrifice; i < HydCalCController.deltaPOrifice + 1; i++) {
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.gicleur);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.gicleur);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.gicleur);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML private void modif() {
        hydCalCController.listeDesTextfield.get(HydCalCController.deltaPOrifice).setText("");
    }


}