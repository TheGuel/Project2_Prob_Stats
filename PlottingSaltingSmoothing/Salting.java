import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Takes a csv file and salts the data stored
 */
public class Salting
{
    public static void saltData() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
    
        // Read data
        System.out.print("Enter a file name: ");
        File csvFile = new File(input.nextLine());
        Scanner csvScanner = new Scanner(csvFile);
        
        if (csvScanner.hasNextLine()) {
            csvScanner.nextLine();
        }
        
        while (csvScanner.hasNextLine()) {
            String line = csvScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2) {
                xValues.add(Double.parseDouble(parts[0]));
                yValues.add(Double.parseDouble(parts[1]));
            }
        }
        csvScanner.close();
    
        // Set salt range to 50% of average Y by default
        double meanY = 0;
        for (double y : yValues) {
            meanY += y;
        }
        
        meanY /= yValues.size();
        double defaultSaltRange = meanY * 0.5;
    
        System.out.print("Enter the maximum salt value (hit Enter for default " + defaultSaltRange + "): ");
        String userInput = input.nextLine();
        double saltRange = userInput.isEmpty() ? defaultSaltRange : Double.parseDouble(userInput);
    
        // The salting amount set by the user
        for (int i = 0; i < yValues.size(); i++) {
            double salt = rand.nextGaussian() * saltRange;
            double newY = yValues.get(i) + salt;
            yValues.set(i, newY);
        }
    
        DataHandler.exportCSVFile(xValues, yValues, "saltedValues.csv");
        System.out.println("Salted data exported. Open 'saltedValues.csv' in Excel to plot.");
    }
}