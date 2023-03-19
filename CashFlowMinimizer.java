import java.util.Arrays;
import java.util.Scanner;

public class CashFlowMinimizer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of cash flows: ");
        int numFlows = input.nextInt();

        int[] flows = new int[numFlows];
        for (int i = 0; i < numFlows; i++) {
            System.out.print("Enter cash flow " + (i + 1) + ": ");
            flows[i] = input.nextInt();
        }

        minimizeCashFlows(flows);
    }

    private static void minimizeCashFlows(int[] flows) {
        int numFlows = flows.length;
        int[] netFlows = new int[numFlows];
        int numTransactions = 0;

        for (int i = 0; i < numFlows; i++) {
            netFlows[i] = flows[i];
        }

        while (true) {
            int maxFlowIndex = findMax(netFlows);
            int minFlowIndex = findMin(netFlows);

            if (netFlows[maxFlowIndex] == 0 && netFlows[minFlowIndex] == 0) {
                break;
            }

            int amount = Math.min(Math.abs(netFlows[maxFlowIndex]), Math.abs(netFlows[minFlowIndex]));
            netFlows[maxFlowIndex] -= amount;
            netFlows[minFlowIndex] += amount;
            numTransactions++;
            System.out.println("Cash flow " + (maxFlowIndex + 1) + " pays cash flow " + (minFlowIndex + 1) + " $" + amount);
        }

        System.out.println("Transactions: ");
        for (int i = 0; i < numFlows; i++) {
            if (netFlows[i] > 0) {
                for (int j = 0; j < netFlows[i]; j++) {
                    int minFlowIndex = findMin(netFlows);
                    int amount = Math.min(Math.abs(netFlows[i]), Math.abs(netFlows[minFlowIndex]));
                    netFlows[i] -= amount;
                    netFlows[minFlowIndex] += amount;
                    numTransactions++;
                    System.out.println("Cash flow " + (i + 1) + " pays cash flow " + (minFlowIndex + 1) + " $" + amount);
                }
            }
        }

        System.out.println("Total number of transactions: " + numTransactions);
    }

    private static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

}
