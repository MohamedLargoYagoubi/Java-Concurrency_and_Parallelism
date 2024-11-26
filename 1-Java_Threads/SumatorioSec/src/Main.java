import java.text.DecimalFormat;

public class Main {
    public static final double NANOSECONDS_IN_ONE_SECOND = 1000 * 1000 * 1000;
    static final long CDefaultN = 100;
    static final int CDefaultTheads = 1;

    public static void main(String[] args)
    {
        long N = CDefaultN;
        int MThreads = CDefaultTheads;

        System.out.println("Main ThreadId: " + Thread.currentThread().getId());

        if (args.length>0)
            N = Long.parseLong(args[0]);
        else
            N = CDefaultN;

        if (args.length>1)
            MThreads = Integer.parseInt(args[1]);
        else
            MThreads = CDefaultTheads;

        long startTime = System.nanoTime();

        SumatorioSecuencial SumSec = new SumatorioSecuencial(N);
        SumSec.Calculate();

        long endTime = System.nanoTime();
        DecimalFormat df = new DecimalFormat("0.000000");
        System.out.println("Sumatorio de 1-" + N + ": "+SumSec.getResult()+" realizado con " + MThreads + " threads en "+ df.format((endTime-startTime)/NANOSECONDS_IN_ONE_SECOND) + " segs.");
    }
}