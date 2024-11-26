import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Main
{
    static int N=100;      // Number of elements to sort.

    // Driver code
    public static void main(String args[])
    {
        if (args.length>0)
            N=Integer.parseInt(args[0]);

        // Init numbers array randomly.
        int[] Array = new int[N];
        generateArray(Array);

        MergeSort merge_sec = new MergeSort();

        if (N<=200) {
            System.out.printf("[SEQUENTIAL MERGE SORT %d] Original array: ", N);
            merge_sec.printArray(Array);
        }
        Instant start = Instant.now();

        merge_sec.sort(Array, 0, Array.length - 1);

        Instant finish = Instant.now();

        System.out.printf("[SEQUENTIAL MERGE SORT %d] Result   array: ", N);
        merge_sec.printArray(Arrays.copyOfRange(Array, 0, 200));

        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.printf("[SEQUENTIAL MERGE SORT %d] Total execution time: %.3f secs.\n", N, timeElapsed/1000.0);
    }

    static void generateArray(int randomArray[])
    {
        // Create a Random object
        Random random = new Random(10);

        // Assign random values to the array
        for (int i = 0; i < N; i++) {
            // Generate a random integer
            // between INT_MIN and INT_MAX
            randomArray[i] = random.nextInt(0,N);
        }
    }
}