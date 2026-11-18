package com.mycompany.club.ix;

import com.mycompany.club.ix.model.Socio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReservaFormViewController {

    @FXML
    private Label lblSocioInfo;

    @FXML
    private TextField txtEspacio;

    @FXML
    private Label lblHorario;

    private Socio socio;

    public void setSocio(Socio socio) {
        this.socio = socio;
        if (socio != null) {
            lblSocioInfo.setText("Socio asignado: " + socio.getNombre());
        }
    }

    @FXML
    private void onConfirmar() {
        String espacio = (txtEspacio != null && txtEspacio.getText() != null) ? txtEspacio.getText().trim() : "Espacio";
        String nombreSocio = (socio != null) ? socio.getNombre() : "General";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Exitosa");
        alert.setHeaderText("Reserva confirmada");
        alert.setContentText("Se reservó con éxito: " + espacio + "\nPara: " + nombreSocio);
        alert.showAndWait();

        cerrarVentana();
    }

    @FXML
    private void onDeshacer() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtEspacio.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}
