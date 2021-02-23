package HydCalC.Controller;

import HydCalC.Class.Client;
import HydCalC.Main;
import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML ComboBox<Client> cbClient;
    @FXML TextField raisonSociale, contact, tel, mail, adresse;
    Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new Client();
    }

    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    @FXML private void buttonImprimer() {
        client.setRaisonSociale(raisonSociale.getText());
        client.setContact(contact.getText());
        client.setTelephone(tel.getText());
        client.setMail(mail.getText());
        client.setAdresse(adresse.getText());

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
        imprimerController.main();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Impression");
        stage.setScene(new Scene(root));
        stage.show();

        }

}




