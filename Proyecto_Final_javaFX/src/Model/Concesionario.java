/**
 *
 */
package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import Exceptions.AdministradorException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Exceptions.VehiculoException;

/**
 * @author Juan Miguel
 *
 */
public class Concesionario {
	//Atributos
	private String nombre;
	private List<Vehiculo> listaVehiculos= new ArrayList<>();
	private List<Administrador> listaAdministradores= new ArrayList<>();
	private List<Empleado> listaEmpleados= new ArrayList<>();
	private List<Transaccion> listaTransacciones= new ArrayList<>();
	private List<Cliente> listaClientes= new ArrayList<Cliente>();

	public Concesionario (){

	}

	public Concesionario(String nombre) {
		this.nombre = nombre;
		Administrador administrador1= new Administrador("Miguel", "florez", "1010", "1234");
		Administrador administrador2= new Administrador("Miguel", "florez", "2314", "12345");
		listaAdministradores.add(administrador1);




	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}


	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}


	public List<Administrador> getListaAdministradores() {
		return listaAdministradores;
	}


	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public void setListaAdministradores(List<Administrador> listaAdministradores) {
		this.listaAdministradores = listaAdministradores;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public void setListaTransacciones(List<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	@Override
	public String toString() {
		return "Concesionario " + nombre +  "\nLista de Vehiculos: \n" + listaVehiculos +
				"\nLista de Administradores: \n" + listaAdministradores;
	}

//----------------------------CRUD DEL ADMINISTRADOR-------------------------------------------------------------------------

	/**
	 * Metodo que sirve para verificar si un administrador ya existe, un administrador a otro si tienen la misma cedula
	 * @param identificacion
	 * @return
	 */
	public boolean verificarAdministrador(String identificacion, String contrasenia){
		boolean verificado= false;
		for (Administrador administradorAux : listaAdministradores) {
			if(administradorAux.verificarIDContrasenia(identificacion, contrasenia)){
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}

	private boolean verificarAdministrador(String identificacion){
		boolean verificado= false;
		for (Administrador administradorAux : listaAdministradores) {
			if(administradorAux.verificarIdentificacion(identificacion)){
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}

	/**
	 * Metodo que obtiene un administrador en base a su cedula y sus credenciales(contrasenia)
	 * @param identificacion
	 * @return
	 */
	public Administrador obtenerAdministrador(String identificacion, String contrasenia){
		Administrador administradorEncontrado= null;
		for (Administrador administradorAux : listaAdministradores) {
			if (administradorAux.verificarIdentificacion(identificacion))
				administradorEncontrado= administradorAux;
		}
		return administradorEncontrado;
	}

	public Administrador obtenerAdministrador(String identificacion){
		Administrador administradorEncontrado= null;
		for (Administrador administradorAux : listaAdministradores) {
			if (administradorAux.verificarIdentificacion(identificacion))
				administradorEncontrado= administradorAux;
		}
		return administradorEncontrado;
	}

	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param credenciales
	 * @return
	 * @throws AdministradorException
	 */
	public boolean crearAdministrador(Administrador newAdministrador) throws AdministradorException{
		boolean creado= false;
		boolean administradorEncontrado= verificarAdministrador(newAdministrador.getIdentificacion());
		if(administradorEncontrado){
			creado=false;
			throw new  AdministradorException("El empleado ya se encuentra registrado");
		}else{
			creado=true;
			listaAdministradores.add(newAdministrador);
		}

		return creado;
	}

	/**
	 *
	 * @param identificacion
	 * @return
	 * @throws AdministradorException
	 */
	public boolean eliminarAdministrador(Administrador administradorEliminar) throws AdministradorException {
		boolean eliminado= false;
		Administrador adminEncontrado= obtenerAdministrador(administradorEliminar.getIdentificacion());
		if (adminEncontrado==null) {
			throw new AdministradorException("El administrador que desea eliminar no se encuentra en el sistema");
		}else {
			eliminado=true;
			listaAdministradores.remove(adminEncontrado);

		}
		return eliminado;
	}
	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param credenciales
	 * @return
	 * @throws AdministradorException
	 */
	public boolean actualizarAdministrador(String nombre, String apellido, String identificacion, String credenciales) throws AdministradorException{
		boolean actualizado= false;
		Administrador administradorAux= obtenerAdministrador(identificacion);
		if (administradorAux== null) {
			throw new AdministradorException("El administrador no se ha encontrado");
		}
		actualizado= true;
		administradorAux.setNombre(nombre);
		administradorAux.setApellido(apellido);
		administradorAux.setIdentificacion(identificacion);
		administradorAux.setCredenciales(credenciales);

		return actualizado;
	}


//----------------------------------------------------CRUD EMPLEADO---------------------------------------------------------------------
	/**
	 * Metodo necesario para saber si el empleado existe en la lista general de los empleados
	 * @param identificacion
	 * @return
	 */
	public boolean verificarEmpleado(String identificacion){
		boolean verificado= false;
		for (Empleado empleadoAux : listaEmpleados) {
			if (empleadoAux.verificarIdentificacion(identificacion)) {
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}
	/**
	 *
	 * @param identificacion
	 * @return
	 */
	public Empleado obtenerEmpleado(String identificacion){
		Empleado empleado= null;
		for (Empleado empleadoAux : listaEmpleados) {
			if (empleadoAux.verificarIdentificacion(identificacion)) {
				empleado= empleadoAux;
			}
		}
		return empleado;
	}


	/**
	 * En este metodo inicialmente obtenemos un administrador, que es el que esta registrando al empleado, se verifica que este exista, si existe, este administrador va a registrar
	 * al empleado y lo añade a su lista de empleados, esto ya se realiza en los metodos de administrador
	 * Si la funcion administradorAux.crearEmpleado() retorna true, es que se ha podido crear satisfactoriamente el empleado
	 * @param identificacionAdmin
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws EmpleadoException
	 * @throws AdministradorException
	 */
	public boolean crearEmpleado(Administrador administradorEmpleado, Empleado newEmpleado) throws EmpleadoException,AdministradorException {
		boolean creado= false;
		Administrador administradorAux= administradorEmpleado;
		if (administradorAux==null) {
			throw new AdministradorException("El administrador encargado no se encuentra en la base de datos");
		}

		if(administradorAux.crearEmpleado(newEmpleado)){
			creado=true;
			this.listaEmpleados.add(newEmpleado);
		}else {//Preguntar si esta excepcion es necesaria teniendo en cuenta que ya se esta manejando en el metodo de crearEmpleado()
			throw new EmpleadoException("El empleado no ha podido ser registrado");
		}
		//return  administradorAux.crearEmpleado(nombre, apellido, identificacion)
		return creado;
	}
	/**
	 *
	 * @param identificacionAdmin
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws AdministradorException
	 * @throws EmpleadoException
	 */
	public boolean  eliminarEmpleado(String identificacionAdmin,Empleado empleadoEliminar) throws AdministradorException, EmpleadoException {
		boolean eliminado= false;
		Administrador administradorAux= obtenerAdministrador(identificacionAdmin);
		if (administradorAux==null) {
			throw new AdministradorException("El administrador no se encuentra en la base de datos");
		}
		if (administradorAux.eliminarEmpleado(empleadoEliminar)) {
			eliminado=true;
			this.listaEmpleados.remove(empleadoEliminar);
		}
		//return administradorAux.eliminarEmpleado(identificacion);
		return eliminado;
	}

	/**
	 *
	 * @param identificacionAdmin
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws AdministradorException
	 * @throws EmpleadoException
	 */

	public boolean actualizarEmpleado(String identificacionAdmin,String nombre, String apellido, String identificacion) throws AdministradorException, EmpleadoException{
		boolean actualizado= false;
		Administrador administradorAux= obtenerAdministrador(identificacionAdmin);
		if (administradorAux==null) {
			throw new AdministradorException("El administrador no se encuentra en la base de datos");
		}

		if (administradorAux.actualizarEmpleado(nombre, apellido, identificacion)) {
			actualizado=true;
		}

		//return administradorAux.actualizarEmpleado(nombre, apellido, identificacion)
		return actualizado;
	}

//-------------------------------------------------------CRUD CLIENTE--------------------------------------------------------------------------------------------

	/**
	 *
	 * @param identificacionEmpleado
	 * @param newCliente
	 * @return
	 * @throws EmpleadoException
	 * @throws ClienteException
	 */
	public boolean crearCliente(String identificacionEmpleado, Cliente newCliente) throws EmpleadoException, ClienteException{
		boolean creado= false;
		Empleado empleadoCliente= obtenerEmpleado(identificacionEmpleado);
		if (empleadoCliente==null) {
			throw new EmpleadoException("El empleado no se encuentra registrado");
		}

		if (empleadoCliente.crearCliente(newCliente)) {
			creado=true;
			this.listaClientes.add(newCliente);
		}
		return creado;
	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param clienteEliminar
	 * @return
	 * @throws EmpleadoException
	 * @throws ClienteException
	 */
	public boolean eliminarCliente(String identificacionEmpleado, Cliente clienteEliminar) throws EmpleadoException, ClienteException{
		boolean eliminado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.eliminarCliente(clienteEliminar)) {
			eliminado= true;
			this.listaClientes.remove(clienteEliminar);
		}

		return eliminado;
	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param fechaNacimiento
	 * @return
	 * @throws EmpleadoException
	 * @throws ClienteException
	 */
	public boolean actualizarCliente(String identificacionEmpleado,String nombre, String apellido, String identificacion, String fechaNacimiento) throws EmpleadoException, ClienteException{
		boolean actualizado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}
		if (empleadoAux.actualizarCliente(nombre, apellido, identificacion, fechaNacimiento)) {
			actualizado=true;
		}


		return actualizado;

	}
//----------------------------------------CRUD VEHICULOS----------------------------------------------------------------

	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearMoto(String identificacionEmpleado, String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado) throws EmpleadoException, VehiculoException{
		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo moto= new Moto(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);

		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}
		if (empleadoAux.crearMoto(moto)) {
			creado= true;
			this.listaVehiculos.add(moto);
		}
		return creado;

	}
	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param num_Bolsas
	 * @param abs
	 * @param sen_Colision
	 * @param sen_Trafico_Cruzado
	 * @param asistente_Carril
	 * @return
	 * @throws VehiculoException
	 * @throws EmpleadoException
	 */
	public boolean crearSedan(String identificacionEmpleado,String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, int num_Bolsas,
			boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril) throws VehiculoException, EmpleadoException{

		boolean creado= false;

		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo sedan = new Sedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, num_pasajeros,
				num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril);


		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}

		if (empleadoAux.crearSedan(sedan)) {
			creado= true;
			this.listaVehiculos.add(sedan);
		}

		return creado;
	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param num_Bolsas
	 * @param num_Caballos_Fuerza
	 * @param tiempo_en_100KM
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearDeportivo(String identificacionEmpleado,String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int num_Bolsas, double num_Caballos_Fuerza, double tiempo_en_100KM) throws EmpleadoException, VehiculoException{

		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo deportivo= new Deportivo(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado,
				num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);

		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.crearDeportivo(deportivo)) {
			creado= true;
			this.listaVehiculos.add(deportivo);
		}

		return creado;
	}
	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param num_Bolsas
	 * @param abs
	 * @param sen_Colision
	 * @param sen_Trafico_Cruzado
	 * @param asistente_Carril
	 * @param esCuatroxCuatro
	 * @return
	 * @throws VehiculoException
	 * @throws EmpleadoException
	 */
	public boolean crearCamioneta(String identificacionEmpleado,String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, int num_Bolsas,
			boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril,
			boolean esCuatroxCuatro) throws VehiculoException, EmpleadoException {

		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo camioneta= new Camioneta(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, num_pasajeros, num_Puertas,
				cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril, esCuatroxCuatro);

		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.crearDeportivo(camioneta)) {
			creado= true;
			this.listaVehiculos.add(camioneta);
		}

		return creado;

	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param num_Bolsas
	 * @param capacidadCarga
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param vel_Crucero
	 * @param abs
	 * @param esCuatroPorCuatro
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearPickUp(String identificacionEmpleado, String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int num_Bolsas, int capacidadCarga, boolean aire_Acondicionado, boolean cam_Reversa,
			boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) throws EmpleadoException, VehiculoException {
		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo pickUp= new Pick_Up(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, num_pasajeros, num_Puertas,
				num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);


		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.crearDeportivo(pickUp)) {
			creado= true;
			this.listaVehiculos.add(pickUp);
		}

		return creado;
	}

	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param num_Bolsas
	 * @param abs
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearVan(String identificacionEmpleado, String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, int num_Bolsas,
			boolean abs) throws EmpleadoException, VehiculoException {
		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo van= new Van(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, num_pasajeros,
				num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);


		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.crearDeportivo(van)) {
			creado= true;
			this.listaVehiculos.add(van);
		}

		return creado;
	}
	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param vel_Crucero
	 * @param num_Bolsas
	 * @param abs
	 * @param num_ejes
	 * @param num_salidas_emergencia
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearBus(String identificacionEmpleado, String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, boolean vel_Crucero,
			int num_Bolsas, boolean abs, int num_ejes, int num_salidas_emergencia) throws EmpleadoException, VehiculoException {
		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo bus= new Bus(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, num_pasajeros, num_Puertas, cap_Maletero,
				aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas, abs, num_ejes, num_salidas_emergencia);

		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.crearDeportivo(bus)) {
			creado= true;
			this.listaVehiculos.add(bus);
		}

		return creado;
	}
	/**
	 *
	 * @param identificacionEmpleado
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param aire_Acondicionado
	 * @param abs
	 * @param num_ejes
	 * @param tipo_Camion
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearCamion(String identificacionEmpleado, String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, boolean aire_Acondicionado,
			boolean abs, int num_ejes, String tipo_Camion) throws EmpleadoException, VehiculoException {
		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		Vehiculo camion= new Camion(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, aire_Acondicionado, abs, num_ejes, tipo_Camion);

		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.crearDeportivo(camion)) {
			creado= true;
			this.listaVehiculos.add(camion);
		}

		return creado;
	}


	/**
	 *
	 * @param identificacionEmpleado
	 * @param vehiculoEliminar
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean eliminarVehiculo(String  identificacionEmpleado,Vehiculo vehiculoEliminar) throws EmpleadoException, VehiculoException {
		boolean eliminado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}
		if (empleadoAux.eliminarVehiculo(vehiculoEliminar)) {
			eliminado= true;
			this.listaVehiculos.remove(vehiculoEliminar);
		}
		return eliminado;
	}


}
