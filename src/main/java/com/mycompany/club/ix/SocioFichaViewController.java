package com.mycompany.club.ix;

import com.mycompany.club.ix.model.Cuota;
import com.mycompany.club.ix.model.Socio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class SocioFichaViewController {

    @FXML
    private Label lblRecibeSocio;

    @FXML
    private TextField txtEstado;

    @FXML
    private TableView<Cuota> tableCuotas;

    @FXML
    private TableColumn<Cuota, String> colPeriodo;

    @FXML
    private TableColumn<Cuota, String> colEstado;

    private Socio socio;

    @FXML
    public void initialize() {
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
        if (socio != null) {
            lblRecibeSocio.setText("Recibe: Socio " + socio.getNombre() + " (DNI " + socio.getDni() + ")");
            txtEstado.setText("Estado " + socio.getEstado());

            if (socio.getCuotas() != null) {
                tableCuotas.setItems(FXCollections.observableArrayList(socio.getCuotas()));
            }
        }
    }

    @FXML
    private void onNuevaReserva() {
        try {
            FXMLLoader loader = App.openModal("ReservaFormView", "Nueva Reserva");
            ReservaFormViewController controller = loader.getController();
            if (socio != null) {
                controller.setSocio(socio);
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir el formulario de reserva: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
