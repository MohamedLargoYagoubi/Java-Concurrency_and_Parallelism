import org.junit.jupiter.api.Test;


class SumatorioConcurrenteTest {

    @Test
    void TestResults()
    {
        long N;

        N=100;
        SumatorioConc.SumatorioConcurrente.Calculate(N, 1);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 2);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 4);
        System.out.println("\n");

        N=100000000L;
        SumatorioConc.SumatorioConcurrente.Calculate(N, 1);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 2);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 4);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 8);
        System.out.println("\n");

        N=1000000000L;
        SumatorioConc.SumatorioConcurrente.Calculate(N, 1);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 2);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 4);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 8);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 16);
        System.out.println("\n");

        N=4000000000L;
        SumatorioConc.SumatorioConcurrente.Calculate(N, 1);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 2);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 4);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 8);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 10);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 12);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 14);
        SumatorioConc.SumatorioConcurrente.Calculate(N, 16);
        System.out.println("\n");
    }
}