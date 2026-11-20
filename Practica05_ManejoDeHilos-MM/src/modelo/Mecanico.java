package modelo;

import java.util.ArrayList;
import java.util.Locale;

public class Mecanico {
	private String nombre;
	private ArrayList<Vehiculo> vehiculos;

	public Mecanico(String nombre) {
		this.nombre = nombre;
		this.vehiculos = new ArrayList<Vehiculo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarVehiculo(Vehiculo vehiculo) {
		vehiculos.add(vehiculo);
	}

	public String crearReporte() {
		StringBuilder texto = new StringBuilder();
		int erroresLambda = 0;
		int erroresOxigeno = 0;
		int erroresN2O = 0;
		int erroresCO2 = 0;

		texto.append("\nMecanico asignado: ").append(nombre).append("\n");
		texto.append("MARCA         | PLACA    | LAMBDA  | O2      | N2O     | CO2\n");
		texto.append("--------------|----------|---------|---------|---------|---------\n");

		for (Vehiculo vehiculo : vehiculos) {
			boolean fallaLambda = vehiculo.fallaLambda();
			boolean fallaO2 = vehiculo.fallaOxigeno();
			boolean fallaN2O = vehiculo.fallaOxidoNitroso();
			boolean fallaCO2 = vehiculo.fallaDioxidoCarbono();

			if (fallaLambda)
				erroresLambda++;
			if (fallaO2)
				erroresOxigeno++;
			if (fallaN2O)
				erroresN2O++;
			if (fallaCO2)
				erroresCO2++;

			texto.append(String.format(Locale.US, "%-13s | %-8s | %-7s | %-7s | %-7s | %-7s\n", vehiculo.getMarca(),
					vehiculo.getPlaca(), valor(vehiculo.getLambda(), fallaLambda),
					valor(vehiculo.getOxigeno(), fallaO2), valor(vehiculo.getOxidoNitroso(), fallaN2O),
					valor(vehiculo.getDioxidoCarbono(), fallaCO2)));
		}

		int cantidad = vehiculos.size();
		double porcLambda = porcentaje(erroresLambda, cantidad);
		double porcO2 = porcentaje(erroresOxigeno, cantidad);
		double porcN2O = porcentaje(erroresN2O, cantidad);
		double porcCO2 = porcentaje(erroresCO2, cantidad);
		int erroresTotales = erroresLambda + erroresOxigeno + erroresN2O + erroresCO2;
		double porcGeneral = porcentaje(erroresTotales, cantidad * 4);

		texto.append("\nErrores por gas (tolerancia maxima):\n");
		texto.append(String.format(Locale.US,
				"Lambda: %.0f%% (70%%) | O2: %.0f%% (30%%) | N2O: %.0f%% (80%%) | CO2: %.0f%% (50%%)\n", porcLambda,
				porcO2, porcN2O, porcCO2));
		texto.append(String.format(Locale.US, "Porcentaje general de mediciones con error: %.0f%%\n", porcGeneral));

		boolean aprobado = porcLambda <= 70 && porcO2 <= 30 && porcN2O <= 80 && porcCO2 <= 50;
		if (aprobado) {
			texto.append("Resultado del lote: Pasa en Primera Revision\n");
		} else {
			texto.append("Resultado del lote: Segunda Revision Asignada\n");
		}
		return texto.toString();
	}

	private String valor(double numero, boolean tieneError) {
		String marca = tieneError ? "!" : "";
		return String.format(Locale.US, "%.2f%s", numero, marca);
	}

	private double porcentaje(int errores, int total) {
		if (total == 0)
			return 0;
		return errores * 100.0 / total;
	}
}
