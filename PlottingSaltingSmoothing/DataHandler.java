import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Handles the reading and editing for csv files
 */
public class DataHandler
{
    public static void exportCSVFile(ArrayList<Double> xValues, ArrayList<Double> yValues, String filename) throws FileNotFoundException {
        if (xValues.size() != yValues.size()) {
            throw new IllegalArgumentException("xValues and yValues must be equal to each other");
        }
        
        // Allowing for edits to be made on file & printing the header "x,y"
        PrintWriter out = new PrintWriter(new File(filename));
        out.println("x,y");
        
        // Goes line by line to assign x & y to the .csv file within the size of xValues array
        for (int i = 0; i < xValues.size(); i++) {
            out.println(xValues.get(i) + "," + yValues.get(i));
        }
        
        // Shuts out any potential text input for the .csv file
        out.close();
        
        System.out.println("Exported to: " + filename);
    }
    
    public static void generateFunctionValues() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        
        // The number of times you'd like x to run (E.g. 3 means 3 lines of values)
        System.out.print("Enter the amount of times you'd like x to run: ");
        int theLimit = Integer.parseInt(scan.nextLine());
        
        // Creating the csv file and allowing edits to be made w/ PrintWriter
        System.out.print("Name for your csv file \n(Be sure to put .csv at the end): ");
        String fileName = scan.nextLine();
        
        System.out.println("X,Y");
        
        // Loop for printing and adding values to xValues and yValues, as well as the csv file
        for (double x = 1; x <= theLimit; x++) {
            double y = Math.pow(x,2) + (0.5 * x);
            xValues.add(x);
            yValues.add(y);
            
            System.out.println(x + "," + y);
        }
        
        exportCSVFile(xValues, yValues, fileName);
    }
    
    public static void loadCSVFile(String filename, ArrayList<Double> xValues, ArrayList<Double> yValues) {
        File csvFile = new File(filename);
        Scanner input = new Scanner(System.in);
        
        // Skips the header in csvFile
        if (input.hasNextLine()) input.nextLine();
        
        // Splits the line of focus like this "x,y" by inputting the values into the "parts" array
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split(",");
            
            if (parts.length == 2) {
                xValues.add(Double.parseDouble(parts[0]));
                yValues.add(Double.parseDouble(parts[1]));
            }
        }
        
        // Closes the scanner
        input.close();
    }
}
