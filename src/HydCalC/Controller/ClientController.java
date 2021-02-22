package HydCalC.Controller;

import HydCalC.Class.Client;
import HydCalC.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable{

    @FXML ComboBox<Client> cbClient;
    @FXML TextField raisonSociale, contact, telephone, mail, adresse;
    Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new Client();
    }
    private MainController mainController = new MainController();
    public void injection(MainController controller) { this.mainController = controller; }


    @FXML private void buttonImprimer() {
        client.setRaisonSociale(raisonSociale.getText());
        client.setContact(contact.getText());
        client.setTelephone(telephone.getText());
        client.setMail(mail.getText());
        client.setAdresse(adresse.getText());
        try {
            ApercuImpression();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ApercuImpression() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HydCalC/HydCalC/FXML/HydCalC.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}




