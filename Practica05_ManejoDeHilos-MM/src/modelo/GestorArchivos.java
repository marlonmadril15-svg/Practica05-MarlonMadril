package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GestorArchivos {
	private File archivoMecanicos = new File("datos/mecánicos.txt");
	private File archivoVehiculos = new File("datos/vehículos.txt");

	public GestorArchivos() throws IOException {
		File carpeta = new File("datos");
		if (!carpeta.exists())
			carpeta.mkdirs();
		crearMecanicosSiHaceFalta();
		crearVehiculosSiHaceFalta();
	}

	private void crearMecanicosSiHaceFalta() throws IOException {
		if (archivoMecanicos.exists())
			return;
		String[] nombres = { "Carlos Ruiz", "Maria Lopez", "Jose Vera", "Ana Torres", "Luis Mora", "Daniela Paz",
				"Pedro Leon", "Sofia Rios", "Miguel Cruz", "Elena Paredes" };
		try (PrintWriter salida = new PrintWriter(new FileWriter(archivoMecanicos))) {
			for (String nombre : nombres)
				salida.println(nombre);
		}
	}

	private void crearVehiculosSiHaceFalta() throws IOException {
		if (archivoVehiculos.exists())
			return;
		String[] marcas = { "Toyota", "Chevrolet", "Kia", "Hyundai", "Nissan", "Mazda", "Ford", "Renault", "Volkswagen",
				"Suzuki" };
		Random azar = new Random(2026);
		try (PrintWriter salida = new PrintWriter(new FileWriter(archivoVehiculos))) {
			for (int i = 0; i < 50; i++) {
				String marca = marcas[i % marcas.length];
				String placa = String.format("ABC-%04d", 1000 + i);
				double lambda = azar.nextDouble() * 2.5;
				double oxigeno = 0.5 + azar.nextDouble() * 3.0;
				double n2o = 15 + azar.nextDouble() * 55;
				double co2 = 8 + azar.nextDouble() * 20;
				salida.printf(Locale.US, "%s;%s;%.2f;%.2f;%.2f;%.2f%n", marca, placa, lambda, oxigeno, n2o, co2);
			}
		}
	}

	public ArrayList<Mecanico> leerMecanicos() throws IOException {
		ArrayList<Mecanico> mecanicos = new ArrayList<Mecanico>();
		try (Scanner lector = new Scanner(archivoMecanicos)) {
			while (lector.hasNextLine()) {
				String nombre = lector.nextLine().trim();
				if (!nombre.isEmpty())
					mecanicos.add(new Mecanico(nombre));
			}
		}
		return mecanicos;
	}

	public ArrayList<Vehiculo> leerVehiculos() throws IOException {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try (Scanner lector = new Scanner(archivoVehiculos)) {
			while (lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				if (linea.isEmpty())
					continue;
				String[] datos = linea.split(";");
				if (datos.length != 6)
					continue;
				try {
					vehiculos.add(new Vehiculo(datos[0], datos[1], Double.parseDouble(datos[2]),
							Double.parseDouble(datos[3]), Double.parseDouble(datos[4]), Double.parseDouble(datos[5])));
				} catch (NumberFormatException e) {
				}
			}
		}
		return vehiculos;
	}
}
