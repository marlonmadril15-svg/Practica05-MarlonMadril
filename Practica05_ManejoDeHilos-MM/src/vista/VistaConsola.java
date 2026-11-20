package vista;

import modelo.Mecanico;

public class VistaConsola {
	public void mostrarInicio(int mecanicos, int vehiculos) {
		System.out.println(" REPORTE DE REVISION VEHICULAR ");
		System.out.println("Se asignaron " + vehiculos + " autos a " + mecanicos + " mecanicos.");
		System.out.println("Los reportes aparecen a medida que terminan los hilos (maximo 3 segundos).\n");
	}

	public synchronized void mostrarReporte(String reporte) {
		System.out.println(reporte);
	}

	public void mostrarFin() {
		System.out.println("Proceso terminado. Todos los lotes fueron revisados.");
	}

	public void mostrarError(String mensaje) {
		System.out.println("No se pudo iniciar el reporte: " + mensaje);
	}
}
