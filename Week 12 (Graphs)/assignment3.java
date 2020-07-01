// Finding the smallest number of turns to win a game of Snakes and Ladders on a 10x10 board

package assignment3;

import java.util.*;

public class assignment3 {

	public static void main(String[] args) {
		int[][] snakes = { { 17, 13 }, { 52, 29 }, { 57, 40 }, { 62, 22 }, { 88, 18 }, { 95, 51 }, { 97, 79 } };
		int[][] ladders = { { 3, 21 }, { 8, 30 }, { 28, 84 }, { 58, 77 }, { 75, 86 }, { 80, 100 }, { 90, 91 } };

		System.out.print("The minimum number of turns to win is " + snakeGame(snakes, ladders) + " turns.");
	}

	public static int snakeGame(int[][] snakes, int[][] ladders) {
		int turns = 0;
		int start = 1;
		int[] board = new int[107];
		boolean[] visited = new boolean[107];
		for (int[] e : snakes) {
			board[e[0]] = e[1];
		}
		for (int[] e : ladders) {
			board[e[0]] = e[1];
		}

		Queue<State> movement = new LinkedList<State>();
		movement.offer(new State(start, turns));
		visited[start] = true;

		while (!movement.isEmpty()) {
			State current = movement.poll();
			for (int i = 0; i < 6; i++) {
				if (!visited[current.start + i]) {
					if (board[current.start + i] != 0 && current.start + i < 100) {
						movement.offer(new State(board[current.start + i], turns + 1));
						visited[current.start + i] = true;
						visited[board[current.start + i]] = true;
					}
					else if (current.start + i >= 100)
						return (current.turns + 1) / 6;
					else {
						movement.offer(new State(current.start + i, turns + 1));
						visited[current.start + i] = true;
					}
				}
			}
			turns++;
		}
		return -1;
	}

}

class State {
	int start;
	int turns;

	State(int start, int turns) {
		this.start = start;
		this.turns = turns;
	}

	public String toString() {
		return start + ":" + turns;
	}
}

/* Output

The minimum number of turns to win is 7 turns.

*/
