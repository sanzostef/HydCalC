package HydCalC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeltaPController implements Initializable{

    @FXML
    ComboBox<String> cbIncident;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIncident.setPromptText("Type de raccords");
        ObservableMap<String, Double> listcoeff = FXCollections.observableMap(DeltaP.getCoeff());
        cbIncident.getItems().setAll(listcoeff.keySet());
    }
    private MainController mainController = new MainController();
    void injection(MainController controller) { this.mainController = controller; }

    private void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException {
        System.out.println(" Entre dans les calculs sans paramètres");
        System.out.println();
        double valeurDuParametre;

        System.out.println("fini");
        System.out.println();
    }

    @FXML
    private void choixIncident(){

    }
}
