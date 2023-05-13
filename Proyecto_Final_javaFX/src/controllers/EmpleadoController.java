package controllers;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.sun.javafx.css.CssError.StringParsingError;

import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Model.Cliente;
import Model.Concesionario;
import Model.Empleado;
import Model.TipoCombustible;
import Model.TipoEstado;
import Model.TipoTransmicion;
import Model.TipoVehiculo;
import application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EmpleadoController implements Initializable {

	Singleton singleton = Singleton.getInstance();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableColumn<Cliente, String> columNombreCliente;

    @FXML
    private TableColumn<Cliente, String> columApellidosCliente;

    @FXML
    private TableColumn<Cliente, String> columCedulaCliente;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtApellidoCliente;
    @FXML
    private TextField txtCedulaCliente;
    @FXML
    private TextField txtFechaNacimiento;

    @FXML
    private TextField txtModeloVehiculo;
    @FXML
    private TextField txtCaballosFuerza;

    @FXML
    private TextField txtSalidasEmergencia;

    @FXML
    private TextField txt100km;

    @FXML
    private TextField txtMarcaVehiculo;

    @FXML
    private TextField txtCap_Maletero;
    @FXML
    private TextField txtTipoCamion;
    @FXML
    private TextField txtNumPasajeros;
    @FXML
    private TextField txtVelMaxima;

    @FXML
    private TextField txtNum_bolsas;

    @FXML
    private TextField txtCapacidadCarga;

    @FXML
    private TextField txtNumPuertas;

    @FXML
    private TextField txtNumEjes;

    @FXML
    private TextField txtCilindraje;

    @FXML
    private TextField txtCant_CambioVehiculo;

    @FXML
    private TextField txtAutonomiaCarga;
    @FXML
    private TextField txtPromedioCarga;

    @FXML
    private CheckBox checkAsistenteCarril;
    @FXML
    private CheckBox checkSensColision;

    @FXML
    private CheckBox checkCamReversa;

    @FXML
    private CheckBox checkABS;

    @FXML
    private CheckBox checkCuatroPorCuatro;

    @FXML
    private CheckBox checkSensTrafico;

    @FXML
    private CheckBox checkAireAcondicionado;
    @FXML
    private CheckBox checkVelCrucer;

    @FXML
    private CheckBox chekHibridoLigero;

    @FXML
    private CheckBox checkEnchufable;

    @FXML
    private ComboBox<TipoTransmicion> comboBoxTipoTransmicion;
    @FXML
    private ComboBox<TipoVehiculo> comboBoxTipoVehiculo;
    @FXML
    private ComboBox<TipoEstado> comboBoxEstado;

    @FXML
    private ComboBox<TipoCombustible> comboBoxCombustible;

	private PrincipalController principioController;
	private LoginEmpleadoController loginEmpleadoController;

	private Concesionario concesionario;

	private Stage stage;

	private Aplicacion aplicacion;

	private Cliente clienteSeleccion;
	ObservableList<Cliente> listaClientes= FXCollections.observableArrayList();

	private ObservableList<Cliente> getListaClientes(){
		String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
		Empleado empleadoAux= singleton.obtenerEmpleado(idEmpleado);
		listaClientes.addAll(empleadoAux.getListaClientes());
		return listaClientes;

	}


	private boolean validarDatos(String nombre, String apellido, String cedula, String fechaNacimiento) {
		String notificacion = "";

		if (nombre == null || nombre.equals(""))
			notificacion += "Nombre invalido\n";

		if (apellido == null || apellido.equals(""))
			notificacion += "Apellido(s) inválido\n";

		if (cedula == null || cedula.equals("")) {
			notificacion += "Número de cedula invalido\n";
		}

		if(fechaNacimiento == null || fechaNacimiento.equals(""))
			notificacion+= "fecha de nacimiento invalida\n";

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Cliente no creado", notificacion, AlertType.WARNING);
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
    void nuevoCliente(ActionEvent event) {



    }


	@FXML
    void actualizarCliente(ActionEvent event) throws EmpleadoException, ClienteException {
		String nombre= txtNombreCliente.getText();
		String apellido= txtApellidoCliente.getText();
		String cedula= txtCedulaCliente.getText();
		String fechaNacimiento= txtFechaNacimiento.getText();

		String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();

		if (clienteSeleccion!=null) {
			if (validarDatos(nombre, apellido, cedula, fechaNacimiento)) {
				singleton.actualizarCliente(idEmpleado, nombre, apellido, cedula, fechaNacimiento);
				//Se setean los datos que se muestran en la interfaz
				clienteSeleccion.setNombre(nombre);
				clienteSeleccion.setApellido(apellido);
				clienteSeleccion.setIdentificacion(cedula);

				tableViewClientes.refresh();//Refresco la tabla
				mostrarMensaje("Actualizacion cliente", "Cliente actualizado", "Se ha actualizado correctamente el cliente", AlertType.INFORMATION);
			}
		}else {
			mostrarMensaje("Cliente seleccion", "Cliente seleccion", "Ningun cliente ha sido seleccionado", AlertType.WARNING);
		}

    }

    @FXML
    void agregarCliente(ActionEvent event) throws EmpleadoException, ClienteException {
    	String nombre= txtNombreCliente.getText();
    	String apellido= txtApellidoCliente.getText();
    	String cedula= txtCedulaCliente.getText();
    	String fechaNacimiento= txtFechaNacimiento.getText();
    	String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
    	if (validarDatos(nombre,apellido,cedula,fechaNacimiento)) {
    		crearCliente(idEmpleado,nombre,apellido,cedula,fechaNacimiento);
    		txtNombreCliente.setText("");
    		txtApellidoCliente.setText("");
    		txtCedulaCliente.setText("");
    		txtFechaNacimiento.setText("");

		}
    }

    private void crearCliente(String idEmpleado,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException, ClienteException {

    	if (singleton.crearCliente(idEmpleado, nombre, apellido, cedula, fechaNacimiento)) {
    		tableViewClientes.getItems().clear();
    		tableViewClientes.setItems(getListaClientes());
    		mostrarMensaje("Notificacion cliente", "Cliente registrado", "El cliente "+ nombre+ " ha sido registrado con exito", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificacion cliente", "Cliente no registrado", "No ha sido posible registrar al cliente", AlertType.INFORMATION);
		}
	}

    @FXML
    void limpiarInfoCliente(ActionEvent event) {
    	txtNombreCliente.setText("");
    	txtApellidoCliente.setText("");
    	txtCedulaCliente.setText("");
    	txtFechaNacimiento.setText("");
    	clienteSeleccion=null;
    }

    @FXML
    void eliminarCliente(ActionEvent event) throws EmpleadoException, ClienteException {
    	String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
    	if (clienteSeleccion!=null) {
    		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este cliente");
    		if (confirmacion==0) {
    			if (singleton.eliminarCliente(idEmpleado, clienteSeleccion)) {
					listaClientes.remove(clienteSeleccion);
					mostrarMensaje("Eliminacion cliente", "Cliente eliminado", "Se ha eliminado correctamente al cliente", AlertType.INFORMATION);
				}else {
					mostrarMensaje("Eliminacion cliente", "Cliente no eliminado", "No se ha podido eliminar el cliente", AlertType.INFORMATION);
				}
			}
		}else {
			mostrarMensaje("Cliente seleccion", "Cliente seleccion", "No ha seleccionado ningun cliente, por lo tanto no lo puede eliminar", AlertType.WARNING);
		}
    }

    /**
     * Se obtiene el tipo de vehiculo que selecciono del comboBox, esto para habilitar los diferentes campos de texto
     * No se setea ningun comboBox porque no es necesario, ya que de todos los vehiculos se necesita la informacion que dan estos
     * @param event
     */
    @FXML
    void seleccionVehiculo(ActionEvent event) {

    	TipoVehiculo vehiculoSeleccionado= comboBoxTipoVehiculo.getSelectionModel().getSelectedItem();
    	TipoCombustible combustibleSeleccionadoCombustible= comboBoxCombustible.getSelectionModel().getSelectedItem();

    	//Revisa si el vehiculo es una moto para solo habilitar los campos que necesitan
    	if (vehiculoSeleccionado.equals(TipoVehiculo.MOTO)) {
    		//Habilito los campos necesarios
    		txtMarcaVehiculo.setDisable(false);
    		txtModeloVehiculo.setDisable(false);
    		txtCant_CambioVehiculo.setDisable(false);
    		txtCilindraje.setDisable(false);
    		txtVelMaxima.setDisable(false);
    		//Seteo los campos de texto que no utiliza este tipo de vehiculo
			txtNum_bolsas.setDisable(true);
			txtCap_Maletero.setDisable(true);
			txtNum_bolsas.setDisable(true);
			txtNumPasajeros.setDisable(true);
			txtNumPuertas.setDisable(true);
			txtCaballosFuerza.setDisable(true);
			txt100km.setDisable(true);
			txtCapacidadCarga.setDisable(true);
			txtNumEjes.setDisable(true);
			txtSalidasEmergencia.setDisable(true);
			txtTipoCamion.setDisable(true);
			//Seteo los checkBoox
			checkABS.setDisable(true);
			checkVelCrucer.setDisable(true);
			checkAireAcondicionado.setDisable(true);
			checkCamReversa.setDisable(true);
			checkCuatroPorCuatro.setDisable(true);
			checkAsistenteCarril.setDisable(true);
			checkSensColision.setDisable(true);
			checkSensTrafico.setDisable(true);
		}
    	//Revisa si el vehiculo es un sedan para habilitar sus campos
    	if (vehiculoSeleccionado.equals(TipoVehiculo.SEDAN)) {

    		//Habilito todos los campos que necesita el sedan
     		txtMarcaVehiculo.setDisable(false);
    		txtModeloVehiculo.setDisable(false);
    		txtCant_CambioVehiculo.setDisable(false);
    		txtCilindraje.setDisable(false);
    		txtVelMaxima.setDisable(false);
    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtCap_Maletero.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		//Habilito los checkBox que necesito
    		checkAireAcondicionado.setDisable(false);
    		checkCamReversa.setDisable(false);
    		checkVelCrucer.setDisable(false);
    		checkABS.setDisable(false);
    		checkSensColision.setDisable(false);
    		checkSensTrafico.setDisable(false);
    		checkAsistenteCarril.setDisable(false);

    		//inhabilito todos los demas campos
    		txt100km.setDisable(true);
    		txtCaballosFuerza.setDisable(true);
    		txtSalidasEmergencia.setDisable(true);
    		txtTipoCamion.setDisable(true);

    		checkCuatroPorCuatro.setDisable(true);
		}
    	if (vehiculoSeleccionado.equals(TipoVehiculo.DEPORTIVO)) {

      		txtMarcaVehiculo.setDisable(false);
    		txtModeloVehiculo.setDisable(false);
    		txtCant_CambioVehiculo.setDisable(false);
    		txtCilindraje.setDisable(false);
    		txtVelMaxima.setDisable(false);
    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		txtCaballosFuerza.setDisable(false);
    		txt100km.setDisable(false);
    		//Deshabilito los textField que no necesito
      		txtSalidasEmergencia.setDisable(true);
    		txtTipoCamion.setDisable(true);
    		txtNumEjes.setDisable(true);


    		//Deshabilito lo que no necesito
    		checkABS.setDisable(true);
			checkVelCrucer.setDisable(true);
			checkAireAcondicionado.setDisable(true);
			checkCamReversa.setDisable(true);
			checkCuatroPorCuatro.setDisable(true);
			checkAsistenteCarril.setDisable(true);
			checkSensColision.setDisable(true);
			checkSensTrafico.setDisable(true);
			checkCuatroPorCuatro.setDisable(true);
		}

    	if (vehiculoSeleccionado.equals(TipoVehiculo.CAMIONETA)) {
    		//Habilito todos los campos que necesita el sedan
     		txtMarcaVehiculo.setDisable(false);
    		txtModeloVehiculo.setDisable(false);
    		txtCant_CambioVehiculo.setDisable(false);
    		txtCilindraje.setDisable(false);
    		txtVelMaxima.setDisable(false);
    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtCap_Maletero.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		//Habilito los checkBox que necesito
    		checkAireAcondicionado.setDisable(false);
    		checkCamReversa.setDisable(false);
    		checkVelCrucer.setDisable(false);
    		checkABS.setDisable(false);
    		checkSensColision.setDisable(false);
    		checkSensTrafico.setDisable(false);
    		checkAsistenteCarril.setDisable(false);
    		checkCuatroPorCuatro.setDisable(false);
    		//inhabilito todos los demas campos
    		txt100km.setDisable(true);
    		txtCaballosFuerza.setDisable(true);
    		txtSalidasEmergencia.setDisable(true);
    		txtTipoCamion.setDisable(true);
		}
    	if (vehiculoSeleccionado.equals(TipoVehiculo.PICKUP)) {
    		//TxtField necesarios
    		txtMarcaVehiculo.setDisable(false);
    		txtModeloVehiculo.setDisable(false);
    		txtCant_CambioVehiculo.setDisable(false);
    		txtCilindraje.setDisable(false);
    		txtVelMaxima.setDisable(false);
    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		txtCapacidadCarga.setDisable(false);

    		//Cheack box necesarias
    		checkABS.setDisable(false);
    		checkCuatroPorCuatro.setDisable(false);
    		checkAireAcondicionado.setDisable(false);
    		checkCamReversa.setDisable(false);

    		//txtFields innecesarios
		    txtCaballosFuerza.setDisable(true);
		    txtSalidasEmergencia.setDisable(true);
		    txt100km.setDisable(true);
		    txtMarcaVehiculo.setDisable(true);
		    txtCap_Maletero.setDisable(true);
		    txtTipoCamion.setDisable(true);
		    txtNumEjes.setDisable(true);

		    //checkBox innecesarias
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkSensTrafico.setDisable(true);
		    checkVelCrucer.setDisable(true);
		    chekHibridoLigero.setDisable(true);
		    checkEnchufable.setDisable(true);
		}

    	if (vehiculoSeleccionado.equals(TipoVehiculo.VAN)) {
    		//txt necesarios
    		txtMarcaVehiculo.setDisable(false);
    		txtModeloVehiculo.setDisable(false);
    	    txtCant_CambioVehiculo.setDisable(false);
    	    txtVelMaxima.setDisable(false);
    	    txtCilindraje.setDisable(false);
    	    txtNumPasajeros.setDisable(false);
		    txtNumPuertas.setDisable(false);
		    txtCap_Maletero.setDisable(false);
		    txtNum_bolsas.setDisable(false);
		    //checkbox necesarias
		    checkAireAcondicionado.setDisable(false);
		    checkCamReversa.setDisable(false);
		    checkABS.setDisable(false);

		    //CheckBox innecesarias
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkCuatroPorCuatro.setDisable(true);
		    checkSensTrafico.setDisable(true);
		    checkVelCrucer.setDisable(true);
		    chekHibridoLigero.setDisable(true);
		    checkEnchufable.setDisable(true);

    	    //txt innecesarios
		    txtCaballosFuerza.setDisable(true);
		    txtSalidasEmergencia.setDisable(true);
		    txt100km.setDisable(true);
		    txtTipoCamion.setDisable(true);
		    txtCapacidadCarga.setDisable(true);
		    txtNumEjes.setDisable(true);
		    txtAutonomiaCarga.setDisable(true);
		    txtPromedioCarga.setDisable(true);
		}
    	if (vehiculoSeleccionado.equals(TipoVehiculo.BUS)) {
    		//Txtfields necesarios
    		txtMarcaVehiculo.setDisable(true);
			txtModeloVehiculo.setDisable(false);
			txtCant_CambioVehiculo.setDisable(false);
			txtVelMaxima.setDisable(false);
			txtCilindraje.setDisable(false);
			txtNumPasajeros.setDisable(false);
			txtNumPuertas.setDisable(false);
			txtCap_Maletero.setDisable(false);
			txtNumEjes.setDisable(false);
			txtSalidasEmergencia.setDisable(false);

			//checkBox necesarios
		    checkAireAcondicionado.setDisable(false);
		    checkCamReversa.setDisable(false);
		    checkABS.setDisable(false);


			//check box innecesarios
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkCuatroPorCuatro.setDisable(true);
		    checkSensTrafico.setDisable(true);
		    checkVelCrucer.setDisable(true);
		    chekHibridoLigero.setDisable(true);
		    checkEnchufable.setDisable(true);

			//txtFields innecesarios
		    txtCaballosFuerza.setDisable(true);
		    txt100km.setDisable(true);
		    txtTipoCamion.setDisable(true);
		    txtNum_bolsas.setDisable(false);;
		    txtCapacidadCarga.setDisable(true);
		    txtAutonomiaCarga.setDisable(true);
		    txtPromedioCarga.setDisable(true);
		}


    	if (vehiculoSeleccionado.equals(TipoVehiculo.CAMION)) {

    		//txtFields necesarios
    		txtMarcaVehiculo.setDisable(true);
			txtModeloVehiculo.setDisable(false);
			txtCant_CambioVehiculo.setDisable(false);
			txtVelMaxima.setDisable(false);
			txtCilindraje.setDisable(false);
			txtCapacidadCarga.setDisable(false);
			txtTipoCamion.setDisable(false);
			txtNumEjes.setDisable(false);
			//checkBox neecesarias
			checkAireAcondicionado.setDisable(false);
			checkABS.setDisable(false);
			//txtfields Innecesarios
		    txtCaballosFuerza.setDisable(false);
		    txtSalidasEmergencia.setDisable(false);
		    txt100km.setDisable(true);
		    txtCap_Maletero.setDisable(true);
		    txtNumPasajeros.setDisable(true);
		    txtNum_bolsas.setDisable(false);;
		    txtNumPuertas.setDisable(true);;
		    txtAutonomiaCarga.setDisable(true);
		    txtPromedioCarga.setDisable(true);
		    //checkBox innecesarios
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkCamReversa.setDisable(true);
		    checkCuatroPorCuatro.setDisable(true);
		    checkSensTrafico.setDisable(true);
		    checkVelCrucer.setDisable(true);
		    chekHibridoLigero.setDisable(true);
		    checkEnchufable.setDisable(true);


    	}

    }





    @FXML
    void registrarVehiculo(ActionEvent event) {

    }


    @FXML
    void mostrarVentanPrincipal(ActionEvent event) {
    	this.stage.close();
    	principioController.show();
    }

    @FXML
    void initialize() {

    }
	public void init(Stage stage, LoginEmpleadoController loginEmpleadoController) {
		this.loginEmpleadoController = loginEmpleadoController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;

	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}

/**
 * Se inicializan los valores de los combo box
 * Se organizan los valores de las tablas
 *
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxTipoVehiculo.getItems().addAll(TipoVehiculo.MOTO,TipoVehiculo.SEDAN,TipoVehiculo.PICKUP,
				TipoVehiculo.DEPORTIVO,TipoVehiculo.CAMIONETA,TipoVehiculo.BUS,TipoVehiculo.CAMION,TipoVehiculo.VAN);

		comboBoxTipoTransmicion.getItems().addAll(TipoTransmicion.AUTOMATICA,TipoTransmicion.MANUAL);

		comboBoxEstado.getItems().addAll(TipoEstado.NUEVO, TipoEstado.USADO);

		comboBoxCombustible.getItems().addAll(TipoCombustible.DIESEL,TipoCombustible.GASOLINA, TipoCombustible.ELECTRICO,TipoCombustible.HIBRIDO);


		this.columNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columApellidosCliente.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("identficacion"));

		tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				clienteSeleccion= newSelection;
				//mostrarInformacionCliente();
			}
		});


	}
}
