package com.mycompany.club.ix;

import com.mycompany.club.ix.model.Cuota;
import com.mycompany.club.ix.model.Socio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SocioFormViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private Label lblErrorNombre;

    @FXML
    private TextField txtDni;

    @FXML
    private Label lblErrorDni;

    @FXML
    private ComboBox<String> comboEstado;

    @FXML
    private TextField txtDisciplinas;

    @FXML
    private Label lblMensajeError;

    private static final String ESTILO_NORMAL = "-fx-background-radius: 4; -fx-border-color: #cccccc; -fx-border-radius: 4;";
    private static final String ESTILO_ERROR = "-fx-background-radius: 4; -fx-border-color: #dc3545; -fx-border-width: 1.5; -fx-border-radius: 4;";

    @FXML
    public void initialize() {
        comboEstado.setItems(FXCollections.observableArrayList("ACTIVO", "INACTIVO", "SUSPENDIDO"));
        comboEstado.getSelectionModel().select("ACTIVO");

        // Al escribir, remover el aviso rojo
        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.trim().isEmpty()) {
                txtNombre.setStyle(ESTILO_NORMAL);
                lblErrorNombre.setText("");
            }
        });

        txtDni.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.trim().isEmpty()) {
                txtDni.setStyle(ESTILO_NORMAL);
                lblErrorDni.setText("");
            }
        });
    }

    @FXML
    private void onGuardar() {
        String nombre = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
        String dni = txtDni.getText() != null ? txtDni.getText().trim() : "";
        String estado = comboEstado.getValue();
        String disciplinas = txtDisciplinas.getText() != null ? txtDisciplinas.getText().trim() : "";

        boolean hayError = false;

        // Validación de dato crítico 1: Nombre
        if (nombre.isEmpty()) {
            txtNombre.setStyle(ESTILO_ERROR);
            lblErrorNombre.setText("⚠ El nombre y apellido es un dato crítico obligatorio.");
            hayError = true;
        } else {
            txtNombre.setStyle(ESTILO_NORMAL);
            lblErrorNombre.setText("");
        }

        // Validación de dato crítico 2: DNI
        if (dni.isEmpty()) {
            txtDni.setStyle(ESTILO_ERROR);
            lblErrorDni.setText("⚠ El DNI es un dato crítico obligatorio.");
            hayError = true;
        } else {
            txtDni.setStyle(ESTILO_NORMAL);
            lblErrorDni.setText("");
        }

        // Si falta algún dato crítico, NO cierra el formulario y avisa en color rojo
        if (hayError) {
            lblMensajeError.setText("No se pudo guardar: faltan datos críticos requeridos (en rojo).");
            return;
        }

        // Si todo está correcto, crea la entidad y la agrega a Datos
        Socio nuevoSocio = new Socio(nombre, dni, estado, disciplinas.isEmpty() ? "Sin disciplina" : disciplinas);
        nuevoSocio.agregarCuota(new Cuota("Noviembre 2026", "PENDIENTE"));

        Datos.agregar(nuevoSocio);

        // Cierra el formulario
        cerrarVentana();
    }

    @FXML
    private void onCancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}
