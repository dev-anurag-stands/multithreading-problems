package Que4_Image_Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ImageProcessor {
    public static void main(String[] args) {
        // Step 1: Create a fixed thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Step 2: Create a list to hold Future results
        List<Future<String>> futureList = new ArrayList<>();

        // Step 3: Record start time
        long startTime = System.currentTimeMillis();

        // Step 4: Submit 50 Callable tasks to the executor
        for (int i = 1; i <= 50; i++) {
            final int imageId = i;
            Callable<String> task = () -> {
                // Simulate image processing by sleeping for 100ms
                Thread.sleep(100);
                return "Image " + imageId + " processed";
            };

            Future<String> future = executor.submit(task);
            futureList.add(future);
        }

        // Step 5: Collect and print results in order
        for (Future<String> future : futureList) {
            try {
                String result = future.get(); // blocks if not done
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Step 6: Record end time and calculate total time
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + "ms");

        // Step 7: Shutdown executor
        executor.shutdown();
    }
}
