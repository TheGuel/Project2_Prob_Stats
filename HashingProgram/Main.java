import java.io.IOException;

/**
 * Handles the methods written up in MiguelSimpleHashMap
 * Tests to see the efficiency of the methods and their counts such as ResizeCount
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MiguelSimpleHashMap<String> map = new MiguelSimpleHashMap<>(10);
        ResultLogger tracker = new ResultLogger("myhashmap_stats.csv");

        int loggedInserts = 100;           // Log every 100 inserts
        int totalLogsCount = 2500;    // Total number of items to insert into hashmap

        for (int i = 1; i <= totalLogsCount; i++) {
            String value = "item" + i;

            long start = System.nanoTime();
            for (int j = 0; j < 100; j++) {
                String innerValue = "item" + (i + j);
                map.add(innerValue);
            }
            long end = System.nanoTime();

            double elapsedMsTime = (end - start) / 1_000_000.0;

            if (i % loggedInserts == 0) {
                long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                double memoryKB = memoryUsed / 1024.0;
                double loadFactor = (double) map.getSize() / map.getCapacity();
                
                tracker.log(i, map.getCollisionCount(), map.getResizeCount(), elapsedMsTime, memoryKB, loadFactor);
            }
        }
        tracker.close();
        
        System.out.println("Data logged to myhashmap_stats.csv. Open it in Excel to create your graph.");
    }
}