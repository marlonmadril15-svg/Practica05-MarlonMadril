package controlador;

import java.io.IOException;
import java.util.ArrayList;
import modelo.GestorArchivos;
import modelo.Mecanico;
import modelo.Vehiculo;
import vista.VistaConsola;

public class ControladorTaller {
	private GestorArchivos archivos;
	private VistaConsola vista;

	public ControladorTaller(GestorArchivos archivos, VistaConsola vista) {
		this.archivos = archivos;
		this.vista = vista;
	}

	public void ejecutar() throws IOException, InterruptedException {
		ArrayList<Mecanico> mecanicos = archivos.leerMecanicos();
		ArrayList<Vehiculo> vehiculos = archivos.leerVehiculos();

		if (mecanicos.size() != 10 || vehiculos.size() < 50) {
			vista.mostrarError("se necesitan 10 mecanicos y al menos 50 autos en los archivos de datos.");
			return;
		}

		for (int i = 0; i < 50; i++) {
			int indiceMecanico = i / 5;
			mecanicos.get(indiceMecanico).agregarVehiculo(vehiculos.get(i));
		}

		vista.mostrarInicio(mecanicos.size(), 50);
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		for (Mecanico mecanico : mecanicos) {
			Thread hilo = new Thread(new TareaMecanico(mecanico, vista));
			hilos.add(hilo);
			hilo.start();
		}

		for (Thread hilo : hilos)
			hilo.join();
		vista.mostrarFin();
	}
}
