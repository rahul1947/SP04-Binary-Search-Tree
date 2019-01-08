package rsn170330.sp04;

import java.util.Map;
import java.util.TreeMap;

/**
 * Problem: No of TWO SUM PAIRS
 * Solution to the problem 4: with TreeMap/ TreeSet
 * @author Rahul Nalawade
 * 
 * Date: Wednesday, Jan 02, 2019
 */

public class HowMany {
	
	/**
	 * Given an unsorted array A of integers, might containing 
	 * duplicates, and an integer X, find how many pairs of 
	 * elements of A sum to X:
	 * 
	 * E.g. How many indexes i, j (with i != j) are there 
	 * with A[i] + A[j] = X? 
	 * If A = {3,3,4,5,3,5} then howMany(A,8) returns 6
	 * 
	 * @param A the input array of integers 
	 * @param X the target number
	 * @return number of pairs summing up to X
	 */
	static int howMany1(int[] A, int X) {
		// map: maps and integer to it's count in array A
		Map<Integer, Integer> map = new TreeMap<>();
		Integer count = 0; // map.get(e) returns Integer* (not int)
		
		// Insertion: RT = O(log(1) + log(2) + ... + log(n)) = O(log(n!))
		// As, log(n!) <= n log(n). RT = O(log(n!)) = 0(n log(n)) 
		// Thus, RT = O(n log(n)) (loosely mentioned)
		for (int e : A) {
			count = map.get(e);
			if (count == null) 
				map.put(e, 1);
			else 
				map.put(e, count + 1);
		}
		
		int result = 0;
		for (int e : A) {
			count = map.get(X - e);
			result += count;
			// Avoiding self counting
			if (e == X - e) 
				result--;
		}
		
		// Avoiding double counting: {X, X-e} and {X - e, e}
		return (int) (result / 2); 
	}
	
	// Using DYNAMIC PROGRAMMING, better solution
	static int howMany2(int[] A, int X) {
		// map: maps and integer to it's count in array A
		Map<Integer, Integer> map = new TreeMap<>();
		
		// Initializing all {e, X-e} pairs. So, that 
		// result += is updated correctly.
		for (int e : A) {
			map.put(e, 0);
			map.put(X - e, 0);
		}
		
		int result = 0;
		// Dynamically stores results in result 
		// after each integer is visited
		for (int e : A) {
			result += map.get(X - e); // count of all visited (X - e)
			
			map.put(e, map.get(e) + 1); // increment count as you 
			// see the integer more than once
		}
		
		return result;
	}

	public static void main(String[] args) {
		int[] arr = {3, 3, 4, 5, 3, 5, 4}; 
		int X = 8;
		
		System.out.println("Using TreeMap: ");
		System.out.println("howMany1(A,8) = " + howMany1(arr, X));
		
		System.out.println();
		System.out.println("Using Dynamic Programming and TreeMap: ");
		System.out.println("howMany2(A,8) = " + howMany2(arr, X));
	}
}
