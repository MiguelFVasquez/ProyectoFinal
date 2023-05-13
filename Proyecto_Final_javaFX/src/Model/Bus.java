package Model;

import java.io.Serializable;

public class Bus extends Vehiculo implements Serializable{
    private int num_pasajeros;
    private int num_Puertas;
    private int cap_Maletero;
    private boolean aire_Acondicionado;
    private boolean cam_Reversa;
    private boolean vel_Crucero;
    private int num_Bolsas;
    private boolean abs;
    private int num_ejes;
    private int num_salidas_emergencia;

    public Bus(){
    }

    public Bus(int num_pasajeros, int num_Puertas, int cap_Maletero, boolean aire_Acondicionado,
    		boolean cam_Reversa, boolean vel_Crucero, int num_Bolsas, boolean abs, int num_ejes,
    		int num_salidas_emergencia) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.cap_Maletero = cap_Maletero;
        this.aire_Acondicionado = aire_Acondicionado;
        this.cam_Reversa = cam_Reversa;
        this.vel_Crucero = vel_Crucero;
        this.num_Bolsas = num_Bolsas;
        this.abs = abs;
        this.num_ejes = num_ejes;
        this.num_salidas_emergencia = num_salidas_emergencia;
    }


    public Bus(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, boolean vel_Crucero,
			int num_Bolsas, boolean abs, int num_ejes, int num_salidas_emergencia) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.cap_Maletero = cap_Maletero;
		this.aire_Acondicionado = aire_Acondicionado;
		this.cam_Reversa = cam_Reversa;
		this.vel_Crucero = vel_Crucero;
		this.num_Bolsas = num_Bolsas;
		this.abs = abs;
		this.num_ejes = num_ejes;
		this.num_salidas_emergencia = num_salidas_emergencia;
	}

	public int getNum_pasajeros() {
        return num_pasajeros;
    }

    public void setNum_pasajeros(int num_pasajeros) {
        this.num_pasajeros = num_pasajeros;
    }

    public int getNum_Puertas() {
        return num_Puertas;
    }

    public void setNum_Puertas(int num_Puertas) {
        this.num_Puertas = num_Puertas;
    }

    public int getCap_Maletero() {
        return cap_Maletero;
    }

    public void setCap_Maletero(int cap_Maletero) {
        this.cap_Maletero = cap_Maletero;
    }

    public boolean isAire_Acondicionado() {
        return aire_Acondicionado;
    }

    public void setAire_Acondicionado(boolean aire_Acondicionado) {
        this.aire_Acondicionado = aire_Acondicionado;
    }

    public boolean isCam_Reversa() {
        return cam_Reversa;
    }

    public void setCam_Reversa(boolean cam_Reversa) {
        this.cam_Reversa = cam_Reversa;
    }

    public boolean isVel_Crucero() {
        return vel_Crucero;
    }

    public void setVel_Crucero(boolean vel_Crucero) {
        this.vel_Crucero = vel_Crucero;
    }

    public int getNum_Bolsas() {
        return num_Bolsas;
    }

    public void setNum_Bolsas(int num_Bolsas) {
        this.num_Bolsas = num_Bolsas;
    }

    public boolean isAbs() {
        return abs;
    }

    public void setAbs(boolean abs) {
        this.abs = abs;
    }

    public int getNum_ejes() {
        return num_ejes;
    }

    public void setNum_ejes(int num_ejes) {
        this.num_ejes = num_ejes;
    }

    public int getNum_salidas_emergencia() {
        return num_salidas_emergencia;
    }

    public void setNum_salidas_emergencia(int num_salidas_emergencia) {
        this.num_salidas_emergencia = num_salidas_emergencia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + num_ejes;
        result = prime * result + num_salidas_emergencia;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bus other = (Bus) obj;
        if (num_ejes != other.num_ejes)
            return false;
        if (num_salidas_emergencia != other.num_salidas_emergencia)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Bus \nNumero de pasajeros: " + num_pasajeros + "\nNumero de puertas: " + num_Puertas + "\nCapacidad del maletero: " + cap_Maletero + "\nAire acondicionado: " + aire_Acondicionado + "\nCamara de reversa: " + cam_Reversa + "Tiene velocidad de crucero?: "+ vel_Crucero + ", num_Bolsas=" + num_Bolsas + ", abs=" + abs + ", num_ejes=" + num_ejes + "\nCantidad de salidas de emergencia=" + num_salidas_emergencia;
    }



}
