import java.util.*;

public class Knapsack {

	public static void main(String[] args) {
		final int NUMBER_ITEMS = 10;
		final int MIN_WEIGHT = 1;
		final int MAX_WEIGHT = 5;
		final int MIN_VALUE = 50;
		final int MAX_VALUE = 100;
		final int KNAPSACK_CAPACITY = 20;
		Item[] items = generateItems(NUMBER_ITEMS, MIN_VALUE, MAX_VALUE, MIN_WEIGHT, MAX_WEIGHT);

		// your work
		int[][] knapsack = new int[NUMBER_ITEMS][KNAPSACK_CAPACITY];

		// Populating the "table" of max values for specific weights through dynamic programming
		for (int i = 0; i < NUMBER_ITEMS; i++) {
			for (int j = 1; j < KNAPSACK_CAPACITY + 1; j++) {
				if (i == 0 && items[i].weight <= j)
					knapsack[0][j - 1] = items[0].value;
				else if (i > 0) {
					if (j - items[i].weight > 0) {
						int comparisonValue = (items[i].value + knapsack[i - 1][j - 1 - items[i].weight]);
						if (comparisonValue > knapsack[i - 1][j - 1])
							knapsack[i][j - 1] = comparisonValue;
						else
							knapsack[i][j - 1] = knapsack[i - 1][j - 1];
					}
					else if (j - items[i].weight == 0)
						knapsack[i][j - 1] = items[i].value > knapsack[i - 1][j - 1] ? items[i].value : knapsack[i - 1][j - 1];
					else
						knapsack[i][j - 1] = knapsack[i - 1][j - 1];
				}
			}
		}

		// Displaying the list of available items and the maximum value of the haul
		for (Item i : items)
			System.out.println(i);
		System.out.println(
				"\nThe maximum value obtained from putting the items above into a knapsack" + " with a capacity of "
						+ KNAPSACK_CAPACITY + " pounds is $" + knapsack[NUMBER_ITEMS - 1][KNAPSACK_CAPACITY - 1] + ".");
	}

	static Item[] generateItems(int howMany, int lValue, int hValue, int lWeight, int hWeight) {
		Item[] results = new Item[howMany];
		for (int i = 0; i < howMany; i++) {
			int value = (int) (Math.random() * (hValue - lValue + 1)) + lValue;
			int weight = (int) (Math.random() * (hWeight - lWeight + 1)) + lWeight;
			results[i] = new Item("Item" + i, value, weight);
		}
		return results;
	}
}

class Item {
	String name;
	int value;
	int weight;

	public Item(String s, int v, int w) {
		name = s;
		value = v;
		weight = w;
	}

	@Override
	public String toString() {
		return name + ":" + value + "|" + weight;
	}
}

/* Output

Item0:57|3
Item1:56|5
Item2:95|2
Item3:86|3
Item4:91|3
Item5:94|5
Item6:64|5
Item7:73|3
Item8:63|2
Item9:87|5

The maximum value obtained from putting the items above into a knapsack with a capacity of 20 pounds is $516.

*/