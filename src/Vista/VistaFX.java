package Vista;

import Modelo.GestorArchivo;
import Modelo.Instrumento;
import Modelo.SistemaAPA;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/*
Esta clase especificamente modela la ventana grafica para el sistema de
salud mental e integral, se tomo en cuenta la vista en consola
en la tabla se podra mostrar los regsitros hecho ademas de las consultas
*/

public class VistaFX extends Application {

    private SistemaAPA modelo;
    private GestorArchivo gestor;

    //atributos del formulario de registro
    private TextField txtNombre;
    private TextField txtAutor;
    private ComboBox<String> cbForma;
    private ComboBox<String> cbCondicion;
    private CheckBox chkValidez;
    private TextField txtFecha;
    private Button btnRegistrar;

    //seccion de consulta
    private ComboBox<String> cbConsulta;
    private TextField txtFiltro;
    private Button btnConsultar;

    //tabla donde se mostrartan los registros hechps
    private TableView<FilaTabla> tabla;

    //seccion para eliminar
    private TextField txtClave;
    private Button btnEliminar;

    //mensajes para el usuario
    private Label lblEstado;

    @Override
    public void start(Stage stage) {
        modelo = new SistemaAPA();
        gestor = new GestorArchivo();
        cargarArchivo();

        crearFormulario();
        crearConsulta();
        crearTabla();
        crearEliminar();

        lblEstado = new Label("Listo.");

        HBox filaConsulta = new HBox(8, cbConsulta, txtFiltro, btnConsultar);
        HBox filaEliminar = new HBox(8, new Label("Eliminar por clave:"), txtClave, btnEliminar);

        VBox raiz = new VBox(10);
        raiz.setPadding(new Insets(12));
        raiz.getChildren().add(crearGridFormulario());
        raiz.getChildren().add(btnRegistrar);
        raiz.getChildren().add(new Separator());
        raiz.getChildren().add(filaConsulta);
        raiz.getChildren().add(tabla);
        raiz.getChildren().add(filaEliminar);
        raiz.getChildren().add(lblEstado);

        mostrarTodos();

        Scene escena = new Scene(raiz, 800, 560);
        stage.setTitle("Sistema APA");
        stage.setScene(escena);
        stage.setOnCloseRequest(e -> guardarArchivo());
        stage.show();
    }

    //desarrollo

    private void crearFormulario() {
        txtNombre = new TextField();
        txtAutor = new TextField();
        //forma
        cbForma = new ComboBox<>();
        cbForma.getItems().addAll("escala", "cuestionario", "test");
        cbForma.setValue("test");
        //condicion
        cbCondicion = new ComboBox<>();
        cbCondicion.getItems().addAll("Estrés", "Ansiedad", "Ambos");
        cbCondicion.setValue("Estrés");
        //tiene validez
        chkValidez = new CheckBox("Marca la casilla si tiene validez y confiabilidad");
        //fecha con formato (esta seccion se modifico gracias a los comentarios en clase)
        txtFecha = new TextField();
        txtFecha.setPromptText("dd/mm/aaaa");
        //boton para guardar el registro
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setOnAction(e -> registrar());
    }

    //metrdo privado para hacer la tabla con la informacion del registro
    // aqui se mostraran los registros hechos por el usuario y consultas
    private GridPane crearGridFormulario() {
        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(6);
        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Autor:"), 0, 1);
        grid.add(txtAutor, 1, 1);
        grid.add(new Label("Forma:"), 0, 2);
        grid.add(cbForma, 1, 2);
        grid.add(new Label("Condición:"), 0, 3);
        grid.add(cbCondicion, 1, 3);
        grid.add(new Label("Validez:"), 0, 4);
        grid.add(chkValidez, 1, 4);
        grid.add(new Label("Fecha evaluación:"), 0, 5);
        grid.add(txtFecha, 1, 5);
        return grid;
    }
    /*
    Seccion de consulta del sistema
    las opciones para realizar consultas son estas:
    -"clave"
    -"autor"
    -"Por autor"
    -"Por forma"
    -"Por condición"
    -"Con validez"
    -"Sin validez"
    -"Por condición con validez"
     */
    private void crearConsulta() {
        cbConsulta = new ComboBox<>();
        cbConsulta.getItems().addAll(
                "Todos por clave",
                "Todos por autor",
                "Por autor",
                "Por forma",
                "Por condición",
                "Con validez",
                "Sin validez",
                "Por condición con validez");
        cbConsulta.setValue("Todos por clave");

        txtFiltro = new TextField();
        //txtFiltro.setPromptText("autor / forma / condición");

        btnConsultar = new Button("Consultar");
        btnConsultar.setOnAction(e -> consultar());
    }

