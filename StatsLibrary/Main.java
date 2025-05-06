/**
 * The main class, which tests out the workings of the statistics formulas
 * Goes through hypothetical test scenarios to confirm it works
 */
public class Main {
    public static void main(String[] args) {
        int y = 5;
        double lambda = 7.5;
        double k = 2.5;

        double p = StatsLibraryFunctions.poissonProbability(y, lambda);
        double expected = StatsLibraryFunctions.expectedPoisson(lambda);
        double variance = StatsLibraryFunctions.variancePoisson(lambda);
        double tchebysheff = StatsLibraryFunctions.tchebysheffLowerBound(k);

        System.out.println("Poisson Distribution = Poisson P(Y = " + y + ") = " + p);
        System.out.println("Expected value for Poisson (E[Y]) = " + expected);
        System.out.println("Variance for Poisson(Var[Y]) = " + variance);
        System.out.println("Tchebysheff lower bound for k = " + k + ": " + tchebysheff);
    }
}

