import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Smooths the data from csv file so that it is readily available for graphing
 */
public class Smoother
{
    public static void smoothData() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();

        System.out.print("Enter the window size (Ex. 5 means +/-5): ");
        int windowSize = Integer.parseInt(input.nextLine());

        System.out.print("Enter the file name you'd like to smooth: ");
        File csvFile = new File(input.nextLine());

        Scanner csvScanner = new Scanner(csvFile);
        if (csvScanner.hasNextLine()) csvScanner.nextLine();

        while (csvScanner.hasNextLine()) {
            String line = csvScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2) {
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                xValues.add(x);
                yValues.add(y);
            }
        }
        csvScanner.close();

        ArrayList<Double> smoothedY = new ArrayList<>();

        // Weighted Moving Average Smoothing
        for (int i = 0; i < yValues.size(); i++) {
            double weightedSum = 0.0;
            double totalWeight = 0.0;

            for (int j = -windowSize; j <= windowSize; j++) {
                int idx = i + j;
                if (idx < 0 || idx >= yValues.size()) continue; // Skip out-of-bounds

                // Weight: center gets max weight, edges get 1, in-between linearly
                int weight = windowSize + 1 - Math.abs(j);
                weightedSum += yValues.get(idx) * weight;
                totalWeight += weight;
            }
            smoothedY.add(weightedSum / totalWeight);
        }

        DataHandler.exportCSVFile(xValues, smoothedY, "smoothedValues.csv");
        System.out.println("Smoothed data exported to 'smoothedValues.csv'. Open in Excel to visualize.");
    }
}