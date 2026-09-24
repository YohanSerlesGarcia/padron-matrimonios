package pe.edu.upeu.padronmatrimonios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pe.edu.upeu.padronmatrimonios.model.Registro;
import pe.edu.upeu.padronmatrimonios.repository.RegistroRepositoryImpl;
import pe.edu.upeu.padronmatrimonios.service.MatrimonioService;
import pe.edu.upeu.padronmatrimonios.service.MatrimonioServiceImpl;

import java.time.LocalDate;

public class MainApp extends Application {

    private MatrimonioService matrimonioService;
    private ListView<String> listViewRegistros;

    @Override
    public void init() {
        RegistroRepositoryImpl repository = new RegistroRepositoryImpl();
        this.matrimonioService = new MatrimonioServiceImpl(repository);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Padrón de Matrimonios - UPeU");

        TextField txtActa = new TextField();
        txtActa.setPromptText("Número de Acta");

        TextField txtC1 = new TextField();
        txtC1.setPromptText("Contrayente 1");

        TextField txtC2 = new TextField();
        txtC2.setPromptText("Contrayente 2");

        DatePicker datePicker = new DatePicker(LocalDate.now());

        TextField txtLugar = new TextField();
        txtLugar.setPromptText("Lugar");

        Button btnRegistrar = new Button("Registrar Matrimonio");
        Button btnListar = new Button("Actualizar Lista");
        Button btnEliminar = new Button("eliminar Seleccionado");

        btnRegistrar.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        btnListar.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        btnEliminar.setStyle("-fx-background-color: gray; -fx-text-fill: white;");

        btnRegistrar.setPrefWidth(200);
        btnListar.setPrefWidth(200);
        btnEliminar.setPrefWidth(200);

        listViewRegistros = new ListView<>();

        btnRegistrar.setOnAction(e -> {
            try {
                String acta = txtActa.getText();
                String c1 = txtC1.getText();
                String c2 = txtC2.getText();
                LocalDate fecha = datePicker.getValue();
                String lugar = txtLugar.getText();

                if (acta.isEmpty() || c1.isEmpty() || c2.isEmpty() || lugar.isEmpty()) {
                    mostrarAlerta("Error", "Por favor completa todos los campos.");
                    return;
                }
                for (var m : matrimonioService.listarMatrimonios()){
                    if (m.getNumeroActa().equalsIgnoreCase(acta)){
                        mostrarAlerta("Error","El numero de acta ya esta registrado");
                        return;
                    }
                }

                matrimonioService.registrarMatrimonio(acta, c1, c2, fecha, lugar);
                actualizarListaVisual();

                txtActa.clear();
                txtC1.clear();
                txtC2.clear();
                txtLugar.clear();

            } catch (Exception ex) {
                mostrarAlerta("Error", "Ocurrió un error al registrar: " + ex.getMessage());
            }
        });

        btnListar.setOnAction(e -> actualizarListaVisual());
        btnEliminar.setOnAction(e ->{
            int index = listViewRegistros.getSelectionModel().getSelectedIndex();
            if(index >= 0){
                Registro r = matrimonioService.listarMatrimonios().get(index);
                matrimonioService.eliminarMatrimonio(r.getNumeroActa());
                actualizarListaVisual();
            } else {
                mostrarAlerta("Atencion", "selecciona un matrimonio de la lista para eliminar.");
            }
        });
        TextField txtBuscar = new TextField();
        txtBuscar.setPromptText("Buscar por nombre de un contrayente");
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            String filtro = txtBuscar.getText().trim().toLowerCase();
            StringBuilder sb = new StringBuilder();
            for (Registro r : matrimonioService.listarMatrimonios()) {
                if (r.descripcion().toLowerCase().contains(filtro)) {
                    sb.append("Acta: ").append(r.getNumeroActa()).append(" | ").append(r.descripcion())
                            .append(" | ").append(r.getFecha()).append(" | ").append(r.getLugar()).append("\n");
                }
            }
            new Alert(Alert.AlertType.INFORMATION, sb.length() == 0 ? "Sin resultados" : sb.toString()).showAndWait();
        });
        VBox formulario = new VBox(10,
                new Label("Nuevo Registro de Matrimonio"),
                txtActa, txtC1, txtC2, datePicker, txtLugar, btnRegistrar, btnListar, txtBuscar, btnBuscar
        );
        formulario.setPadding(new Insets(15));


        VBox listaPanel = new VBox(10, new Label("Lista de Matrimonios Registrados"), listViewRegistros, btnEliminar);
        listaPanel.setPadding(new Insets(15));

        HBox root = new HBox(20, formulario, listaPanel);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #b0bec5;");

        Scene scene = new Scene(root, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.show();

        actualizarListaVisual();
    }

    private void actualizarListaVisual() {
        listViewRegistros.getItems().clear();
        for (Registro r : matrimonioService.listarMatrimonios()) {
            listViewRegistros.getItems().add(
                    "Acta: " + r.getNumeroActa() + " | " + r.descripcion() + " | Fecha: " + r.getFecha() + " | Lugar: " + r.getLugar()
            );
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}