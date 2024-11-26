import java.time.Duration;
import java.time.Instant;

/**
 * Main class of the example
 */
public class Main {

	/**
	 * Main method of the example
	*/
	public static void main(String[] args)
	{
		String inputPath = "./Input";
		if (args.length>0)
			inputPath = args[0];

		Instant start = Instant.now();

		// Crear la clase de procesamiento para los ficheros
		ProcesarFicherosSec fileProcessing=new ProcesarFicherosSec(inputPath,"txt");
		// Procesar el directorio de entrada para buscar ficheros de texto.
		fileProcessing.ProcesarDirectorio();
		// Procesar ficheros de texto.
		fileProcessing.ProcesarFicheros();

		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();  //in millis

		// Mostrar resultados de ocurrencias para cada carácter encontrado.
		fileProcessing.PrintResults();

		System.out.printf("[Procesamiento del directorio %s con %d ficheros y %d carácteres] Total execution Time: %f secs.\n", inputPath, fileProcessing.getTotalFiles(), fileProcessing.getTotalCaracteres() , timeElapsed/1000.0);
	}

}
