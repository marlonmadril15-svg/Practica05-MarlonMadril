package controlador;

import modelo.GestorArchivos;
import vista.VistaConsola;

public class Main {
	public static void main(String[] args) {
		VistaConsola vista = new VistaConsola();
		try {
			GestorArchivos archivos = new GestorArchivos();
			ControladorTaller controlador = new ControladorTaller(archivos, vista);
			controlador.ejecutar();
		} catch (Exception e) {
			vista.mostrarError(e.getMessage());
		}
	}
}
