import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase que procesa todos los ficheros con extensión txt, contando el número de ocurrencias de cada
 * caracter dentro todos los ficheros de texto encontrados.
 **/
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class ProcesarFicherosSec {

	private String path;
	private String extension;
	private List<File> filesList;
	private ConcurrentMap<Character, Integer> resultsMap = new ConcurrentHashMap<>();
	private long totalCaracteres = 0;
	private int totalFiles = 0;

	public long getTotalCaracteres() {
		return totalCaracteres;
	}

	public int getTotalFiles() {
		return totalFiles;
	}

	protected ProcesarFicherosSec(String path, String extension) {
		this.path = path;
		this.extension = extension;
		this.filesList = new ArrayList<>();
	}

	public void ProcesarDirectorio() {
		totalFiles = 0;
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> procesarDirectorioRecursivo(path));
		executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void procesarDirectorioRecursivo(String dirpath) {
		File file = new File(dirpath);
		File[] content = file.listFiles();
		if (content != null) {
			for (File f : content) {
				if (f.isDirectory()) {
					procesarDirectorioRecursivo(f.getAbsolutePath());
				} else {
					if (checkFile(f.getName())) {
						filesList.add(f);
						System.out.printf("%3dth Fichero de texto encontrado: %s\n", ++totalFiles, f.getAbsolutePath());
					}
				}
			}
		} else {
			System.err.printf("Directorio %s no existe.\n", file.getAbsolutePath());
		}
	}

	public void ProcesarFicheros() {
		totalCaracteres = 0;
		ExecutorService executor = Executors.newFixedThreadPool(filesList.size());
		for (File file : filesList) {
			executor.submit(() -> procesarFichero(file));
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				Thread.sleep(10); // 10 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void procesarFichero(File file) {
		try (FileReader fr = new FileReader(file)) {
			int c;
			while ((c = fr.read()) != -1) {
				char car = (char) c;
				resultsMap.merge(car, 1, Integer::sum);
				totalCaracteres++;
			}
		} catch (IOException e) {
			System.err.printf("Error lectura fichero %s.\n", file.getAbsolutePath());
			e.printStackTrace();
		}
	}

	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

	public void PrintResults() {
		long sumValues = 0;
		for (Map.Entry<Character, Integer> e : resultsMap.entrySet()) {
			System.out.println("Carácter " + e.getKey() + " -> número de ocurrencias: " + e.getValue());
			sumValues += e.getValue();
		}
		assert totalCaracteres == sumValues;
	}

	public static void main(String[] args) {
		ProcesarFicherosSec pfs = new ProcesarFicherosSec("ruta/al/directorio", ".txt");
		pfs.ProcesarDirectorio();
		pfs.ProcesarFicheros();
		pfs.PrintResults();
	}
}