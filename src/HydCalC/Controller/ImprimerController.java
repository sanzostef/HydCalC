package HydCalC.Controller;

import HydCalC.HydCalCController;
import HydCalC.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ImprimerController{

    @FXML StackPane dspPagePreview;
    @FXML PagePreviewController dspPagePreviewController;
    @FXML ComboBox<Printer> cbImprimante;
    @FXML Label lblStatus;

    Printer selectedPrinter;

    public void main() {

        dspPagePreviewController.injection(hydCalCController);
        dspPagePreviewController.remplirLabel();
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
    private HydCalCController hydCalCController = new HydCalCController();
    public void injection(HydCalCController controller) { this.hydCalCController = controller; }

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
        double scaleX = pageLayout.getPrintableWidth() / dspPagePreview.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / dspPagePreview.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrlImprimer = Main.class.getResource("FXML/pagePreview.fxml");
        loader.setLocation(xmlUrlImprimer);
        try {
            StackPane node = loader.load();
            PagePreviewController pagePreviewController = loader.getController();
            pagePreviewController.remplirLabel();
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