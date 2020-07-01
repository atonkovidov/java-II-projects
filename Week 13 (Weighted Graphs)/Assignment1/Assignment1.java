// Finding the minimum distance to connect all farms together, given a NxN matrix of their distances to each
// other.

package assignment1;

import java.util.*;

public class Assignment1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				distance[i][j] = input.nextInt();
		}
		input.close();

		List<WeightedEdge> edges = new ArrayList<WeightedEdge>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!(i == j)) {
				edges.add(new WeightedEdge(i,j,distance[i][j]));
				}
			}
		}
		
		WeightedGraph internet = new WeightedGraph(edges, n);
		System.out.printf("The minimum length of fiber required to connect the entire set of farms is %d.",
				(int) internet.getMinimumSpanningTree().getTotalWeight());
	}
}


/* Output

4 0 4 9 21 4 0 8 17 9 8 0 16 21 17 16 0
The minimum length of fiber required to connect the entire set of farms is 28.

5 0 3 9 21 10 3 0 8 19 20 9 8 0 18 20 21 19 18 0 10 10 20 20 10 0
The minimum length of fiber required to connect the entire set of farms is 31.

*/
