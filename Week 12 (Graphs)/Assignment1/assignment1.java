// Test whether a graph is connected 

package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class assignment1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a file name (full location):");
		File graph = new File(input.nextLine());
		
		try {
			input = new Scanner(graph);
			int vertices = input.nextInt();
			System.out.println("The number of vertices is " + vertices);
			
			List<Edge> edges = new ArrayList<Edge>();
			
			for (int i = 0; i < vertices + 1; i++) {
				String s = input.nextLine();
				String[] edgeEndPoints = s.split(" ");
				
				for (int j = 1; j < edgeEndPoints.length; j++) {
					Edge temp = new Edge(Integer.parseInt(edgeEndPoints[0]), Integer.parseInt(edgeEndPoints[j]));
					edges.add(temp);
				}
			}
			
			input.close();
			
			UnweightedGraph g = new UnweightedGraph(edges, vertices);
			g.printEdges();
			
			UnweightedGraph.SearchTree tree = g.dfs(0);
			System.out.print("The graph is ");
			if (tree.getNumberOfVerticesFound() == vertices)
				System.out.print("connected");
			else
				System.out.print("not connected");
		}
		
		catch (FileNotFoundException e) {
	        System.out.print("File not found");
	    }
	}

}


/* Output

Enter a file name (full location):
C:\Users\Andre\Programs\eclipse\eclipse-workspace\Java II\Week 12\src\assignment1\GraphSample1.txt
The number of vertices is 6
Vertex 0: (0, 1) (0, 2) 
Vertex 1: (1, 0) (1, 3) 
Vertex 2: (2, 0) (2, 3) (2, 4) 
Vertex 3: (3, 1) (3, 2) (3, 4) (3, 5) 
Vertex 4: (4, 2) (4, 3) (4, 5) 
Vertex 5: (5, 3) (5, 4) 
The graph is connected

*/
