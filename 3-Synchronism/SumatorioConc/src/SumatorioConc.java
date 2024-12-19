import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumatorioConc {
    public static final double NANOSECONDS_IN_ONE_SECOND = 1000 * 1000 * 1000;
    static final long CDefaultN = 100;
    static final int CDefaultTheads = 1;

    public static void main(String[] args) {
        long N = CDefaultN;
        int MThreads = CDefaultTheads;

        System.out.println("Main ThreadId: " + Thread.currentThread().getId());

        if (args.length < 1) {
            show_help("Sumatorio: Missing Arguments");
            return;
        }

        if (args.length > 2) {
            show_help("Sumatorio: Too many Arguments");
            return;
        }

        if (args.length > 0)
            N = Long.parseLong(args[0]);
        else
            N = CDefaultN;

        if (args.length > 1)
            MThreads = Integer.parseInt(args[1]);
        else
            MThreads = CDefaultTheads;

        SumatorioConcurrente.Calculate(N, MThreads);
    }

    static public void show_help(String err_message) {
        System.out.println(err_message);
        System.out.println("Usage: Sumatorio <N> [<Number_threads>]");
    }

    public static class SumatorioConcurrente {
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_RESET = "\u001B[0m";
        private static long Begin;
        private static long End;
        private static AtomicLong Result = new AtomicLong(0);
        private static AtomicLong NSums = new AtomicLong(0);

        public static long getBegin() {
            return Begin;
        }

        public static void setBegin(long begin) {
            Begin = begin;
        }

        public static long getEnd() {
            return End;
        }

        public static void setEnd(long end) {
            End = end;
        }

        public static long getResult() {
            return Result.get();
        }

        public static void addResult(long num) {
            Result.addAndGet(num);
        }

        public static long getNSums() {
            return NSums.get();
        }

        public static void incNSums() {
            NSums.incrementAndGet();
        }

        private static long SumatorioFormula() {
            return (long) (End * ((End + 1) / 2.0)) - (long) ((Begin - 1) * (Begin / 2.0));
        }

        public static long Calculate(long N, int MThreads) {
            Result.set(0);
            NSums.set(0);
            setBegin(1);
            setEnd(N);

            long startTime = System.nanoTime();

            LaunchThreads(MThreads);

            long endTime = System.nanoTime();
            DecimalFormat df = new DecimalFormat("0.000000");
            System.out.println("Sumatorio de " + Begin + "-" + End + ": " + getResult() + " realizado con " + MThreads + " threads en " + df.format((endTime - startTime) / SumatorioConc.NANOSECONDS_IN_ONE_SECOND) + " segs.");

            // Verificar que el resultado sea correcto.
            try {
                assertEquals(SumatorioFormula(), getResult());
            } catch (AssertionError e) {
                System.out.println(ANSI_RED + e.getMessage() + " " + ANSI_RESET);
            }
            // Verificar números sumados es correcto.
            try {
                assertEquals(End - Begin + 1, getNSums());
            } catch (AssertionError e) {
                System.out.println(ANSI_RED + e.getMessage() + " " + ANSI_RESET);
            }

            return Result.get();
        }

        public static void LaunchThreads(int MThreads) {
            Thread Hilos[];
            SumatorioThread Trabajo[];

            Hilos = new Thread[MThreads];
            Trabajo = new SumatorioThread[MThreads];
            for (int h = 0; h < MThreads; h++) {
                Trabajo[h] = new SumatorioThread();

                if (h == 0)
                    Trabajo[h].setBegin(Begin);
                else
                    Trabajo[h].setBegin(Trabajo[h - 1].getEnd());

                if (h == (MThreads - 1))
                    Trabajo[h].setEnd(End + 1);
                else
                    Trabajo[h].setEnd(Trabajo[h].getBegin() + ((End - Trabajo[h].getBegin()) + 1) / (MThreads - h));

                Hilos[h] = new Thread(Trabajo[h]);
                Hilos[h].start();
            }

            /* Esperar finalización Hilos */
            for (int h = 0; h < MThreads; h++) {
                try {
                    Hilos[h].join();
                } catch (InterruptedException e) {
                    show_error("[SumatorioJavaThreads::LaunchThreads] Recibida interrupción ( " + e + ").");
                }
            }
        }

        public static void show_error(String err_message) {
            System.err.println(err_message);
        }
    }
}