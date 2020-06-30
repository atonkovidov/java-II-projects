// A method that returns the maximum element in a two-dimensional array 

public class Assignment2 {
	public static void main(String[] args) {
		Integer[][] numbers = { { 1, 2, 3 }, { 4, 4, 6 } };
		System.out.println(max(numbers));
	}
	
	public static <E extends Comparable<E>> E max(E[][] list) {
		E max_value = list[0][0];
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (max_value.compareTo(list[i][j]) < 0)
					max_value = list[i][j];
			}
		}
		return max_value;
	}
}
