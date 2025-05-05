import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for used for handling the Plotting, Salting, and Smoothing project
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        
        // Generate the function and save it
        String functionFilename = DataHandler.generateFunctionValues();
        
        // Salt the function data 
        DataProcessor.saltData();
        
        // Smooth the function data 
        DataProcessor.smoothData();
        
        // Plots the points for the normal, salted, and smoothed data points
        DataProcessor.plotData(functionFilename, "Original Function");
        DataProcessor.plotData("saltedValues.csv", "Salted Data");
        DataProcessor.plotData("smoothedValues.csv", "Smoothed Data");
        
        // Calculuates the statistics for the normal, salted, and smoothed data
        StatCalculator.calculateStats(functionFilename);
        StatCalculator.calculateStats("saltedValues.csv");
        StatCalculator.calculateStats("smoothedValues.csv");
    }
}
