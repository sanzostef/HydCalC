package HydCalC.Controller;

import HydCalC.HydCalCController;
import HydCalC.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ImprimerController{

    @FXML StackPane dspPagePreview;
    @FXML PagePreviewController dspPagePreviewController;
    @FXML ComboBox<Printer> cbImprimante;
    @FXML Label lblStatus;

    String controllerAppelant;
    Printer selectedPrinter;
    public void controllerAppelant(String controller){   controllerAppelant = controller; }

    public void main() {
        dspPagePreviewController.injection(hydCalCController);
        if (controllerAppelant == "client") {
            dspPagePreviewController.injection(clientController);
            dspPagePreviewController.remplirLabelClient();
        }
        if (controllerAppelant == "outil") {
            dspPagePreviewController.injection(OutilController);
            dspPagePreviewController.remplirLabelOutil();
        }
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        ArrayList<Printer> printerlist = new ArrayList<>(printers);
        cbImprimante.setPromptText("Imprimante");
        cbImprimante.setItems(FXCollections.observableArrayList(printerlist));
        Printer defaultprinter = Printer.getDefaultPrinter();
        if (defaultprinter != null) {
            cbImprimante.getSelectionModel().select(defaultprinter);
            selectedPrinter = defaultprinter;
        }
    }
    private ClientController clientController = new ClientController();
    private HydCalCController hydCalCController = new HydCalCController();
    private OutilController OutilController = new OutilController();
    //private ArrayList<Controller> lstController = new ArrayList<Controller>;
    public void injection(HydCalCController controller) { hydCalCController = controller; }
    public void injection(ClientController controller) { clientController = controller; }
    public void injection(OutilController controller) { OutilController = controller; }

    //]  public void injection(ClientController controller) { clientController = controller; }

    @FXML
    private void choixImprimante(){
        selectedPrinter = cbImprimante.getValue();
    }

    @FXML
    private void annuler(){
        // get a handle to the stage
        Stage stage = (Stage) cbImprimante.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void imprimer() {
        // Create a printer job for the printer
        lblStatus.setText("Impression: En cour...");
        PageLayout pageLayout = selectedPrinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterJob job = PrinterJob.createPrinterJob(selectedPrinter);
      //  lblStatus.textProperty().bind(job.jobStatusProperty().asString());
        double scaleX = pageLayout.getPrintableWidth() / dspPagePreview.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / dspPagePreview.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrlImprimer = Main.class.getResource("FXML/pagePreview.fxml");
        loader.setLocation(xmlUrlImprimer);
        try {
            StackPane node = loader.load();
            PagePreviewController pagePreviewController = loader.getController();
            dspPagePreviewController.injection(hydCalCController);
            if (controllerAppelant == "client") {
                dspPagePreviewController.injection(clientController);
                dspPagePreviewController.remplirLabelClient();
            }
            if (controllerAppelant == "outil") {
                dspPagePreviewController.injection(OutilController);
                dspPagePreviewController.remplirLabelOutil();
            }
            node.getTransforms().add(scale);
            if (job != null) {
                //job.showPageSetupDialog(cbImprimante.getScene().getWindow());
                // Print the node
                boolean printed = job.printPage(pageLayout, node);
                // node.getTransforms().remove(scale);
                if (printed) {
                    // End the printer job
                    job.endJob();
                    lblStatus.setText("Impression: Terminée.");
                } else {
                    lblStatus.setText("Impression: Annulée/Echec.");
                }
            } else { lblStatus.setText("Impression: Impossible de creer le travail d'impression."); }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}