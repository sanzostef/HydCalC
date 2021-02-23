package HydCalC.Controller;

import HydCalC.HydCalCController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ImprimerController{

    @FXML Label lblDFond; @FXML Label lblDTige; @FXML Label lblCourse; @FXML Label lblSFond; @FXML Label lblSAnn; @FXML Label lblR; @FXML Label lblIncident;
    @FXML Label lblVFond; @FXML Label lblVTige; @FXML Label lblFSortie; @FXML Label lblFRentree; @FXML Label lblFDiff; @FXML Label lblVitSortie;
    @FXML Label lblVitRentree; @FXML Label lblVitDiff; @FXML Label lblTpsSortie; @FXML Label lblTpsRentree; @FXML Label lblTpsDiff; @FXML Label lblDebit;
    @FXML Label lblVitDeRot; @FXML Label lblPwrHyd; @FXML Label lblPwrMeca; @FXML Label lblRendement; @FXML Label lblV0; @FXML Label lblV1;
    @FXML Label lblPression; @FXML Label lblRInv; @FXML Label lblCyl; @FXML Label lblP1; @FXML Label lblP2; @FXML Label lblDeltaV;
    @FXML Label lblN; @FXML Label lblDiamSing; @FXML Label lblMasseVol; @FXML Label lblDeltaPSing; @FXML Label lblV2; @FXML Label lblP0;
    @FXML Label lblVisco; @FXML Label lblDeltaPLin; @FXML Label lblCouple; @FXML Label lblCylMot; @FXML Label lblVitRotMot; @FXML Label lblDiamLin;
    @FXML Label lblL; @FXML Label lblmasseVolL; @FXML Label lblPwrMecaMot; @FXML Label lblRendVol; @FXML Label lblRendHydMeca; @FXML Label lblRendTot;

    public void main() {
        Label[] tabLabel = new Label[48];
        tabLabel[0]= lblDFond; tabLabel[1]=lblDTige; tabLabel[2]=lblCourse; tabLabel[3]=lblSFond; tabLabel[4]=lblSAnn; tabLabel[5]=lblR; tabLabel[6]=lblRInv;
        tabLabel[7]=lblVFond;tabLabel[8]=lblVTige; tabLabel[9]=lblFSortie; tabLabel[10]=lblFRentree; tabLabel[11]=lblFDiff; tabLabel[12]=lblVitSortie; tabLabel[13]=lblVitRentree;
        tabLabel[14]=lblVitDiff; tabLabel[15]=lblTpsSortie; tabLabel[16]=lblTpsRentree; tabLabel[17]=lblTpsDiff; tabLabel[18]=lblDebit; tabLabel[19]=lblPression; tabLabel[20]=lblCyl;
        tabLabel[21]=lblVitDeRot; tabLabel[22]=lblPwrHyd; tabLabel[23]=lblPwrMeca; tabLabel[24]=lblRendement; tabLabel[25]=lblV0; tabLabel[26]=lblV1; tabLabel[27]=lblV2; tabLabel[28]=lblP0;
        tabLabel[29]=lblP1; tabLabel[30]=lblP2; tabLabel[31]=lblDeltaV; tabLabel[32]=lblN; tabLabel[33]=lblDiamSing; tabLabel[34]=lblMasseVol; tabLabel[35]=lblDeltaPSing; tabLabel[36]=lblDiamLin;
        tabLabel[37]=lblmasseVolL; tabLabel[38]=lblL; tabLabel[39]=lblVisco; tabLabel[40]=lblDeltaPLin; tabLabel[41]=lblCouple; tabLabel[42]=lblCylMot; tabLabel[43]=lblVitRotMot;
        tabLabel[44]=lblPwrMecaMot; tabLabel[45]=lblRendVol; tabLabel[46]=lblRendHydMeca; tabLabel[47]=lblRendTot;

        for (int i = 0; i< hydCalCController.listeDesTextfield.size(); i++){
            Label lbltmp = new Label();
            lbltmp.setText(hydCalCController.listeDesTextfield.get(i).getText());
            tabLabel[i].setText(tabLabel[i].getText().replace("__", hydCalCController.listeDesTextfield.get(i).getText()));
        }
        lblIncident.setText(lblIncident.getText().replace("__", String.valueOf(hydCalCController.deltaPSinguliere.getCoeff())));
    }
    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }
}