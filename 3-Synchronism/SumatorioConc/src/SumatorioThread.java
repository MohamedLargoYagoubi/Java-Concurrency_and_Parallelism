public class SumatorioThread implements Runnable {
    private long 	Begin;
    private long 	End;

    public void setBegin(long i) {
        Begin = i;
    }

    public long getBegin() {
        return Begin;
    }

    public void setEnd(long i) {
        End = i;
    }

    public long getEnd() {
        return End;
    }


    private void Add(long num)
    {
        SumatorioConc.SumatorioConcurrente.addResult(num);
        SumatorioConc.SumatorioConcurrente.incNSums();
    }

    @Override
    public void run()
    {
        long x;

        for(x=Begin;x<End;x++)
            Add(x);
    }
}
