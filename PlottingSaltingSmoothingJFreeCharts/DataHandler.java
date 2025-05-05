import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading, exporting, and generating CSV data
 */
public class DataHandler {
    public static void exportCSVFile(ArrayList<Double> xValues, ArrayList<Double> yValues, String filename) throws FileNotFoundException {
        if (xValues.size() != yValues.size()) {
            throw new IllegalArgumentException("xValues and yValues must have the same size.");
        }
        
        PrintWriter out = new PrintWriter(new File(filename));
        out.println("x,y");
        
        for (int i = 0; i < xValues.size(); i++) {
            out.println(xValues.get(i) + "," + yValues.get(i));
        }
        
        out.close();
        System.out.println("Exported to: " + filename);
    }
    
    public static String generateFunctionValues() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        
        System.out.print("Enter the number of x values to generate: ");
        int limit = Integer.parseInt(scan.nextLine());
        
        System.out.print("Enter a filename to save (with .csv): ");
        String filename = scan.nextLine();
        
        for (double x = 1; x <= limit; x++) {
            double y = Math.pow(x, 2) + 0.5 * x;
            xValues.add(x);
            yValues.add(y);
            System.out.println(x + "," + y);
        }
        
        exportCSVFile(xValues, yValues, filename);
        
        return filename;
    }
    
    public static void loadCSVFile(String filename, ArrayList<Double> xValues, ArrayList<Double> yValues) throws FileNotFoundException {
        File csvFile = new File(filename);
        Scanner fileScanner = new Scanner(csvFile);
        
        if (fileScanner.hasNextLine()) fileScanner.nextLine(); // Skip header
        
        while (fileScanner.hasNextLine()) {
            String[] parts = fileScanner.nextLine().split(",");
            if (parts.length == 2) {
                xValues.add(Double.parseDouble(parts[0]));
                yValues.add(Double.parseDouble(parts[1]));
            }
        }
        fileScanner.close();
    }
}