    private void crearTabla() {
        tabla = new TableView<>();
        //columnas de la tabla
        TableColumn<FilaTabla, Integer> colClave = new TableColumn<>("Clave");
        colClave.setCellValueFactory(new PropertyValueFactory<>("clave"));

        TableColumn<FilaTabla, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<FilaTabla, String> colAutor = new TableColumn<>("Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        TableColumn<FilaTabla, String> colForma = new TableColumn<>("Forma");
        colForma.setCellValueFactory(new PropertyValueFactory<>("forma"));

        TableColumn<FilaTabla, String> colCondicion = new TableColumn<>("Condición");
        colCondicion.setCellValueFactory(new PropertyValueFactory<>("condicion"));

        TableColumn<FilaTabla, String> colValidez = new TableColumn<>("Validez");
        colValidez.setCellValueFactory(new PropertyValueFactory<>("validez"));

        TableColumn<FilaTabla, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tabla.getColumns().add(colClave);
        tabla.getColumns().add(colNombre);
        tabla.getColumns().add(colAutor);
        tabla.getColumns().add(colForma);
        tabla.getColumns().add(colCondicion);
        tabla.getColumns().add(colValidez);
        tabla.getColumns().add(colFecha);

        //se reparte el ancho entre las columnas
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //texto que aparecera cunado la tabla no tiene filas
        tabla.setPlaceholder(new Label("Sin instrumentos"));
        //medida de los pixeles
        tabla.setFixedCellSize(26);
    }
    /*
    seccion de eliminar, aqui la única opción es eliminar por clave
    como lo mencionan las instrucciones de la tarea
     */
    private void crearEliminar() {
        txtClave = new TextField();
        txtClave.setPromptText("clave");
        txtClave.setPrefWidth(70);

        btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> eliminar());
    }

    //seccion de los botones

    private void registrar() {
        String nombre = txtNombre.getText().trim();
        String autor = txtAutor.getText().trim();
        String forma = cbForma.getValue();
        int condicion = numeroCondicion(cbCondicion.getValue());
        boolean validez = chkValidez.isSelected();
        String fecha = txtFecha.getText().trim();

        if (nombre.isEmpty() || autor.isEmpty()) {
            lblEstado.setText("Nombre y autor son obligatorios.");
            return;
        }
        if (!fecha.isEmpty() && !fechaValida(fecha)) {
            lblEstado.setText("Fecha inválida. Usa dd/mm/aaaa.");
            return;
        }

        modelo.registrarInstrumento(nombre, autor, forma, condicion, validez, fecha);
        guardarArchivo();

        txtNombre.clear();
        txtAutor.clear();
        txtFecha.clear();
        chkValidez.setSelected(false);

        mostrarTodos();
        lblEstado.setText("Instrumento registrado.");
    }
    // boton de eliminar
    private void eliminar() {
        int clave;
        try {
            clave = Integer.parseInt(txtClave.getText().trim());
        } catch (NumberFormatException e) {
            lblEstado.setText("La clave debe ser un número.");
            return;
        }

        // Verificamos si existe usando un lambda (Stream)
        boolean existe = modelo.mostrarTodos().stream().anyMatch(ins -> ins.getClave() == clave);

        if (existe) {
            modelo.eliminarInstrumento(clave);
            guardarArchivo();
            mostrarTodos();
            lblEstado.setText("Instrumento eliminado");
        } else {
            lblEstado.setText("No existe esa clave");
        }
        txtClave.clear();
    }
    // boton de consulta
    private void consultar() {
        String opcion = cbConsulta.getValue();
        String filtro = txtFiltro.getText().trim();
        int condicionFiltro = numeroCondicion(filtro);

        ArrayList<FilaTabla> resultado = new ArrayList<>();

        // Recorremos el ArrayList directamente en lugar del HashMap
        for (Instrumento ins : modelo.mostrarTodos()) {
            int clave = ins.getClave();
            boolean cumple = false;

            if (opcion.equals("Todos por clave") || opcion.equals("Todos por autor")) {
                cumple = true;
            } else if (opcion.equals("Por autor")) {
                cumple = ins.getAutor().equalsIgnoreCase(filtro);
            } else if (opcion.equals("Por forma")) {
                cumple = ins.getTipoInstrumento().equalsIgnoreCase(filtro);
            } else if (opcion.equals("Por condición")) {
                cumple = ins.getCondicion() == condicionFiltro;
            } else if (opcion.equals("Con validez")) {
                cumple = ins.isValidezConfiabilidad();
            } else if (opcion.equals("Sin validez")) {
                cumple = !ins.isValidezConfiabilidad();
            } else if (opcion.equals("Por condición con validez")) {
                cumple = ins.getCondicion() == condicionFiltro && ins.isValidezConfiabilidad();
            }

            if (cumple) {
                resultado.add(new FilaTabla(clave, ins));
            }
        }
        // ordenar
        if (opcion.equals("Todos por autor")) {
            Collections.sort(resultado, new Comparator<FilaTabla>() {
                @Override
                public int compare(FilaTabla a, FilaTabla b) {
                    return a.getAutor().compareToIgnoreCase(b.getAutor());
                }
            });
        } else {
            Collections.sort(resultado, new Comparator<FilaTabla>() {
                @Override
                public int compare(FilaTabla a, FilaTabla b) {
                    return Integer.compare(a.getClave(), b.getClave());
                }
            });
        }

        llenarTabla(resultado);
        lblEstado.setText(resultado.size() + " resultado(s).");
    }

