import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SumatorioSecuencialTest {

    @Test
    void TestResults() {

        SumatorioSecuencial SumSec = new SumatorioSecuencial(100);
        SumSec.Calculate();

        SumSec = new SumatorioSecuencial(99L,100L);
        SumSec.Calculate();
        System.out.println("Sumatorio "+SumSec.getBegin()+"-"+SumSec.getEnd()+": "+SumSec.getResult());

        SumSec = new SumatorioSecuencial(100000000L);
        SumSec.Calculate();
        System.out.println("Sumatorio "+SumSec.getBegin()+"-"+SumSec.getEnd()+": "+SumSec.getResult());

        SumSec = new SumatorioSecuencial(1000000000L);
        SumSec.Calculate();
        System.out.println("Sumatorio "+SumSec.getBegin()+"-"+SumSec.getEnd()+": "+SumSec.getResult());

        SumSec = new SumatorioSecuencial(4000000000L);
        SumSec.Calculate();
        System.out.println("Sumatorio "+SumSec.getBegin()+"-"+SumSec.getEnd()+": "+SumSec.getResult());
    }
}