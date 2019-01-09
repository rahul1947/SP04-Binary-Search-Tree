package rsn170330.sp04;

import java.util.Map;
import java.util.TreeMap;

/**
 * Problem 02: Numbers occurring exactly once
 * 
 * @author Rahul Nalawade
 * 
 * Date: January 09, 2019
 */
public class ExactlyOnce {
	
	/**
	 * Given an array A, return an array B that has those elements of A 
	 * that occur exactly once, in the same order in which they appear in A: 
	 * 
	 * static<T extends Comparable<? super T>> T[] exactlyOnce(T[] A) {}
	 * 
	 * E.g. A = {6, 3, 4, 5, 3, 5}. exactlyOnce(A) returns {6,4} 
	 * 
	 * RT = O(n log(n)).
	 * 
	 * @param A the input array
	 * @return array B
	 */
	static<T extends Comparable<? super T>> Object[] exactlyOnce(T[] A) {
		Map<T, Integer> map = new TreeMap<>();
		
		int unique = 0; // no of unique (occurring exactly once) elements seen 
		for (T e : A) {
			Integer count = map.get(e);
			if (count == null) {
				map.put(e, 1); // seen for the first time
				unique++;
			}
			else {
				map.put(e, count + 1); // duplicate
				// when seen for the second time only, 
				// decrement 'unique' counter
				if (count == 1) { unique--; }
			}
		}
		
		// Building up the output array
		Object[] result = new Object[unique];
		int index = 0; 
		for (T e : A) {
			if (map.get(e) == 1) { result[index++] = e; } // unique elements
			if (index == unique) { break; } // Early Exit
		}
		
		return result;
	}
	// NOTE: the return type is changed from T[] to Object[]
	
	public static void main(String[] args) {
		Integer[] A = {6,3,4,5,3,5};
		
		System.out.println("Exactly once: ");
		Object[] result = exactlyOnce(A);
		
		for (Object e : result) {
			System.out.print(e.toString() + " ");
		}
		System.out.println();
	}
}
