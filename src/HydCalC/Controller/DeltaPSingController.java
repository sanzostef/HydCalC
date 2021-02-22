package HydCalC.Controller;


import HydCalC.Class.DeltaPSing;
import HydCalC.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeltaPSingController implements Initializable{

    @FXML
    ComboBox<DeltaPSing.Incident> cbIncident;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIncident.setPromptText("Type de raccords");
        cbIncident.setItems(mainController.deltaPSinguliere.getIncidentList());
    }
    private MainController mainController = new MainController();
    public void injection(MainController controller) { this.mainController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println("Entre dans les calculs sans paramètres Pertes de charge Singulières");
        System.out.println();
        double valeurDuParametre;
        for (int i = MainController.diamSing; i < MainController.deltaPSing + 1; i++) { // Accu
            valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.deltaPSinguliere);
            if (valeurDuParametre == 0.0d) {
                mainController.lstCalcul.get(i).invoke(mainController.deltaPSinguliere);
                valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.deltaPSinguliere);
                System.out.println("je calcul : " + mainController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML
    private void choixIncident(){
        mainController.deltaPSinguliere.setCoeff(cbIncident.getValue().coeff);
        mainController.listeDesTextfield.get(MainController.deltaPSing).setText("");
        System.out.println(cbIncident.getValue().toString() + " sélectionné.");
    }
    @FXML private void modif(){
        mainController.listeDesTextfield.get(MainController.deltaPSing).setText("");
    }

}