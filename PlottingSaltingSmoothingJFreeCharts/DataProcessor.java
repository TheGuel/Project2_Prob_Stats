import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JFrame;

/**
 * A class intended to handle smoothing, salting, and plotting the data
 */
public class DataProcessor {
    public static void saltData() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        
        System.out.print("Enter the file to salt: ");
        String filename = input.nextLine();
        
        DataHandler.loadCSVFile(filename, xValues, yValues);
        
        double meanY = 0;
        for (double y : yValues) {
            meanY += y;
        }
        meanY /= yValues.size();
        double defaultSaltRange = meanY * 0.5;
        
        System.out.print("Enter the preferred salt value (hit Enter for default " + defaultSaltRange + "): ");
        String userInput = input.nextLine();
        double saltRange = userInput.isEmpty() ? defaultSaltRange : Double.parseDouble(userInput);
        
        Random rand = new Random();
        for (int i = 0; i < yValues.size(); i++) {
            double salt = rand.nextGaussian() * saltRange;
            yValues.set(i, yValues.get(i) + salt);
        }
        
        DataHandler.exportCSVFile(xValues, yValues, "saltedValues.csv");
    }
    
    public static void smoothData() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        
        System.out.print("Enter the file to smooth: ");
        String filename = input.nextLine();
        
        DataHandler.loadCSVFile(filename, xValues, yValues);
        
        System.out.print("Enter the preferred window size (Ex. 5 means +/-5): ");
        int windowSize = Integer.parseInt(input.nextLine());
        
        ArrayList<Double> smoothedY = new ArrayList<>();
        
        for (int i = 0; i < yValues.size(); i++) {
            double weightedSum = 0;
            double totalWeight = 0;
            
            for (int j = -windowSize; j <= windowSize; j++) {
                int idx = i + j;
                if (idx >= 0 && idx < yValues.size()) {
                    int weight = windowSize + 1 - Math.abs(j);
                    weightedSum += yValues.get(idx) * weight;
                    totalWeight += weight;
                }
            }
            smoothedY.add(weightedSum / totalWeight);
        }
        
        DataHandler.exportCSVFile(xValues, smoothedY, "smoothedValues.csv");
    }
    
    public static void plotData(String filename, String chartTitle) throws FileNotFoundException {
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        
        DataHandler.loadCSVFile(filename, xValues, yValues);
        
        XYSeries series = new XYSeries(chartTitle);
        for (int i = 0; i < xValues.size(); i++) {
            series.add(xValues.get(i), yValues.get(i));
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
            chartTitle,
            "X", "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );
        
        JFrame frame = new JFrame(chartTitle);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