    //metodos complementarios para la GUI
    private void mostrarTodos() {
        cbConsulta.setValue("Todos por clave");
        txtFiltro.clear();
        consultar();
    }

    private void llenarTabla(ArrayList<FilaTabla> filas) {
        ObservableList<FilaTabla> datos = FXCollections.observableArrayList(filas);
        tabla.setItems(datos);
        //La tabla incrementa la cantidad de filas
        int numFilas = Math.max(1, filas.size());
        tabla.setPrefHeight(30 + 26 * numFilas);
        tabla.setMinHeight(30 + 26 * numFilas);
        tabla.setMaxHeight(30 + 26 * numFilas);
    }

    //convierte el nombre de la condicipn a número
    private int numeroCondicion(String texto) {
        if (texto.equalsIgnoreCase("Estrés") || texto.equalsIgnoreCase("estres")) {
            return 1;
        } else if (texto.equalsIgnoreCase("Ansiedad")) {
            return 2;
        } else if (texto.equalsIgnoreCase("Ambos")) {
            return 3;
        }
        return 0;
    }

    private boolean fechaValida(String fecha) {
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        String[] partes = fecha.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        return dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12;
    }

    private void cargarArchivo() {
        try {
            modelo.cargarCSVDirectorio(gestor.leerArchivo());
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    private void guardarArchivo() {
        try {
            // Ahora le mandamos el ArrayList en lugar del Map
            gestor.guardarArchivo(modelo.mostrarTodos());
        } catch (IOException e) {
            lblEstado.setText("No se pudo guardar: " + e.getMessage());
        }
    }
    public static class FilaTabla {
        private final int clave;
        private final String nombre;
        private final String autor;
        private final String forma;
        private final String condicion;
        private final String validez;
        private final String fecha;

        public FilaTabla(int clave, Instrumento ins) {
            this.clave = clave;
            this.nombre = ins.getNombre();
            this.autor = ins.getAutor();
            this.forma = ins.getTipoInstrumento();
            if (ins.getCondicion() == 1) {
                this.condicion = "Estrés";
            } else if (ins.getCondicion() == 2) {
                this.condicion = "Ansiedad";
            } else {
                this.condicion = "Ambos";
            }
            this.validez = ins.isValidezConfiabilidad() ? "Sí" : "No";
            this.fecha = ins.getFecha();
        }

        public int getClave() { return clave; }
        public String getNombre() { return nombre; }
        public String getAutor() { return autor; }
        public String getForma() { return forma; }
        public String getCondicion() { return condicion; }
        public String getValidez() { return validez; }
        public String getFecha() { return fecha; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}