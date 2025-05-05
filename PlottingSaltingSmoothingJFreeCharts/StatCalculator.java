import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Calculates basic statistics for a the given CSV file the user puts down
 */
public class StatCalculator {
    public static void calculateStats(String filename) throws FileNotFoundException {
        ArrayList<Double> yValues = new ArrayList<>();
        
        File csvFile = new File(filename);
        Scanner fileScanner = new Scanner(csvFile);
        
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }
        
        while (fileScanner.hasNextLine()) {
            String[] parts = fileScanner.nextLine().split(",");
            if (parts.length == 2) {
                yValues.add(Double.parseDouble(parts[1]));
            }
        }
        fileScanner.close();
        
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double y : yValues) {
            stats.addValue(y);
        }
        
        System.out.println("Statistics for " + filename + ":");
        System.out.printf("Mean: %.2f\n", stats.getMean());
        System.out.printf("Standard Deviation: %.2f\n", stats.getStandardDeviation());
        System.out.println("-----------------------------");
    }
}
