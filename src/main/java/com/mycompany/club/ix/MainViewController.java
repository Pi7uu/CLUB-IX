package com.mycompany.club.ix;

import com.mycompany.club.ix.model.Socio;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class MainViewController {

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Socio> tableSocios;

    @FXML
    private TableColumn<Socio, String> colSocio;

    @FXML
    private TableColumn<Socio, String> colDni;

    @FXML
    private TableColumn<Socio, String> colEstado;

    @FXML
    private Label lblSetSocio;

    @FXML
    private Label lblSocioResumen;

    @FXML
    private Label lblDisciplinas;

    @FXML
    public void initialize() {
        colSocio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Carga los datos en memoria provistos por Datos.listar()
        tableSocios.setItems(Datos.listar());

        // Escuchar selección en la tabla
        tableSocios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, nuevoSocio) -> {
            actualizarResumen(nuevoSocio);
        });

        // Seleccionar primer socio por defecto si existe
        if (!Datos.listar().isEmpty()) {
            tableSocios.getSelectionModel().selectFirst();
        }
    }

    private void actualizarResumen(Socio socio) {
        if (socio != null) {
            lblSetSocio.setText("setSocio(" + socio.getNombre() + ")");
            lblSocioResumen.setText(socio.getNombre() + "  ·  " + socio.getEstado());
            lblDisciplinas.setText(socio.getDisciplinas());
        } else {
            lblSetSocio.setText("setSocio(null)");
            lblSocioResumen.setText("Sin selección");
            lblDisciplinas.setText("-");
        }
    }

    @FXML
    private void onBuscar() {
        String criterio = txtBuscar.getText();
        List<Socio> filtrados = Datos.buscar(criterio);
        tableSocios.setItems(FXCollections.observableArrayList(filtrados));

        if (!filtrados.isEmpty()) {
            tableSocios.getSelectionModel().selectFirst();
        } else {
            actualizarResumen(null);
        }
    }

    @FXML
    private void onLimpiarBusqueda() {
        txtBuscar.clear();
        tableSocios.setItems(Datos.listar());
        if (!Datos.listar().isEmpty()) {
            tableSocios.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void onNuevoSocio() {
        try {
            App.openModal("SocioFormView", "Alta de Nuevo Socio");

            // Aseguramos que la tabla muestre la lista completa y seleccione al recién agregado
            tableSocios.setItems(Datos.listar());
            if (!Datos.listar().isEmpty()) {
                tableSocios.getSelectionModel().selectLast();
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir el formulario de nuevo socio: " + e.getMessage());
        }
    }

    @FXML
    private void onAbrirFicha() {
        Socio seleccionado = tableSocios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Por favor seleccione un socio de la lista.");
            return;
        }

        try {
            FXMLLoader loader = App.openModal("SocioFichaView", "Ficha de Socio - " + seleccionado.getNombre());
            SocioFichaViewController controller = loader.getController();
            controller.setSocio(seleccionado);
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ficha del socio: " + e.getMessage());
        }
    }

    @FXML
    private void onReservar() {
        onNuevaReserva();
    }

    @FXML
    private void onNuevaReserva() {
        Socio seleccionado = tableSocios.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = App.openModal("ReservaFormView", "Nueva Reserva");
            ReservaFormViewController controller = loader.getController();
            if (seleccionado != null) {
                controller.setSocio(seleccionado);
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir el formulario de reserva: " + e.getMessage());
        }
    }

    @FXML
    private void onRegistrarPago() {
        Socio seleccionado = tableSocios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Seleccione un socio para registrar un pago.");
            return;
        }

        mostrarAlerta(Alert.AlertType.INFORMATION, "Pago Registrado",
                "Se registró el pago de cuota para " + seleccionado.getNombre() + ".\nEstado actualizado a AL DÍA.");
    }

    @FXML
    private void onSalir() {
        Platform.exit();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
