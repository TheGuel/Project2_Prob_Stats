/**
 * A collection of the statistics-based formulas that come after Chapter 3.7
 * The textbook in reference is Mathematical Statistics by Wackerly
 */
public class StatsLibraryFunctions
{
    // The formula for calculating Poisson Distribution
    public static double poissonProbability(int y, double lambda) {
        if (y < 0 || lambda <= 0) {
            return 0;    
        }
        return Math.pow(lambda, y) * Math.exp(-lambda) / factorial(y);
    }
    
    // The formula for calculating an expected value for Poisson
    public static double expectedPoisson(double lambda) {
        return lambda;
    }

    // The formula for caculating the variance value for Poisson
    public static double variancePoisson(double lambda) {
        return lambda;
    }

    // The formula used for calculating the lower bound for Tchebysheff's
    public static double tchebysheffLowerBound(double k) {
        if (k <= 0) {
            return 0;
        }
        return 1 - (1 / (k * k));
    }
    
    // A hidden operation used to replicate the workings of "!"
    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
