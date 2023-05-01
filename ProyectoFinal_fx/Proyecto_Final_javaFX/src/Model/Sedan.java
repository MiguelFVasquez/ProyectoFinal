package Model;

import java.io.Serializable;

public class Sedan extends Vehiculo implements Serializable{
    private int num_pasajeros;
    private int num_Puertas;
    private int cap_Maletero;
    private boolean aire_Acondicionado;
    private boolean cam_Reversa;
    private int num_Bolsas;
    private boolean abs;
    private boolean sen_Colision;
    private boolean sen_Trafico_Cruzado;
    private boolean asistente_Carril;

    public Sedan(){
    }



    public Sedan(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa, int num_Bolsas,
			boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril) {

    	super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.cap_Maletero = cap_Maletero;
		this.aire_Acondicionado = aire_Acondicionado;
		this.cam_Reversa = cam_Reversa;
		this.num_Bolsas = num_Bolsas;
		this.abs = abs;
		this.sen_Colision = sen_Colision;
		this.sen_Trafico_Cruzado = sen_Trafico_Cruzado;
		this.asistente_Carril = asistente_Carril;
	}



	public Sedan(int num_pasajeros, int num_Puertas, boolean aire_Acondicionado, boolean cam_Reversa, int num_Bolsas, boolean abs, boolean sen_Colision,
			boolean sen_Trafico_Cruzado, boolean asistente_Carril) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.aire_Acondicionado = aire_Acondicionado;
        this.cam_Reversa = cam_Reversa;
        this.num_Bolsas = num_Bolsas;
        this.abs = abs;
        this.sen_Colision = sen_Colision;
        this.sen_Trafico_Cruzado = sen_Trafico_Cruzado;
        this.asistente_Carril = asistente_Carril;
    }

    public Sedan(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado) {
		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
		// TODO Auto-generated constructor stub
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
    public boolean isSen_Colision() {
        return sen_Colision;
    }
    public void setSen_Colision(boolean sen_Colision) {
        this.sen_Colision = sen_Colision;
    }
    public boolean isSen_Trafico_Cruzado() {
        return sen_Trafico_Cruzado;
    }
    public void setSen_Trafico_Cruzado(boolean sen_Trafico_Cruzado) {
        this.sen_Trafico_Cruzado = sen_Trafico_Cruzado;
    }
    public boolean isAsistente_Carril() {
        return asistente_Carril;
    }
    public void setAsistente_Carril(boolean asistente_Carril) {
        this.asistente_Carril = asistente_Carril;
    }

    public int getCap_Maletero() {
        return cap_Maletero;
    }
    public void setCap_Maletero(int cap_Maletero) {
        this.cap_Maletero = cap_Maletero;
    }



    @Override
    public String toString() {
        return super.toString()+ "\nNumero de pasajeros: " + num_pasajeros + "\nNumero de puertas: " +
        		num_Puertas + "\nCapacidad del maletero: "+ cap_Maletero + "\nAire acondicionado: " +
        		aire_Acondicionado + "\nCamara de reversa: " + cam_Reversa + "\nNumero de bolsas de aire: " +
        		num_Bolsas + "\nAbs: " + abs + "\nSensor de colision: " + sen_Colision + "\nSensor de trafico cruzado: " +
        		sen_Trafico_Cruzado + "\nAsistente de permanencia en el carril: " + asistente_Carril;
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
        Sedan other = (Sedan) obj;
        if (num_pasajeros != other.num_pasajeros)
            return false;
        if (num_Puertas != other.num_Puertas)
            return false;
        if (cap_Maletero != other.cap_Maletero)
            return false;
        return true;
    }






}
