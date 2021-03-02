package HydCalC.Controller;

import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class PagePreviewController {

    @FXML StackPane feuille;
    @FXML Label lblRaisonSociale, lblAdresse, lblTel, lblContact, lblMail, lblTelContact;
    @FXML Label lblDFond, lblDTige, lblCourse, lblSFond, lblSAnn, lblR, lblIncident;
    @FXML Label lblVFond, lblVTige, lblFSortie, lblFRentree, lblFDiff, lblVitSortie;
    @FXML Label lblVitRentree, lblVitDiff, lblTpsSortie, lblTpsRentree, lblTpsDiff, lblDebit;
    @FXML Label lblVitDeRot, lblPwrHyd, lblPwrMeca, lblRendement, lblV0, lblV1;
    @FXML Label lblPression, lblRInv, lblCyl, lblP1, lblP2, lblDeltaV, lblClient;
    @FXML Label lblN, lblDiamSing, lblMasseVol, lblDeltaPSing, lblV2, lblP0;
    @FXML Label lblVisco, lblDeltaPLin, lblCouple, lblCylMot, lblVitRotMot, lblDiamLin;
    @FXML Label lblL, lblmasseVolL, lblPwrMecaMot, lblRendVol, lblRendHydMeca, lblRendTot, lblDiamGicleur, lblDeltaPGicleur;
    @FXML Label lblDescription, lblTitreDescription;

    public void remplirLabelClient() {
        Label[] tabLabel = new Label[50];
        tabLabel[0]= lblDFond; tabLabel[1]=lblDTige; tabLabel[2]=lblCourse; tabLabel[3]=lblSFond; tabLabel[4]=lblSAnn; tabLabel[5]=lblR; tabLabel[6]=lblRInv;
        tabLabel[7]=lblVFond;tabLabel[8]=lblVTige; tabLabel[9]=lblFSortie; tabLabel[10]=lblFRentree; tabLabel[11]=lblFDiff; tabLabel[12]=lblVitSortie; tabLabel[13]=lblVitRentree;
        tabLabel[14]=lblVitDiff; tabLabel[15]=lblTpsSortie; tabLabel[16]=lblTpsRentree; tabLabel[17]=lblTpsDiff; tabLabel[18]=lblDebit; tabLabel[19]=lblPression; tabLabel[20]=lblCyl;
        tabLabel[21]=lblVitDeRot; tabLabel[22]=lblPwrHyd; tabLabel[23]=lblPwrMeca; tabLabel[24]=lblRendement; tabLabel[25]=lblV0; tabLabel[26]=lblV1; tabLabel[27]=lblV2; tabLabel[28]=lblP0;
        tabLabel[29]=lblP1; tabLabel[30]=lblP2; tabLabel[31]=lblDeltaV; tabLabel[32]=lblN; tabLabel[33]=lblDiamSing; tabLabel[34]=lblMasseVol; tabLabel[35]=lblDeltaPSing; tabLabel[36]=lblDiamLin;
        tabLabel[37]=lblmasseVolL; tabLabel[38]=lblL; tabLabel[39]=lblVisco; tabLabel[40]=lblDeltaPLin; tabLabel[41]=lblCouple; tabLabel[42]=lblCylMot; tabLabel[43]=lblVitRotMot;
        tabLabel[44]=lblPwrMecaMot; tabLabel[45]=lblRendVol; tabLabel[46]=lblRendHydMeca; tabLabel[47]=lblRendTot; tabLabel[48]=lblDiamGicleur; tabLabel[49]=lblDeltaPGicleur;

        for (int i = 0; i< hydCalCController.listeDesTextfield.size(); i++){
            Label lbltmp = new Label();
            lbltmp.setText(hydCalCController.listeDesTextfield.get(i).getText());
            tabLabel[i].setText(tabLabel[i].getText().replace("__", hydCalCController.listeDesTextfield.get(i).getText()));
        }
        lblIncident.setText(lblIncident.getText().replace("__", String.valueOf(hydCalCController.deltaPSinguliere.getCoeff())));
        lblRaisonSociale.setText(lblRaisonSociale.getText() + " " + clientController.client.getRaisonSociale());
        lblAdresse.setText(lblAdresse.getText() + " " + clientController.client.getAdresse());
        lblTel.setText(lblTel.getText() + " " + clientController.client.getTelephone());
        lblContact.setText(lblContact.getText() + " " + clientController.client.getContact());
        lblMail.setText(lblMail.getText() + " " + clientController.client.getMail());
        lblTelContact.setText(lblTelContact.getText() + " " + clientController.client.getTelephoneContact());
        lblDescription.setVisible(false);
        lblTitreDescription.setVisible(false);
    }

    public void remplirLabelOutil(){
        lblClient.setText("Outil: " + outilController.outil.getOutil());
        lblRaisonSociale.setText("Fonction: " + outilController.outil.getFonction());
        lblDescription.setText(outilController.outil.getDescription());
        lblAdresse.setVisible(false);
        lblContact.setVisible(false);
        lblMail.setVisible(false);
        lblTel.setVisible(false);
        lblTelContact.setVisible(false);
    }

    private HydCalCController hydCalCController = new HydCalCController();
    private ClientController clientController = new ClientController();
    private OutilController outilController = new OutilController();
    public void injection(HydCalCController controller) { hydCalCController = controller; }
    public void injection(ClientController controller) { clientController = controller; }
    public void injection(OutilController controller) { outilController = controller; }
}
