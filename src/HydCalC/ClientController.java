package HydCalC;

import HydCalC.Class.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable{

    @FXML Pane dspImprimer;
    @FXML imprimerController dspImprimerController;
    @FXML ComboBox<Client> cbClient;
    @FXML TextField raisonSociale, contact, telephone, mail, adresse;
    Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        try {
            imprimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void imprimer() throws IOException {
       /* FXMLLoader imprimLoader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("imprimer.fxml");
        //  Parent root = FXMLLoader.load(getClass().getResource("HydCalC.fxml"));
        imprimLoader.setLocation(xmlUrl);
        Parent root = imprimLoader.load();
        newStage.setTitle("Impression");
        newStage.setScene(new Scene(root));
        newStage.show();  */
    }


}