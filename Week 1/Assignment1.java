import java.util.ArrayList;

public class Assignment1 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(14);
		list.add(42);
		list.add(25);
		ArrayList<Integer> newList = removeDuplicates(list);
		System.out.print(newList);
	}

	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		ArrayList<E> newlist = new ArrayList<E>();
		for (int i = 0; i < list.size(); i++) {
			E value = list.get(i);
			if (newlist.contains(value))
				continue;
			newlist.add(value);
		}
		return newlist;
	}
}
