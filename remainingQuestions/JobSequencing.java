package remainingQuestions;

import java.util.Arrays;

/*When Does a Greedy Strategy Provide an Optimal Solution?
A greedy strategy provides an optimal solution when a problem exhibits the following properties:

Optimal Substructure: An optimal solution to the problem contains optimal solutions to subproblems.
Greedy Choice Property: A globally optimal solution can be obtained by making a series of locally optimal (greedy) choices.
Examples where a greedy strategy provides an optimal solution:

Activity Selection Problem
Huffman Coding
Kruskal’s and Prim’s Algorithms for Minimum Spanning Trees
Dijkstra’s Algorithm for Single-Source Shortest Path (only for graphs with non-negative weights)
Job Sequencing with Deadlines (when sorted by profit and scheduled greedily)

*/

class Job {
    int id, deadline, profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    public static void jobSequencing(Job[] jobs, int n) {
        // Step 1: Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 2: Create a slot array to track assigned jobs (-1 means empty)
        int[] slot = new int[maxDeadline + 1];
        Arrays.fill(slot, -1);

        // Step 3: Schedule jobs
        int totalProfit = 0;
        int count = 0;

        for (Job job : jobs) {
            for (int j = Math.min(maxDeadline, job.deadline); j > 0; j--) {
                if (slot[j] == -1) { // If slot is free
                    slot[j] = job.id;
                    totalProfit += job.profit;
                    count++;
                    break;
                }
            }
        }

        // Step 4: Print scheduled jobs
        System.out.println("Scheduled Jobs: " + count);
        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = { new Job(1, 2, 100), new Job(2, 1, 50), new Job(3, 2, 10), new Job(4, 1, 20),
                new Job(5, 3, 200) };
        jobSequencing(jobs, jobs.length);
    }
}
