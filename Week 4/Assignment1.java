// Performing set operations on hash sets

import java.util.*;

public class Assignment1 {

	public static void main(String[] args) {
		// Initializing the two sets
		Set<String> set1 = new HashSet<>();
		set1.add("George");
		set1.add("Jim");
		set1.add("John");
		set1.add("Blake");
		set1.add("Kevin");
		set1.add("Michael");
		
		Set<String> set2 = new HashSet<>();
		set2.add("George");
		set2.add("Katie");
		set2.add("Kevin");
		set2.add("Michelle");
		set2.add("Ryan");
		
		// Displaying the union of the two sets
		Set<String> union = new HashSet<>(set1);
		union.addAll(set2);
		System.out.println("The union of the two sets is " + union + ".");
		
		// Displaying the difference of the two sets
		Set<String> difference = new HashSet<>(set1);
		difference.removeAll(set2);
		System.out.println("\nThe difference of the two sets (set1 - set2) is " + difference + ".");
		
		// Displaying the intersection of the two sets
		Set<String> intersection = new HashSet<>(set1);
		intersection.retainAll(set2);
		System.out.println("\nThe intersection of the two sets is " + intersection + ".");
	}

}
