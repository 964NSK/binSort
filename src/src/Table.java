import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class Table {
    public static int size;
    public static int index = -1;

    public static void main(String[] args) throws Exception {
        File file = new File("infoTable.txt");
        PrintWriter pw = new PrintWriter(file);
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.print("Size of Table: ");
        int size = in.nextInt();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int value = (int) (Math.random() * 10000);
                matrix[i][j] = value;
            }
        }

        int[] temp = new int[size * size];
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                temp[k] = matrix[i][j];
                k++;
            }
        }
        sort(temp);
        k = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                k++;
                matrix[i][j] = temp[k];
                if (size < 100) {
                    System.out.print(" " + matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
        writeTableToFile(matrix);
        System.out.println();
        System.out.print("Enter the value: ");

        int val = in.nextInt();
        long time = System.nanoTime();
        binarySearch(temp, val, 0, temp.length - 1);
        if (index == -1) {
            System.out.println("Number not found");
            pw.println("Number not found");
        } else {
            System.out.printf("Position in the table = %d%n", binarySearch(temp, val, 0, temp.length - 1) + 1);
            pw.printf("Position in the table = %d%n", binarySearch(temp, val, 0, temp.length - 1) + 1);
        }
        time = System.nanoTime() - time;
        System.out.printf("Time: %,9.3f ms\n", time / 1_000_000.0);
        pw.printf("Time: %,9.3f ms\n", time / 1_000_000.0);
        pw.printf("Amount of numbers = " + size*size);
        System.out.println("Amount of numbers = " + size*size);
        pw.close();


    }

    public static void sort(int[] a) {
        boolean unsort = true;
        int tmp;

        while (unsort) {
            unsort = false;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    unsort = true;
                }
            }
        }

    }

    private static int binarySearch(int[] sortedArray, int valueToFind, int low, int high) {

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < valueToFind) {
                low = mid + 1;
            } else if (sortedArray[mid] > valueToFind) {
                high = mid - 1;
            } else if (sortedArray[mid] == valueToFind) {
                index = mid;
                break;
            }
        }
        return index;
    }


    public static void writeTableToFile(int[][] a) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Table.txt")));

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    writer.write(a[i][j] + " ");
                }
                writer.newLine();
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
