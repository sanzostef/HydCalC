package HydCalC.Controller;

import HydCalC.HydCalCController;
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
    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres Pertes de charge lineaire");
        System.out.println();
        double valeurDuParametre;
        for (int i = HydCalCController.diamLin; i < HydCalCController.deltaPLin+1; i++) { // Accu
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.deltaPLineaire);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.deltaPLineaire);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.deltaPLineaire);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }
    @FXML private void modif(){
        hydCalCController.listeDesTextfield.get(HydCalCController.deltaPLin).setText("");
    }

}