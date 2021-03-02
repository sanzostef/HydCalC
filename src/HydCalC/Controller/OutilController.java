package HydCalC.Controller;

import HydCalC.Class.Outil;
import HydCalC.HydCalCController;
import HydCalC.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OutilController implements Initializable {

    @FXML TextField txtOutil, txtFonction;
    @FXML TextArea txtDescription;
    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }
    Outil outil;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        outil = new Outil();
    }
    @FXML private void buttonImprimer() {
        outil.setOutil(txtOutil.getText());
        outil.setFonction(txtFonction.getText());
        outil.setDescription(txtDescription.getText());
        try {
            ApercuImpression();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ApercuImpression() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrlImprimer = Main.class.getResource("FXML/imprimer.fxml");
        loader.setLocation(xmlUrlImprimer);
        Parent root = loader.load();
        ImprimerController imprimerController = loader.getController();
        imprimerController.injection(hydCalCController);
        imprimerController.injection(this);
        imprimerController.controllerAppelant("outil");
        imprimerController.main();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Impression");
        stage.setScene(new Scene(root));
        stage.show();

    }

}



