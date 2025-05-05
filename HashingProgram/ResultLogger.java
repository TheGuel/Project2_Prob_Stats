import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Takes the hashmap results and operations into a CSV file for plotting purposes
 */

public class ResultLogger {
    private PrintWriter writer;

    // The declaration of the CSV file setup
    public ResultLogger(String filename) throws IOException {
        writer = new PrintWriter(new FileWriter(filename));
        writer.println("ItemCount,CollisionCount,ResizeCount,ElapsedTime(ms),MemoryUsed(KB),LoadFactor");
    }

    // The format used in Excel to present the following metrics
    public void log(int itemCount, int collisions, int resizes, double timeMs, double memoryUsed, double loadFactor) {
        writer.printf("%d,%d,%d,%.4f,%.2f,%.4f\n", itemCount, collisions, resizes, timeMs, memoryUsed, loadFactor);
    }

    // Closes the csv file for editing
    public void close() {
        writer.close();
    }
}
