package HydCalC;

import HydCalC.Class.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class imprimerController implements Initializable{

    Client client;
    @FXML
    ComboBox<Client> cbClient;
    @FXML TextField raisonSociale, contact, telephone, mail, adresse;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       client = new Client();
        //cbClient.setPromptText("Client");
        //  cbClient.setItems(BddClient.getClientList());
    }
    private MainController mainController = new MainController();
    void injection(MainController controller) { this.mainController = controller; }

    @FXML private void buttonImprimer() {
        client.setRaisonSociale(raisonSociale.getText());
        client.setContact(contact.getText());
        client.setTelephone(telephone.getText());
        client.setMail(mail.getText());
        client.setAdresse(adresse.getText());


    }

}