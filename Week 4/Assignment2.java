
// Program counts the number of occurrences of entered integers 

import java.util.*;

public class Assignment2 {

	public static void main(String[] args) {
		// Initializing a scanner and map for counting occurrences
		Scanner input = new Scanner(System.in);
		Map<Integer, Integer> map = new TreeMap<>();

		// Processing the entered integers and populating the map
		while (input.hasNext()) {
			int number = input.nextInt();
			if (number == 0)
				break;
			else {
				if (!map.containsKey(number))
					map.put(number, 1);
				else {
					int value = map.get(number);
					value++;
					map.put(number, value);
				}
			}
		}
		input.close();
		
		// Determining max number of occurrences
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();
		TreeSet<Integer> entrySet = new TreeSet<>(map.values());
		int maxOccurrence = entrySet.last();
		
		// Finding and displaying the entries with most occurrences
		Iterator i = set.iterator();
		System.out.println("Number \t\tMax Occurrences");
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			if ((Integer)entry.getValue() == maxOccurrence)
				System.out.println(entry.getKey() + "\t\t" + entry.getValue());
		}
	}
}

/* Output

2 3 40 3 5 4 -3 3 3 2 0
Number 		Max Occurrences
3		4

9 30 3 9 3 2 4 0
Number 		Max Occurrences
3		2
9		2


*/
