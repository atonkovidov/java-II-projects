// A program that sorts various items using a comparator interface 

import java.util.Comparator;

public class Assignment3 {

	public static void main(String[] args) {
		// Creating the two lists to be sorted
		GeometricObject[] list1 = {new Circle(5), new Rectangle(4, 5), new Circle(5.5),
			new Rectangle(2.4, 5), new Circle(0.5), new Rectangle(4, 65),new Circle(4.5),
			new Rectangle(4.4, 1), new Circle(6.5), new Rectangle(4, 5)};
		String[] list2 = {"red", "blue", "green", "yellow", "orange", "pink"};
		
		// Sorting the lists
		selectionSort(list1, new GeometricObjectComparator());
		selectionSort(list2, new StringComparator());
		
		// Displaying the sorted lists
		for (GeometricObject o: list1)
			System.out.print("|" + o.getArea() + "| ");
		System.out.println();
		for (String s: list2)
			System.out.print("|" + s + "| ");
	}
	
	// The generic selectionSort method
	public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
		for (int i = 0; i < list.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < list.length; j++) {
				int result = comparator.compare(list[minIndex], list[j]);
				if (result == -1)
					minIndex = j;					
			}
			E min = list[minIndex];
			list[minIndex] = list[i];
			list[i] = min;
		}
	}
}

//Comparator for Strings sorted by their last character
class StringComparator implements Comparator<String> {
	public int compare(String s1, String s2) {
		char c1 = s1.charAt(s1.length() - 1);
		char c2 = s2.charAt(s2.length() - 1);
		
		if (c1 < c2)
			return -1;
		else if (c1 == c2)
			return 0;
		else
			return 1;
	}
}

/* Output

|260.0| |132.73228961416876| |95.03317777109125| |78.53981633974483| |63.61725123519331| |20.0| |20.0| |12.0| |4.4| |0.7853981633974483| 
|yellow| |green| |pink| |orange| |blue| |red| 

*/
