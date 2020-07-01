// Displaying a minimum spanning tree with data from a file 

package assignment2;

import java.util.*;
import assignment1.WeightedEdge;
import assignment1.WeightedGraph;
import java.io.File;
import java.io.FileNotFoundException;

public class Assignment2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		File graph = new File(input.nextLine());
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
			
			WeightedGraph.MST tree = g.getMinimumSpanningTree();
			System.out.println("Total weight in MST is " + (int) tree.getTotalWeight());
			tree.printTree();
		}

		catch (FileNotFoundException e) {
			System.out.print("File not found");
		}
	}
}

/* Output

Enter a file name: C:\Users\Andre\Programs\eclipse\eclipse-workspace\Java II\Week 13\src\assignment2\WeightedGraphSample.txt
The number of vertices is 12
Vertex 0: (0, 1, 807) (0, 3, 1331) (0, 5, 2097) 
Vertex 1: (1, 0, 807) (1, 2, 381) (1, 3, 1267) 
Vertex 2: (2, 1, 381) (2, 3, 1015) (2, 4, 1663) (2, 10, 1435) 
Vertex 3: (3, 0, 1331) (3, 1, 1267) (3, 2, 1015) (3, 4, 599) (3, 5, 1003) 
Vertex 4: (4, 2, 1663) (4, 3, 599) (4, 5, 533) (4, 7, 1260) (4, 8, 864) (4, 10, 496) 
Vertex 5: (5, 0, 2097) (5, 3, 1003) (5, 4, 533) (5, 6, 983) (5, 7, 787) 
Vertex 6: (6, 5, 983) (6, 7, 214) 
Vertex 7: (7, 4, 1260) (7, 5, 787) (7, 6, 214) (7, 8, 888) 
Vertex 8: (8, 4, 864) (8, 7, 888) (8, 9, 661) (8, 10, 781) (8, 11, 810) 
Vertex 9: (9, 8, 661) (9, 11, 1187) 
Vertex 10: (10, 2, 1435) (10, 4, 496) (10, 8, 781) (10, 11, 239) 
Vertex 11: (11, 8, 810) (11, 9, 1187) (11, 10, 239) 
Total weight in MST is 6513
Root is: 0
Edges: (0, 1) (1, 2) (2, 3) (3, 4) (4, 5) (7, 6) (5, 7) (10, 8) (8, 9) (4, 10) (10, 11)   

*/
