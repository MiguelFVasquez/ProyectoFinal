package Model;

import java.io.Serializable;

public class Deportivo extends Vehiculo implements Serializable {
    private int num_pasajeros;
    private int num_Puertas;
    private int num_Bolsas;
    private double num_Caballos_Fuerza;
    private double tiempo_en_100KM;

    public Deportivo(int num_pasajeros, int num_Puertas, int num_Bolsas, double num_Caballos_Fuerza,
            double tiempo_en_100KM) {
        this.num_pasajeros = num_pasajeros;
        this.num_Puertas = num_Puertas;
        this.num_Bolsas = num_Bolsas;
        this.num_Caballos_Fuerza = num_Caballos_Fuerza;
        this.tiempo_en_100KM = tiempo_en_100KM;
    }



    public Deportivo(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado) {
		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
		// TODO Auto-generated constructor stub
	}



	public Deportivo(String marca, String modelo, String cambios, double velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, int num_pasajeros,
			int num_Puertas, int num_Bolsas, double num_Caballos_Fuerza, double tiempo_en_100KM) {

		super(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado);
		this.num_pasajeros = num_pasajeros;
		this.num_Puertas = num_Puertas;
		this.num_Bolsas = num_Bolsas;
		this.num_Caballos_Fuerza = num_Caballos_Fuerza;
		this.tiempo_en_100KM = tiempo_en_100KM;
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
    public int getNum_Bolsas() {
        return num_Bolsas;
    }
    public void setNum_Bolsas(int num_Bolsas) {
        this.num_Bolsas = num_Bolsas;
    }
    public double getNum_Caballos_Fuerza() {
        return num_Caballos_Fuerza;
    }
    public void setNum_Caballos_Fuerza(double num_Caballos_Fuerza) {
        this.num_Caballos_Fuerza = num_Caballos_Fuerza;
    }
    public double getTiempo_en_100KM() {
        return tiempo_en_100KM;
    }
    public void setTiempo_en_100KM(double tiempo_en_100KM) {
        this.tiempo_en_100KM = tiempo_en_100KM;
    }
    @Override
    public String toString() {
        return super.toString()+"Deportivo \nNumero de pasajeros:" + num_pasajeros + ", \nNumero de puertas:" + num_Puertas + "\nNumero de bolsas"+ num_Bolsas + "\nCaballos de fuerza" + num_Caballos_Fuerza + "\nTiempo en el que alcanza los 100 km/h" + tiempo_en_100KM;
    }




}
