package rsn170330.sp04;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.Comparator;

/**
 * CS 5V81.001: Implementation of DSA
 * Short Project 04: Implementation of Binary Search Tree
 * 
 * @author Rahul Nalawade - rsn170330
 * @author Bharath Reddy - bxr180008
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
	
	// Standard Attributes of BST:  
	Entry<T> root; // root of BST
	int size; // size of BST
	
	// Personal Attributes of BST:
	Entry<T> t; // a temporary entry
	// to keep track of ancestral information - 
	
	// For tree-traversal from root to a certain node t
	// useful in methods: find(t) bypass(t)
	Stack<Entry <T>> ancestors = new Stack<Entry <T>>(); // also useful in AVL (balancing)
	
	// Every node of BST = an Entry
	public static class Entry<T> {
		T element; // value of the entry
		Entry<T> left, right; // left and right child
		
		// default constructor
		public Entry(T x, Entry<T> left, Entry<T> right) {
			element = x;
			this.left = left;
			this.right = right;
		}
		
		// for internal use: Entry without any children
		private Entry(T x) {
			element = x;
			left = null;
			right = null;
		}
	}
	
	// default constructor
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * finds an entry that has its element = x. or 
	 * finds an entry at which we failed to find x.
	 * @param x the element to be searched
	 * @return the Entry e where e.element = x
	 */
	private Entry<T> find(T x) {
		
		ancestors.push(null); // pushing parent of root
		return find(root, x); 
	}
	
	/**
	 * O(depth(x)) worst case O(log n)
	 * Helper find(x) method: starts the search of x 
	 * from the tree rooted at t (generally root)
	 * @param t the Entry from which the search begins
	 * @param x the element to be searched
	 * @return the Entry when search ends, when element = x is found
	 */
	private Entry<T> find(Entry<T> t, T x) {
		
		// When first entry itself = null or when we found the element
		if (t == null || t.element.equals(x)) return t;
		
		// 
		while(true) {
			// When x < t.element
			if (x.compareTo(t.element) < 0) { 
				
				// When there is no left subtree
				if (t.left == null) break; // when failed, return the element where failed
				else {
					// when left child exists
					ancestors.push(t);
					t = t.left;
				}
			}
			// When t.element < x
			else if (x.compareTo(t.element) > 0) {
				if (t.right == null) break;
				else {
					ancestors.push(t);
					t = t.right;
				}
			}
			// We found the element
			else break;
		}
		return t;
	}
	
	/**
	 * Is x contained in tree?
	 * @param x
	 * @return
	 */
	public boolean contains(T x) {
		t = find(x);
		if (t == null || x.compareTo(t.element) != 0) return false; // When failed
		
		return true;
	}

	/**
	 * Is there an element that is equal to x in the tree? 
	 * Element in tree that is equal to x is returned, null otherwise.
	 * @param x the element we are looking for
	 * @return if found, return the element, null otherwise
	 */
	public T get(T x) {
		t = find(x);
		if (t == null || x.compareTo(t.element) != 0) return null; // When not found
		
		return x;
	}

	/**
	 * Add x to tree. If tree contains a node with same key, replace element by x. 
	 * Returns true if x is a new element added to tree.
	 * @param x the element to be added
	 * @return is addition successful?
	 */
	public boolean add(T x) {
		
		// When the tree itself is empty, DON'T attempt to find(x) 
		if (size == 0) {
			root = new Entry<T>(x);
			// Having dummy root node is not common in tree, unlike lists
			size = 1;
		} 
		
		else {
			t = find(x); // t won't be null, as size > 0
			
			if (x.compareTo(t.element) == 0) {
				t.element = x; // replacement*
				return false; // funny code :)
			}
			
			// Now, adding as child of t
			else if (x.compareTo(t.element) < 0) t.left = new Entry<T>(x); // x < t.element
			else t.right = new Entry<T>(x); // t.element < x
			
			size++;
		}
		
		// addition successful
		return true;
	}

	/**
	 * Remove x from tree. Return x if found, otherwise return null
	 * @param x the element to be removed
	 * @return removed element
	 */
	public T remove(T x) {
		
		// When tree is empty
		if (root == null) return null;
		
		t = find(x);
		
		// When we didn't found x in the tree
		if (x.compareTo(t.element) != 0) return null;
		
		T result = t.element; // found x at t
		
		// When t has at-most one child.
		if (t.left == null || t.right == null) bypass(t);
		
		// When t has two children
		else {
			ancestors.push(t);
			//attempting to find x which is at t in subtree rooted at t.right
			Entry<T> minRight = find(t.right, x); // minimum RIGHT DESCENDANT of t*
			t.element = minRight.element;
			bypass(minRight); // minRight would always have at-most ONE child :)
		}
		size--;
		return result;
	}
	
	// Returns minimum element in BST
	public T min() {
		if (size == 0) return null;
		
		t = root; // starting at root
		while (t.left != null) t = t.left; // tracing left subtree
		
		return t.element; 
	}
	
	// Returns maximum element in BST 
	public T max() {
		if (size == 0) return null;
		
		t = root; // starting at root
		while (t.right != null) t = t.right; // tracing right subtree
		
		return t.element;
	}
	
	/**
	 * Removes parent from the subtree -grandParent-parent-child- 
	 * attaching only child to grandParent
	 * 
	 * Precondition: 
	 * 	1. t has at-most one child 
	 * 	2. Stack ancestors has path from root to parent of t
	 * @param t the input element
	 */
	private void bypass(Entry<T> t) {
		
		Entry<T> parent = ancestors.peek(); // parent of t
		
		Entry<T> child = (t.left == null)? t.right: t.left; // Precondition 1
		
		// when t is root of the tree
		if (parent == null) root = child; // bypassing root 
		
		else { 
			// When the king(t) is dead, long live the king! ;)
			if (parent.left == t) parent.left = child; 
			else parent.right = child; // t was rightChild of parent
		}
	}

	/**
	 * Creates an array with the elements using in-order traversal of tree.
	 */
	public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		
		if (root == null) return null;
		
		Stack<Entry<T>> ancestors = new Stack<Entry<T>>();
		t = root;
		int i=0;
		
		while (t != null || ancestors.size() > 0) {
			
			while (t != null) {
				ancestors.push(t);
				t = t.left;	
			}
			t = ancestors.pop();
			
			arr[i] = t.element;
			i++;
			
			t = t.right;
		}
		return arr;
	}

	// Start of Optional problem 2

	/**
	 * Optional problem 2: Iterate elements in sorted order of keys Solve this
	 * problem without creating an array using in-order traversal (toArray()).
	 */
	public Iterator<T> iterator() {
		return null;
	}

	// Optional problem 2. Find largest key that is no bigger than x. Return null if
	// there is no such key.
	public T floor(T x) {
		return null;
	}

	// Optional problem 2. Find smallest key that is no smaller than x. Return null
	// if there is no such key.
	public T ceiling(T x) {
		return null;
	}

	// Optional problem 2. Find predecessor of x. If x is not in the tree, return
	// floor(x). Return null if there is no such key.
	public T predecessor(T x) {
		return null;
	}

	// Optional problem 2. Find successor of x. If x is not in the tree, return
	// ceiling(x). Return null if there is no such key.
	public T successor(T x) {
		return null;
	}

	// End of Optional problem 2
	
	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	private void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		Scanner in = new Scanner(System.in);
		
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
			} 
			else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.printTree();
			} 
			else {
				Comparable[] arr = t.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				return;
			}
		}
	}
}