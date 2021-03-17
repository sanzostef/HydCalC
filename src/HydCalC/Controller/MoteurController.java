package HydCalC.Controller;

import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoteurController implements Initializable {

    @FXML
    public TextField txtηVol, txtηMeca, txtηtot;
    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtηVol.setText("0.97");
        txtηMeca.setText("0.85");
        txtηtot.setText("0.82");
    }
    public void calculerSansParametreExterne() throws IllegalAccessException, InvocationTargetException{
        System.out.println(" Entre dans les calculs sans paramètres Moteur:");
        System.out.println();
        double valeurDuParametre;
        for (int i = HydCalCController.couple; i < HydCalCController.ηtot + 1; i++) {
            valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.moteur);
            if (valeurDuParametre == 0.0d) {
                hydCalCController.lstCalcul.get(i).invoke(hydCalCController.moteur);
                valeurDuParametre = (double) hydCalCController.lstGet.get(i).invoke(hydCalCController.moteur);
                System.out.println("je calcul : " + hydCalCController.listeDesTextfield.get(i).getId() + " - résultat => " + valeurDuParametre);
            }
        }
        System.out.println("fini");
        System.out.println();
    }

    @FXML private void modifCouple(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMecaM).setText("");
    }
    @FXML private void modifCyl(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMecaM).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.VitDeRotationM).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.VitDeRotationM).setText("");
    }
    @FXML private void modifVitDeRot(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMecaM).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.cylm).setText("");
    }
    @FXML private void modifRendHydMeca(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMecaM).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.cylm).setText("");
    }
    @FXML private void modifPwrMeca(){
        hydCalCController.listeDesTextfield.get(HydCalCController.cylm).setText("");
    }
    @FXML private void modifRendement(){
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMecaM).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.ηvol).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.ηhm).setText("");
    }
    @FXML private void modifRendVol(){
        hydCalCController.listeDesTextfield.get(HydCalCController.VitDeRotationM).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.ηhm).setText("");
        hydCalCController.listeDesTextfield.get(HydCalCController.pwrMecaM).setText("");
    }

}