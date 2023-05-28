/**
 *
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Exceptions.AdministradorException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Exceptions.TransaccionException;
import Exceptions.VehiculoException;
import Exceptions.VentaException;

/**
 * @author Juan Miguel
 * @author Santiago Ovalle
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
	private List<Persona> listaPersonas= new ArrayList<>();
	private List<Vehiculo> listaVehiculosAlquiladosList= new ArrayList<>();
	private List<Vehiculo> listaVehiculosVendidosList= new ArrayList<>();


	public Concesionario (){

	}

	public Concesionario(String nombre) {
		this.nombre = nombre;
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

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public List<Vehiculo> getListaVehiculosAlquiladosList() {
		return listaVehiculosAlquiladosList;
	}

	public void setListaVehiculosAlquiladosList(List<Vehiculo> listaVehiculosAlquiladosList) {
		this.listaVehiculosAlquiladosList = listaVehiculosAlquiladosList;
	}

	public List<Vehiculo> getListaVehiculosVendidosList() {
		return listaVehiculosVendidosList;
	}

	public void setListaVehiculosVendidosList(List<Vehiculo> listaVehiculosVendidosList) {
		this.listaVehiculosVendidosList = listaVehiculosVendidosList;
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
	/*
	 * El funcionamiento general de los metodos de creacion de los vehiculos es el siguiente: Primero se obtiene un empleado encargado, se verifica que no sea un valor nulo
	 * Despues, lo que se hace es evaluar el tipo de combustible del vehiculo, si es electrico, significa que no es hibrido por lo que los atributos de hibrido se setean
	 *  como false
	 *Si es hibrido, se verifica si es enchufable, si lo es se setea el atributo de hibridoLigero como false, si no lo es, se pasa el atributo comun y corriente
	 *Si es diesel o gasolina, se crea un vehiculo normal, sin embargo, los atributos de electrico e hibrido se setean como nulos o false, segun corresponda
	 *
	 *Los vehiculos creado tienen una notacion especifica de acuerdo a su combustible
	 *VehiculoE= vehiculo electrico
	 *VehiculoH= vehiculo hibrido
	 *VehiculoD= vehiculo Diesel
	 *
	 * */




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
	 * @param precio
	 * @param autonomia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearMoto(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio, String autonomia, String tiempoPromedioCarga,
			boolean esEnchufable, boolean esHibridoLigero) throws EmpleadoException, VehiculoException{


		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);

		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo motoE = new Moto(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado,
					precio, autonomia, tiempoPromedioCarga, false, false);
			if (empleadoAux.crearVehiculo(motoE)) {
				listaVehiculos.add(motoE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo motoH = new Moto(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible,
						estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero);
				if (empleadoAux.crearVehiculo(motoH)) {
					creado = true;
					listaVehiculos.add(motoH);
				}
			} else {
				Vehiculo motoH = new Moto(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible,
						estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, false);
				if (empleadoAux.crearVehiculo(motoH)) {
					creado = true;
					listaVehiculos.add(motoH);
				}
			}
			break;

		default:
			Vehiculo motoD= new Moto(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null,
					null, false, false);
			if (empleadoAux.crearVehiculo(motoD)) {
				creado=true;
				listaVehiculos.add(motoD);
			}
			break;
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
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
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
	public boolean crearSedan(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String  num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril) throws VehiculoException, EmpleadoException{

		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);

		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo sedanE= new Sedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
					false, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision,
					sen_Trafico_Cruzado, asistente_Carril);

			if (empleadoAux.crearVehiculo(sedanE)) {
				listaVehiculos.add(sedanE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo sedanH= new Sedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision,
						sen_Trafico_Cruzado, asistente_Carril);
				if (empleadoAux.crearVehiculo(sedanH)) {
					creado = true;
					listaVehiculos.add(sedanH);
				}

			} else {
				Vehiculo sedanH= new Sedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision,
						sen_Trafico_Cruzado, asistente_Carril);
				if (empleadoAux.crearVehiculo(sedanH)) {
					creado = true;
					listaVehiculos.add(sedanH);
				}
			}
			break;

		default:
			Vehiculo sedanD= new Sedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision,
					sen_Trafico_Cruzado, asistente_Carril);
			if (empleadoAux.crearVehiculo(sedanD)) {
				creado= true;
				listaVehiculos.add(sedanD);
			}
			break;
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
	public boolean crearDeportivo(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String num_Bolsas, String  num_Caballos_Fuerza, String tiempo_en_100KM) throws EmpleadoException, VehiculoException{

		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo deportivoE= new Deportivo(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
					false, false, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);

			if (empleadoAux.crearVehiculo(deportivoE)) {
				listaVehiculos.add(deportivoE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo deportivoH= new Deportivo(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);
				if (empleadoAux.crearVehiculo(deportivoH)) {
					creado = true;
					listaVehiculos.add(deportivoH);
				}
			} else {
				Vehiculo deportivoH= new Deportivo(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, false, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);

				if (empleadoAux.crearVehiculo(deportivoH)) {
					creado = true;
					listaVehiculos.add(deportivoH);
				}
			}
			break;

		default:
			Vehiculo deportivoD= new Deportivo(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false, false, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);
			if (empleadoAux.crearVehiculo(deportivoD)) {
				creado = true;
				listaVehiculos.add(deportivoD);
			}
			break;
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
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
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
	public boolean crearCamioneta(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril,
			boolean esCuatroxCuatro) throws VehiculoException, EmpleadoException {

		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);

		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo camionetaE = new Camioneta(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, false,
					false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado,
					asistente_Carril, esCuatroxCuatro);

			if (empleadoAux.crearVehiculo(camionetaE)) {
				listaVehiculos.add(camionetaE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo camionetaH = new Camioneta(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable,esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado,
						asistente_Carril, esCuatroxCuatro);
				if (empleadoAux.crearVehiculo(camionetaH)) {
					creado = true;
					listaVehiculos.add(camionetaH);
				}
			} else {
				Vehiculo camionetaH = new Camioneta(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable,false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado,
						asistente_Carril, esCuatroxCuatro);
				if (empleadoAux.crearVehiculo(camionetaH)) {
					creado = true;
					listaVehiculos.add(camionetaH);
				}
			}
			break;

		default:
			Vehiculo camionetaD = new Camioneta(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false,false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado,
					asistente_Carril, esCuatroxCuatro);
			if (empleadoAux.crearVehiculo(camionetaD)) {
				creado = true;
				listaVehiculos.add(camionetaD);
			}

			break;
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
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
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
	public boolean crearPickUp(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String num_Bolsas, String capacidadCarga, boolean aire_Acondicionado,
			boolean cam_Reversa, boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) throws EmpleadoException, VehiculoException {
		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);

		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo pickUpE= new Pick_Up(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
					false, false, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);
			if (empleadoAux.crearVehiculo(pickUpE)) {
				listaVehiculos.add(pickUpE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo pickUpH= new Pick_Up(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);
				if (empleadoAux.crearVehiculo(pickUpH)) {
					creado = true;
					listaVehiculos.add(pickUpH);
				}
			} else {
				Vehiculo pickUpH= new Pick_Up(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);
				if (empleadoAux.crearVehiculo(pickUpH)) {
					creado = true;
					listaVehiculos.add(pickUpH);
				}
			}
			break;

		default:
			Vehiculo pickUpD= new Pick_Up(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false, false, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);
			if (empleadoAux.crearVehiculo(pickUpD)) {
				creado = true;
				listaVehiculos.add(pickUpD);
			}

			break;
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
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
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
	public boolean crearVan(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs) throws EmpleadoException, VehiculoException {


		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);


		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo vanE= new Van(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
					false, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);
			if (empleadoAux.crearVehiculo(vanE)) {
				listaVehiculos.add(vanE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo vanH= new Van(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);

				if (empleadoAux.crearVehiculo(vanH)) {
					creado = true;
					listaVehiculos.add(vanH);
				}
			} else {
				Vehiculo vanH= new Van(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);

				if (empleadoAux.crearVehiculo(vanH)) {
					creado = true;
					listaVehiculos.add(vanH);
				}
			}
			break;

		default:
			Vehiculo vanD= new Van(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);

			if (empleadoAux.crearVehiculo(vanD)) {
				creado = true;
				listaVehiculos.add(vanD);
			}

			break;
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
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
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
	public boolean crearBus(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			boolean vel_Crucero, String num_Bolsas, boolean abs, String num_ejes, String num_salidas_emergencia) throws EmpleadoException, VehiculoException {


		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo busE = new Bus(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
					false, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas,
					abs, num_ejes, num_salidas_emergencia);

			if (empleadoAux.crearVehiculo(busE)) {
				listaVehiculos.add(busE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo busH = new Bus(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas,
						abs, num_ejes, num_salidas_emergencia);
				if (empleadoAux.crearVehiculo(busH)) {
					creado = true;
					listaVehiculos.add(busH);
				}
			} else {
				Vehiculo busH = new Bus(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas,
						abs, num_ejes, num_salidas_emergencia);
				if (empleadoAux.crearVehiculo(busH)) {
					creado = true;
					listaVehiculos.add(busH);
				}
			}
			break;

		default:
			Vehiculo busD = new Bus(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false, false, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas,
					abs, num_ejes, num_salidas_emergencia);
			if (empleadoAux.crearVehiculo(busD)) {
				creado = true;
				listaVehiculos.add(busD);
			}
			break;
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
	 * @param precio
	 * @param autonimia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
	 * @param aire_Acondicionado
	 * @param abs
	 * @param num_ejes
	 * @param tipo_Camion
	 * @return
	 * @throws EmpleadoException
	 * @throws VehiculoException
	 */
	public boolean crearCamion(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			boolean aire_Acondicionado, boolean abs, String num_ejes, String tipo_Camion) throws EmpleadoException, VehiculoException {


		boolean creado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);


		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no está registrado");
		}

		switch (combustible) {
		case ELECTRICO:
			Vehiculo camionE= new Camion(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
					false, false, aire_Acondicionado, abs, num_ejes, tipo_Camion);

			if (empleadoAux.crearVehiculo(camionE)) {
				listaVehiculos.add(camionE);
				creado = true;
			}
			break;

		case HIBRIDO:
			if (!esEnchufable) {
				Vehiculo camionH= new Camion(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, esHibridoLigero, aire_Acondicionado, abs, num_ejes, tipo_Camion);
				if (empleadoAux.crearVehiculo(camionH)) {
					creado = true;
					listaVehiculos.add(camionH);
				}
			} else {
				Vehiculo camionH= new Camion(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga,
						esEnchufable, false, aire_Acondicionado, abs, num_ejes, tipo_Camion);
				if (empleadoAux.crearVehiculo(camionH)) {
					creado = true;
					listaVehiculos.add(camionH);
				}
			}
			break;

		default:
			Vehiculo camionD= new Camion(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, null, null,
					false, false, aire_Acondicionado, abs, num_ejes, tipo_Camion);
			if (empleadoAux.crearVehiculo(camionD)) {
				creado=true;
				listaVehiculos.add(camionD);
			}



			break;
		}
		return creado;
	}
	/**
	 *
	 * @param marca
	 * @param modelo
	 * @return
	 */
	private Vehiculo obtenerVehiculo(String marca, String modelo) {
		return (Vehiculo) listaVehiculos.stream().filter(v -> v.getMarca().equals(marca) && v.getModelo().equals(modelo));

	}
	/**
	 *
	 * @param vehiculoEliminar
	 * @return
	 * @throws VehiculoException
	 */
	public boolean eliminarVehiculo(Vehiculo vehiculoEliminar) throws VehiculoException {

		boolean eliminado = false;
		Vehiculo vehiculoEncontrado= obtenerVehiculo(vehiculoEliminar.getMarca(), vehiculoEliminar.getModelo());
		if (vehiculoEncontrado==null) {
			throw new VehiculoException("El vehiculo que desea eliminar no se encuentra registrado");
		}else {
			eliminado= true;
			listaVehiculos.remove(vehiculoEncontrado);
		}

		return eliminado;
	}



//----------------------------------------------------Transacciones----------------------------------------------------------------------------
	/**
	 *
	 * @param codigo
	 * @return
	 */
	public Transaccion obtenerTransaccion(String codigo){
		return (Transaccion) listaTransacciones.stream().filter(t -> t.getCodigo().equals(codigo));
	}
	/**
	 *
	 * @param codigo
	 * @return
	 */
	public boolean verificarTransaccion(String codigo){
		boolean encontrada= false;

		List<Transaccion> transaccionesEncontradas = (List<Transaccion>) this.listaTransacciones.stream()
				.filter(t ->t.getCodigo().equals(codigo))
				.collect(Collectors.toList());			;

		if (!transaccionesEncontradas.isEmpty()) {
			encontrada= true;
		}

		return encontrada;
	}
	/**
	 *
	 * @param identificacionEmpleado
	 * @param fecha
	 * @param total
	 * @param codigo
	 * @return
	 * @throws EmpleadoException
	 * @throws TransaccionException
	 */
	public boolean crearTransaccion(String identificacionEmpleado, TipoTransaccion tipoTransaccion, String fecha, double total , String codigo, String cantDias, String cedulaCliente)
			throws EmpleadoException, TransaccionException{
		boolean creada= false;

		Empleado empleadoAux= obtenerEmpleado(identificacionEmpleado);
		//Transaccion nuevaTransaccion= new Transaccion(fecha, total, codigo, tipoTransaccion, cantDias);
		if (empleadoAux==null) {
			throw new EmpleadoException("El empleado no esta registrado");
		}

		if (tipoTransaccion.equals(TipoTransaccion.COMPRA)) {
			Transaccion nuevaTransaccion= new Transaccion(fecha, total, codigo, tipoTransaccion, null,cedulaCliente);
			creada= empleadoAux.crearTransaccion(nuevaTransaccion);
			return creada;

		}else if (tipoTransaccion.equals(TipoTransaccion.ALQUILER)) {
			Transaccion nuevaTransaccion= new Transaccion(fecha, total, codigo, tipoTransaccion, cantDias,cedulaCliente);
			creada= empleadoAux.crearTransaccion(nuevaTransaccion);
			return creada;
		}

		//return obtenerEmpleado(identificacionEmpleado)!=null && obtenerEmpleado(identificacionEmpleado).crearTransaccion(nuevaTransaccion) ? true : false;
		return creada;
	}
	/**
	 *
	 * @param transaccionEliminar
	 * @return
	 * @throws TransaccionException
	 */
	public boolean eliminarTransaccion(Transaccion transaccionEliminar) throws TransaccionException{
		boolean eliminado= false;
		Transaccion transaccionEncontrada= obtenerTransaccion(transaccionEliminar.getCodigo());
		if (transaccionEncontrada==null) {
			throw new TransaccionException("La transaccion que desea eliminar no se encuentra registrada");
		}else {
			eliminado= true;
			listaTransacciones.remove(transaccionEncontrada);
		}
		return eliminado;

	}

//-----------------------------------------VENTA Y ALQUILER DE LOS VEHICULOS---------------------------------------------------




	/**
	 * En este metodod se verifica primero que el empleado exista, esto por medio del metodo obtenerEmpleado, si este toma un valor nulo, retorna false
	 * En cambio, si es diferente de null y el retusultado del metodo de venderVehiculo es true, este metodo retorna true
	 * Tambien se verifica que un vehiculo no se encuentrea alquilado para poder realizar la venta de este
	 * Se verifica que no este vendido ya, aunque estoe es casi imposible porque al momento de venderlo ya se borra de la lista,
	 * sin embargo, nunca esta mal una confirmacion mas
	 * @param idEmpleado
	 * @param vehiculoVenta
	 * @return
	 * @throws VehiculoException
	 * @throws EmpleadoException
	 * @throws VentaException
	 */
	public boolean venderVehiculo(String idEmpleado, Vehiculo vehiculoVenta) throws VehiculoException, EmpleadoException, VentaException {
		Empleado empleadoAux=obtenerEmpleado(idEmpleado);
		if (empleadoAux== null){
			throw new EmpleadoException("El empleado no está registrado");
		}
		/*Verifico que no se encuentre en alquiler
		 * */
		for (Vehiculo vehiculoAux : listaVehiculosAlquiladosList) {
			if (vehiculoAux==vehiculoVenta) {
				throw new VentaException("Un vehiculo alquilado no puede ser vendido");
			}
		}
		for (Vehiculo vehiculoAux : listaVehiculosVendidosList) {
			if (vehiculoAux== vehiculoVenta) {
				throw new VentaException("El vehiculo ya se encuentra vendido, por lo tanto no está disponible");
			}
		}

		if (empleadoAux.venderVehiculo(vehiculoVenta)) {
			listaVehiculosVendidosList.add(vehiculoVenta);
			listaVehiculos.remove(vehiculoVenta);
			return true;
		}


		return false;
		//return  obtenerEmpleado(idEmpleado)!=null && obtenerEmpleado(idEmpleado).venderVehiculo(vehiculoVenta) ? true : false;
	}
	public boolean alquilarVehiculo(String idEmpleado,Vehiculo  vehiculoAlquiler) {
		boolean alquilado= false;


		return alquilado;
	}


}
