import static org.junit.jupiter.api.Assertions.*;

public class SumatorioSecuencial {

    private long Begin;

    private long End;
    private long Result;

    private long NSums;
    public SumatorioSecuencial(long end) { this.Begin=1; this.End=end; }
    public SumatorioSecuencial(long begin, long end) { this.Begin=begin; this.End=end; }

    public long getBegin() {
        return Begin;
    }
    public void setBegin(long begin) {
        Begin = begin;
    }
    public long getEnd() {
        return End;
    }
    public void setEnd(long end) {
        End = end;
    }
    public long getResult() {
        return Result;
    }
    public void setResult(long result) {
        Result = result;
    }
    public long getNSums() {
        return NSums;
    }
    public void setNSums(long NSums) {
        this.NSums = NSums;
    }

    private void Add(long num)
    {
        Result+=num;
        NSums++;
    }

    private long SumatorioFormula(){
        return( (long) (End*((End+1)/2.0)) - (long) ((Begin-1)*(Begin/2.0)) );
    }

    public long Calculate()
    {
        setResult(0);
        setNSums(0);
        for(long x=Begin; x<=End; Add(x),x++);

        assertEquals(Result, SumatorioFormula());
        assertEquals(NSums, End-Begin+1);

        return Result;
    }

}
