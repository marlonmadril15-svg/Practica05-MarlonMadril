package controlador;

import java.util.Random;
import modelo.Mecanico;
import vista.VistaConsola;

public class TareaMecanico implements Runnable {
	private Mecanico mecanico;
	private VistaConsola vista;

	public TareaMecanico(Mecanico mecanico, VistaConsola vista) {
		this.mecanico = mecanico;
		this.vista = vista;
	}

	@Override
	public void run() {
		try {
			int demora = new Random().nextInt(3001);
			Thread.sleep(demora);
			vista.mostrarReporte(mecanico.crearReporte());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			vista.mostrarError("se interrumpio el reporte de " + mecanico.getNombre());
		}
	}
}
