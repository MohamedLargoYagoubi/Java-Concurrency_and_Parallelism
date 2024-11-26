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
public class ProcesarFicherosSec {

	// Path al directorio de entrada que contiene los ficheros que se van a prcoesar.
	private String path;
	
	// Extensión de los ficheros a procesar
	private String extension;
	
	// Lista ne donde se guardas los ficheros a procesar
	private List<File> filesList;
	
	// Mapa de resultados, en donde la clave es cada uno de los carácteres encontrados en los ficheros
	// y el valor el número de ocurrencias
	private Map<Character,Integer> resultsMap = new TreeMap<>();
	private long TotalCaracteres = 0;
	private int TotalFiles = 0;

	public long getTotalCaracteres() {
		return TotalCaracteres;
	}

	public int getTotalFiles() {
		return TotalFiles;
	}

	//Constructor de la clase
	protected ProcesarFicherosSec(String path, String extension)
	{
		this.path=path;
		this.extension=extension;
		filesList=new ArrayList<>();
	}

	public void ProcesarDirectorio()
	{
		TotalFiles = 0;
		this.ProcesarDirectorioRecursivo(path);
	}

	// Procesamiento recursivo del directorio para buscar los ficheros de texto, almacenandolo en la lista fileList
	public void ProcesarDirectorioRecursivo(String dirpath)
	{
		File file=new File(dirpath);
		File content[] = file.listFiles();
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					// Si es un directorio, procesarlo recursivamente.
					ProcesarDirectorioRecursivo(content[i].getAbsolutePath());
				}
				else {
					// Si es un fichero de texto, añadirlo a la lista para su posterior procesamiento.
					if (checkFile(content[i].getName())){
						filesList.add(content[i]);
						System.out.printf("%3dth Fichero de texto encontrado: %s\n", ++TotalFiles, content[i].getAbsolutePath());
					}
				}
			}
		}
		else
			System.err.printf("Directorio %s no existe.\n",file.getAbsolutePath());
	}

	// Método para procesar todos los ficheros de la lista. Para cada fichero se lee los caracteres y para cada uno
	// de ellos se incrementa su entrada en el Mapa
	public void ProcesarFicheros()
	{
		TotalCaracteres = 0;
		// Procesar todos los ficheros de texto encontrados
		for (File file : filesList)
		{
			FileReader fr = null;
			try {
				// Crear un objeto FileReader con el path del fichero que toca procesar.
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {
				System.err.printf("Fichero %s no encontrado.\n",file.getAbsolutePath());
				e.printStackTrace();
			}

			// Leer y procesar el fichero
			int c;
			Character car = 'a';
			// Iterar por todps los caracteres del fichero
			while (true)
			{
				try {
					if (!((c = fr.read()) != -1)) break;

					car = (char)c;
					TotalCaracteres++;
					// Si no existe el carácter como clave en el Mapa, añadirla con valor de 1
					Integer value = resultsMap.putIfAbsent(car, 1);
					if (value != null) {
						// Si existe, incrementar el número de ocurrencias para este carácter.
						resultsMap.put(car, value+1);
					}
					// Imprimir contenido
					// System.out.print((char)c);

				} catch (IOException e) {
					System.err.printf("Error lectura fichero %s.\n",file.getAbsolutePath());
					e.printStackTrace();
				}
			}
		}
	}

	// Verificar si la extensión del fichero coincide con la extensiones buscadas (txt)
	private boolean checkFile (String name){
		if (name.endsWith(extension)) {
			return true;
		}
		return false;
	}

	// Imprime los resultados de ocurrencias para cada carácter encontrado, a partir del mapa
	public void PrintResults()
	{
		long sumValues = 0;
		for (Map.Entry<Character,Integer> e : resultsMap.entrySet()) {
			System.out.println("Carácter "+e.getKey()+ " -> número de ocurrencias: "+ e.getValue());
			sumValues += e.getValue();
		}
		// Comprobar que el resultado sea correcto.
		assertEquals(TotalCaracteres, sumValues);
	}
}