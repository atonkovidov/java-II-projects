// Displaying the shortest path between two user-chosen vertices from a file 

package assignment3;

import java.util.*;
import assignment1.WeightedEdge;
import assignment1.WeightedGraph;
import java.io.File;
import java.io.FileNotFoundException;

public class Assignment3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		File graph = new File(input.nextLine());
		
		System.out.print("Enter two vertices (integer indexes): ");
		int start = input.nextInt();
		int finish = input.nextInt();
		input.close();

		try {
			input = new Scanner(graph);
			int n = input.nextInt();
			System.out.println("The number of vertices is " + n);
			List<WeightedEdge> list = new ArrayList<WeightedEdge>();
			
			while (input.hasNextLine()) {
				String s = input.nextLine();
				String[] edges = s.split("[,| ]+");
				
				for (int i = 0; i < edges.length / 3; i++) {
					list.add(new WeightedEdge(Integer.parseInt(edges[i * 3]),
							Integer.parseInt(edges[i * 3 + 1]), Integer.parseInt(edges[i * 3 + 2])));
					// Second representation of edge 
					list.add(new WeightedEdge(Integer.parseInt(edges[i * 3 + 1]),
							Integer.parseInt(edges[i * 3]), Integer.parseInt(edges[i * 3 + 2])));
				}
			}
			
			input.close();
			WeightedGraph g = new WeightedGraph(list, n);
			g.printWeightedEdges();
			
			WeightedGraph.ShortestPathTree tree = g.getShortestPath(start);
			tree.printPath(finish);
		}

		catch (FileNotFoundException e) {
			System.out.print("File not found");
		}
	}
}


/* Output

Enter a file name: C:\Users\Andre\Programs\eclipse\eclipse-workspace\Java II\Week 13\src\assignment3\WeightedGraphSample2.txt
Enter two vertices (integer indexes): 0 1
The number of vertices is 6
Vertex 0: (0, 1, 100) (0, 2, 3) 
Vertex 1: (1, 0, 100) (1, 3, 20) 
Vertex 2: (2, 0, 3) (2, 3, 40) (2, 4, 2) 
Vertex 3: (3, 1, 20) (3, 2, 40) (3, 4, 5) (3, 5, 5) 
Vertex 4: (4, 2, 2) (4, 3, 5) (4, 5, 9) 
Vertex 5: (5, 3, 5) (5, 4, 9) 
A path from 0 to 1: 0 2 4 3 1 

*/
