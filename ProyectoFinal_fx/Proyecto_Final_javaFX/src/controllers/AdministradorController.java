package controllers;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.AdministradorException;
import Exceptions.EmpleadoException;
import Model.Administrador;
import Model.Concesionario;
import Model.Empleado;
import application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdministradorController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<Empleado> tableViewEmpleado;
    @FXML
    private TableColumn<Empleado, String> columNombre;

    @FXML
    private TableColumn<Empleado, String> columApellidos;

    @FXML
    private TableColumn<Empleado, String> columCedula;
    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtApellidoEmpleado;

    @FXML
    private TextField txtCedulaEmpleado;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnAgregarEmpleado;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;

	private PrincipalController principioController;

	private LoginController loginController;

	private Concesionario concesionario;

	private Administrador administrador;

	private Stage stage;

	private Aplicacion aplicacion;

	private Empleado empleadoSeleccion;


	ObservableList<Empleado> listaEmpleados= FXCollections.observableArrayList();
	/**
	 * capturo las credenciales del admin para saber cual es, y asi poder acceder al metodo que obtiene al administrador, y asi en la tableview saldra la lista de
	 *  empleados que ese admin maneja
	 *
	 * @return
	 */
	private ObservableList<Empleado> getListaEmpleados() {
		String idAdminString= loginController.getTxtIdentificacionLogin().getText();
		String contrasenia= loginController.getTxtContraseniaLogin().getText();

		administrador= aplicacion.obtenerAdministrador(idAdminString, contrasenia);

		listaEmpleados.addAll(administrador.getListaEmpleados());
		return listaEmpleados;
	}


	private boolean validarDatos(String nombre, String apellido, String cedula) {
		String notificacion = "";

		if (nombre == null || nombre.equals(""))
			notificacion += "Nombre(s) invalido\n";

		if (apellido == null || apellido.equals(""))
			notificacion += "Apellido(s) inválido\n";

		if (cedula == null || cedula.equals("")) {
			notificacion += "Número de cedula invalido\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Empleado no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}


	@FXML
    void actualizarEmpleado(ActionEvent event) throws AdministradorException, EmpleadoException {
		String nombre= txtNombreEmpleado.getText();
		String apellido= txtApellidoEmpleado.getText();
		String cedula= txtCedulaEmpleado.getText();

		String idAdmin= loginController.getTxtIdentificacionLogin().getText();

		if (empleadoSeleccion!=null) {
			if (validarDatos(nombre, apellido, cedula)) {
				aplicacion.actualizarEmpleado(idAdmin, nombre, apellido, cedula);
				//Se actualizan los valores que se muestran en la interfaz
				empleadoSeleccion.setNombre(nombre);
				empleadoSeleccion.setApellido(apellido);
				empleadoSeleccion.setIdentificacion(cedula);

				tableViewEmpleado.refresh();//Refresco la tabla
				mostrarMensaje("Actualizacion empleado", "Empleado actualizado", "Se ha actualizado correctamente el empleado", AlertType.INFORMATION);

			}
		}else {
			mostrarMensaje("Empleado seleccion", "Empleado seleccion", "No ha seleccioando ningun empleado para actualizar", AlertType.WARNING);
		}


    }

    @FXML
    void agregarEmpleado(ActionEvent event) throws EmpleadoException, AdministradorException {
		//Datos del empleado neuvo
    	String nombre = txtNombreEmpleado.getText();
    	String apellido= txtApellidoEmpleado.getText();
    	String identificacion= txtCedulaEmpleado.getText();
    	//Credenciales del admin
    	String identificacionAdmin= loginController.getTxtIdentificacionLogin().getText();
    	String contraseniaAdmin= loginController.getTxtContraseniaLogin().getText();

    	if (validarDatos(nombre, apellido, identificacion)) {
    		crearEmpleado(identificacionAdmin,contraseniaAdmin,nombre, apellido,identificacion);

    		txtNombreEmpleado.setText("");
    		txtApellidoEmpleado.setText("");
    		txtCedulaEmpleado.setText("");
		}
    }

    private void crearEmpleado(String identificacionAdmin,String contraseniaAdmin,String nombre, String apellido, String identificacion) throws EmpleadoException, AdministradorException {
		if (aplicacion.crearEmpleado(identificacionAdmin, contraseniaAdmin,nombre, apellido, identificacion)) {
			tableViewEmpleado.getItems().clear();
			tableViewEmpleado.setItems(getListaEmpleados());
			mostrarMensaje("Notificacion empleado", "Empleado creado", "El empleado ha sido creado con exito", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificacion empleado", "Empleado creado", "El empleado no se ha podido crear", AlertType.INFORMATION);
		}
    }

    @FXML
    void limpiar(ActionEvent event) {
    	txtNombreEmpleado.setText("");
    	txtApellidoEmpleado.setText("");
    	txtCedulaEmpleado.setText("");
    	empleadoSeleccion=null;
    }
    /**
     * Se obtiene la identificacion del admin desde el login
     * Se verifica que el elemento seleccionado de la tabla no sea nulo
     * Sale un mensaje para verificar la decision de eliminar
     * Se llama al metodo que hay en aplicacion y si este retorna true, entonces elimina al empleado y lo quita de la tabla
     *
     * @param event
     * @throws AdministradorException
     * @throws EmpleadoException
     */
    @FXML
    void eliminarEmpleado(ActionEvent event) throws AdministradorException, EmpleadoException {
    	String identificacionAdmin= loginController.getTxtIdentificacionLogin().getText();
    	if (empleadoSeleccion!=null) {
    		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este empleado?");
    		if (confirmacion==0) {
    			if (aplicacion.eliminarEmpleado(identificacionAdmin, empleadoSeleccion)) {
    				listaEmpleados.remove(empleadoSeleccion);
    				mostrarMensaje("Eliminacion empleado", "Empleado eliminado", "Se ha eliminado correctamente el empleado", AlertType.INFORMATION);
				}else {
					mostrarMensaje("Eliminacion empleado", "Empleado no eliminado", "No se ha podido eliminar el empleado", AlertType.INFORMATION);
				}
			}
		}else {
			mostrarMensaje("Empleado seleccion", "Empleado seleccion", "No ha seleccionado ningun empleado para eliminar", AlertType.INFORMATION);
		}
    }

    @FXML
    void showVentanaPrincipal(ActionEvent event) {
    	this.stage.close();
     	loginController.getTxtContraseniaLogin().setText("");
    	loginController.getTxtIdentificacionLogin().setText("");
    	loginController.show();
    }


    @FXML
    void initialize() {


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columCedula.setCellValueFactory(new PropertyValueFactory<>("identificacion"));

		tableViewEmpleado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				empleadoSeleccion= newSelection;
				//mostrarInformacionEmpleado();
			}
		});


	}

	public void init(Stage stage, LoginController loginController) {
		this.loginController = loginController;
		this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;

	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}

}
