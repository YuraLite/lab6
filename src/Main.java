import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void bucketSort(double[] arr) {
        int n = arr.length;
        if (n <= 0) return;


        @SuppressWarnings("unchecked")
        List<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }


        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) (arr[i] * n);

            if (bucketIndex >= n) {
                bucketIndex = n - 1;
            }
            buckets[bucketIndex].add(arr[i]);
        }


        int index = 0;
        for (int i = 0; i < n; i++) {

            Collections.sort(buckets[i]);

            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }


    public static double[] generateRandomArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Math.random();
        }
        return arr;
    }


    public static long measureAverageExecutionTime(int size, int iterations) {
        long totalDuration = 0;

        for (int i = 0; i < iterations; i++) {

            double[] arr = generateRandomArray(size);

            long startTime = System.nanoTime();
            bucketSort(arr);
            long endTime = System.nanoTime();
            totalDuration += (endTime - startTime);
        }

        return totalDuration / iterations;
    }

    public static void main(String[] args) {
        int N = 100;

        int[] sizes = { N, N * N, N * N * N };


        int iterations = 10;

        System.out.printf("%-20s | %-30s%n", "Кількість елементів", "Середній час (наносекунди)");
        System.out.println("-------------------------------------------------------");

        for (int size : sizes) {
            long avgTime = measureAverageExecutionTime(size, iterations);

            // Виводимо форматовані результати
            System.out.printf("%-20d | %-30d%n", size, avgTime);
        }
    }
}