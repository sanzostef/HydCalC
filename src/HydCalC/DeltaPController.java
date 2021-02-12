package HydCalC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeltaPController implements Initializable{

    @FXML
    ComboBox<DeltaP.Incident> cbIncident;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIncident.setPromptText("Type de raccords");
        cbIncident.setItems(DeltaP.getIncidentList());
    }
    private MainController mainController = new MainController();
    void injection(MainController controller) { this.mainController = controller; }

    void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres Pertes de charge");
        System.out.println();
        double valeurDuParametre;
        for (int i = mainController.diam; i < mainController.deltaP + 1; i++) { // Accu
            valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.deltaPSing);
            if (valeurDuParametre == 0.0d) {
                mainController.lstCalcul.get(i).invoke(mainController.deltaPSing);
                valeurDuParametre = (double) mainController.lstGet.get(i).invoke(mainController.deltaPSing);
                System.out.println("je calcul : " + mainController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML
    private void choixIncident(){
        DeltaP.setCoeff(cbIncident.getValue().coeff);
        mainController.listeDesTextfield.get(mainController.deltaP).setText("");
        System.out.println(cbIncident.getValue().toString() + " sélectionné.");
    }
    @FXML private void modif(){
        mainController.listeDesTextfield.get(mainController.deltaP).setText("");
    }

}