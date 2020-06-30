// Farmer John board cutting problem (finding smallest cost) 

import java.util.*;

public class Assignment3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfBoards = input.nextInt();
		int currentBoard = 0;
		
		BST<Integer> tree = new BST<>();
		
		for (int i = 0; i < numberOfBoards; i++) {
			int length = input.nextInt();
			tree.insert(length);
			currentBoard += length;
		}
		input.close();
		
		int totalCost = currentBoard;
		int[] orderOfCuts = tree.orderOfCuts(numberOfBoards);
		for (int i = numberOfBoards - 1; i > 1; i--) {
			currentBoard -= orderOfCuts[i];
			totalCost += currentBoard;
		}
		
		System.out.println(totalCost);
	}

	public static class BST<Integer extends Comparable<Integer>> implements Tree<Integer> {
		protected TreeNode<Integer> root;
		protected int size = 0;
		protected int maxHeight = 0;

		/** Create a default binary search tree */
		public BST() {
		}

		/** Create a binary search tree from an array of objects */
		public BST(Integer[] objects) {
			for (int i = 0; i < objects.length; i++)
				insert(objects[i]);
		}

		/** Return true if the element is in the tree */
		public boolean search(Integer e) {
			TreeNode<Integer> current = root; // Start from the root

			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					current = current.right;
				} else
					// element matches current.element
					return true; // Element is found
			}

			return false;
		}

		/**
		 * Insert element e into the binary search tree Return true if the element is
		 * inserted successfully
		 */
		public boolean insert(Integer e) {
			if (root == null)
				root = createNewNode(e); // Create a new root
			else {
				// Locate the parent node
				TreeNode<Integer> parent = null;
				TreeNode<Integer> current = root;
				while (current != null)
					if (e.compareTo(current.element) <= 0) {
						parent = current;
						current = current.left;
					} else if (e.compareTo(current.element) > 0) {
						parent = current;
						current = current.right;
					} 
//						else
//						return false; // Duplicate node not inserted

				// Create the new node and attach it to the parent node
				if (e.compareTo(parent.element) < 0) {
					parent.left = createNewNode(e);
					parent.left.height = parent.height + 1;
					if (maxHeight < parent.left.height)
						maxHeight = parent.left.height;
				} else {
					parent.right = createNewNode(e);
					parent.right.height = parent.height + 1;
					if (maxHeight < parent.right.height)
						maxHeight = parent.right.height;
				}
			}

			size++;
			return true; // Element inserted
		}

		protected TreeNode<Integer> createNewNode(Integer e) {
			return new TreeNode<Integer>(e);
		}

		/** Inorder traversal from the root */
		public void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		protected void inorder(TreeNode<Integer> root) {
			if (root == null)
				return;
			inorder(root.left);
			System.out.print(root.element + " ");
			inorder(root.right);
		}

		/** Postorder traversal from the root */
		public void postorder() {
			postorder(root);
		}

		/** Postorder traversal from a subtree */
		protected void postorder(TreeNode<Integer> root) {
			if (root == null)
				return;
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.element + " ");
		}

		/** Preorder traversal from the root */
		public void preorder() {
			preorder(root);
		}

		/** Preorder traversal from a subtree */
		protected void preorder(TreeNode<Integer> root) {
			if (root == null)
				return;
			System.out.print(root.element + " ");
			preorder(root.left);
			preorder(root.right);
		}

		/** Inner class tree node */
		public static class TreeNode<Integer extends Comparable<Integer>> {
			Integer element;
			TreeNode<Integer> left;
			TreeNode<Integer> right;
			// Added variable height to each node
			int height = 0;

			public TreeNode(Integer e) {
				element = e;
			}

			// Get the height of a node
			public int getHeight() {
				return height;
			}
		}

		/** Get the number of nodes in the tree */
		public int getSize() {
			return size;
		}

		public boolean isEmpty() {
			if (getSize() == 0)
				return true;
			else
				return false;
		}

		// Return max height
		public int getMaxHeight() {
			return maxHeight;
		}

		/** Returns the root of the tree */
		public TreeNode<Integer> getRoot() {
			return root;
		}

		/** Returns a path from the root leading to the specified element */
		public java.util.ArrayList<TreeNode<Integer>> path(Integer e) {
			java.util.ArrayList<TreeNode<Integer>> list = new java.util.ArrayList<TreeNode<Integer>>();
			TreeNode<Integer> current = root; // Start from the root

			while (current != null) {
				list.add(current); // Add the node to the list
				if (e.compareTo(current.element) < 0) {
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					current = current.right;
				} else
					break;
			}

			return list; // Return an array of nodes
		}

		/**
		 * Delete an element from the binary search tree. Return true if the element is
		 * deleted successfully Return false if the element is not in the tree
		 */
		public boolean delete(Integer e) {
			// Locate the node to be deleted and also locate its parent node
			TreeNode<Integer> parent = null;
			TreeNode<Integer> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					break; // Element is in the tree pointed by current
			}

			if (current == null)
				return false; // Element is not in the tree

			// Case 1: current has no left children
			if (current.left == null) {
				// Connect the parent with the right child of the current node
				if (parent == null) {
					root = current.right;
				} else {
					if (e.compareTo(parent.element) < 0)
						parent.left = current.right;
					else
						parent.right = current.right;
				}
			} else {
				// Case 2: The current node has a left child
				// Locate the rightmost node in the left subtree of
				// the current node and also its parent
				TreeNode<Integer> parentOfRightMost = current;
				TreeNode<Integer> rightMost = current.left;

				while (rightMost.right != null) {
					parentOfRightMost = rightMost;
					rightMost = rightMost.right; // Keep going to the right
				}

				// Replace the element in current by the element in rightMost
				current.element = rightMost.element;

				// Eliminate rightmost node
				if (parentOfRightMost.right == rightMost)
					parentOfRightMost.right = rightMost.left;
				else
					// Special case: parentOfRightMost == current
					parentOfRightMost.left = rightMost.left;
			}

			size--;
			return true; // Element deleted
		}

		/** Obtain an iterator. Use inorder. */
		public java.util.Iterator<Integer> iterator() {
			return new InorderIterator();
		}

		// Inner class InorderIterator
		private class InorderIterator implements java.util.Iterator<Integer> {
			// Store the elements in a list
			private java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>();
			private int current = 0; // Point to the current element in list

			public InorderIterator() {
				inorder(); // Traverse binary tree and store elements in list
			}

			/** Inorder traversal from the root */
			private void inorder() {
				inorder(root);
			}

			/** Inorder traversal from a subtree */
			private void inorder(TreeNode<Integer> root) {
				if (root == null)
					return;
				inorder(root.left);
				list.add(root.element);
				inorder(root.right);
			}

			/** Next element for traversing? */
			public boolean hasNext() {
				if (current < list.size())
					return true;

				return false;
			}

			/** Get the current element and move cursor to the next */
			public Integer next() {
				return list.get(current++);
			}

			/** Remove the current element and refresh the list */
			public void remove() {
				delete(list.get(current)); // Delete the current element
				list.clear(); // Clear the list
				inorder(); // Rebuild the list
			}
		}

		/** Remove all elements from the tree */
		public void clear() {
			root = null;
			size = 0;
		}

		public int[] orderOfCuts(int amount) {
			int[] cuts = new int[amount];
			Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
			int i = 0;
			TreeNode<Integer> current = root;

			if (root == null)
				return null;

			while (!stack.isEmpty() || current != null) {
				if (current != null) { 
					stack.push(current);
					current = current.left;
				}
				else {
					current = stack.pop();
					cuts[i] = (int) current.element;
					i++;
					current = current.right;
				}
			}
			return cuts;
		}

	}
}

/* Output

3 8 5 8
34

*/
