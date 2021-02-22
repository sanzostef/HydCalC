package HydCalC.Controller;

import HydCalC.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeltaPLineaireController implements Initializable{

    @FXML TextField masseVol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        masseVol.setText("850");
    }
    private MainController mainController = new MainController();
    public void injection(MainController controller) { this.mainController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres Pertes de charge lineaire");
        System.out.println();
        double valeurDuParametre;
        for (int i = MainController.diamLin; i < MainController.deltaPLin+1; i++) { // Accu
            valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.deltaPLineaire);
            if (valeurDuParametre == 0.0d) {
                mainController.lstCalcul.get(i).invoke(mainController.deltaPLineaire);
                valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.deltaPLineaire);
                System.out.println("je calcul : " + mainController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }
    @FXML private void modif(){
        mainController.listeDesTextfield.get(MainController.deltaPLin).setText("");
    }

}