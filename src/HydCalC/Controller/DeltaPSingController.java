package HydCalC.Controller;


import HydCalC.Class.DeltaPSing;
import HydCalC.HydCalCController;
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
        cbIncident.setItems(hydCalCController.deltaPSinguliere.getIncidentList());
    }
    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println("Entre dans les calculs sans paramètres Pertes de charge Singulières");
        System.out.println();
        double valeurDuParametre;
        for (int i = HydCalCController.diamSing; i < HydCalCController.deltaPSing + 1; i++) { // Accu
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.deltaPSinguliere);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.deltaPSinguliere);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.deltaPSinguliere);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML
    private void choixIncident(){
        hydCalCController.deltaPSinguliere.setCoeff(cbIncident.getValue().coeff);
        hydCalCController.listeDesTextfield.get(HydCalCController.deltaPSing).setText("");
        System.out.println(cbIncident.getValue().toString() + " sélectionné.");
    }
    @FXML private void modif(){
        hydCalCController.listeDesTextfield.get(HydCalCController.deltaPSing).setText("");
    }

}