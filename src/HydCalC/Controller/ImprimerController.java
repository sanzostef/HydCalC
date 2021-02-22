package HydCalC.Controller;

import HydCalC.Class.Client;
import HydCalC.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ImprimerController implements Initializable{

    public void initialize(URL url, ResourceBundle rb) {   }


    private MainController mainController = new MainController();
    public void injection(MainController controller) { this.mainController = controller; }
  //  private ClientController clientController = new ClientController();
  //  public void injection(ClientController controller) { this.clientController = controller; }

}