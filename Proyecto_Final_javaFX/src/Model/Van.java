package Model;

import java.io.Serializable;

public class Van extends Vehiculo implements Serializable {
    private int num_pasajeros;
    private int num_Puertas;
    private int cap_Maletero;
    private boolean aire_Acondicionado;
    private boolean cam_Reversa;
    private int num_Bolsas;
    private boolean abs;

    public Van(){
    }
    public Van(int num_pasajeros, int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
    		int num_Bolsas, boolean abs) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.cap_Maletero = cap_Maletero;
        this.aire_Acondicionado = aire_Acondicionado;
        this.cam_Reversa = cam_Reversa;
        this.num_Bolsas = num_Bolsas;
        this.abs = abs;
    }

    public Van(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, int num_Bolsas,
			boolean abs) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.cap_Maletero = cap_Maletero;
		this.aire_Acondicionado = aire_Acondicionado;
		this.cam_Reversa = cam_Reversa;
		this.num_Bolsas = num_Bolsas;
		this.abs = abs;
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + num_pasajeros;
        result = prime * result + num_Puertas;
        result = prime * result + cap_Maletero;
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
        Van other = (Van) obj;
        if (num_pasajeros != other.num_pasajeros)
            return false;
        if (num_Puertas != other.num_Puertas)
            return false;
        if (cap_Maletero != other.cap_Maletero)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return super.toString()+ "\nNumero de pasajeros: " + num_pasajeros + "\nNumero de puertas: " + num_Puertas + "\nCapacidad del maletero: "+ cap_Maletero + "\nAire acondicionado: " + aire_Acondicionado + "\nCamara de reversa: " + cam_Reversa + "\nNumero de bolsas de aire: " + num_Bolsas + "\nAbs: " + abs;
    }


}
