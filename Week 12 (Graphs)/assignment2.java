// Minimum additional snow drifts required to reach any drift from any other 

package assignment2;

import java.util.*;

public class assignment2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		Map<Node, Set<Node>> map = new HashMap<>();
		Node start = null;

		for (int points = 0; points < n; points++) {
			Node newNode = new Node(input.nextInt(), input.nextInt());
			map.put(newNode, new HashSet<Node>());
			if (points == 0)
				start = newNode;
			for (Node e : map.keySet()) {
				if ((e.x == newNode.x && e.y != newNode.y) || (e.x != newNode.x && e.y == newNode.y)) {
					map.get(e).add(newNode);
					map.get(newNode).add(e);
				}
			}
		}
		
		// Mark visited vertices
		Map<Node, Set<Node>> visited = new HashMap<>();

		// Recursively search
		int count = 0;
		int moreDrifts = dfs(visited, map, start, count) - 1;
		input.close();
		System.out.print(moreDrifts);
	}

	/** Recursive method for DFS search */
	private static int dfs(Map<Node, Set<Node>> visited, Map<Node, Set<Node>> map, Node start, int count) {
		count++;
		visited.put(start, map.get(start));
		for (Node s : map.get(start))
			visited.put(s, map.get(s));
		
		for (Node e : map.keySet()) {
			if (!visited.containsKey(e)) {
				count = dfs(visited, map, e, count); // Recursive search
			}
		}
		return count;
	}
}

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

/* Output

2 2 1 4 1
0

2 2 1 1 2
1

6 2 1 2 4 3 6 3 7 4 3 4 5
2

*/
