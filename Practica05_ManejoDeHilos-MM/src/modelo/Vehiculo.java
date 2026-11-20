package modelo;

import java.util.Locale;

public class Vehiculo {
	private String marca;
	private String placa;
	private double lambda;
	private double oxigeno;
	private double oxidoNitroso;
	private double dioxidoCarbono;

	public Vehiculo(String marca, String placa, double lambda, double oxigeno, double oxidoNitroso,
			double dioxidoCarbono) {
		this.marca = marca;
		this.placa = placa;
		this.lambda = lambda;
		this.oxigeno = oxigeno;
		this.oxidoNitroso = oxidoNitroso;
		this.dioxidoCarbono = dioxidoCarbono;
	}

	public String getMarca() {
		return marca;
	}

	public String getPlaca() {
		return placa;
	}

	public double getLambda() {
		return lambda;
	}

	public double getOxigeno() {
		return oxigeno;
	}

	public double getOxidoNitroso() {
		return oxidoNitroso;
	}

	public double getDioxidoCarbono() {
		return dioxidoCarbono;
	}

	public boolean fallaLambda() {
		return lambda > 1;
	}

	public boolean fallaOxigeno() {
		return oxigeno >= 2;
	}

	public boolean fallaOxidoNitroso() {
		return oxidoNitroso >= 40;
	}

	public boolean fallaDioxidoCarbono() {
		return dioxidoCarbono >= 15;
	}
}
