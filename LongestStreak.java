package rsn170330.sp04;

import java.util.Set;
import java.util.TreeSet;

/**
 * Problem 03: Longest Streak
 * 
 * @author Rahul Nalawade
 * 
 * Date: January 09, 2019
 */
public class LongestStreak {
	
	/**
	 * Given an array A of integers, find the length of a longest streak 
	 * of consecutive integers that occur in A (not necessarily contiguously):
	 * 
	 * E.g. A = {1,7,9,4,1,7,4,8,7,1}. longestStreak(A) return 3, 
	 * corresponding to the streak {7,8,9} of consecutive integers 
	 * that occur somewhere in A.
	 * 
	 * RT = O(n log(n)).
	 * 
	 * @param A the input array
	 * @return the length of the longest streak
	 */
	static int longestStreak(int[] A) {
		Set<Integer> set = new TreeSet<>();
		
		// adding all elements to our Tree Set
		for (int e : A) { set.add(e); }
		
		int max = 0;
		for (Integer e : set) { 
			// check if (e - 1) exists. If so, skip it and move further.
			if (set.contains(e-1)) { continue; }
			
			// When no (e-1) available, check on the higher side. 
			// We might have got lowest element of a streak
			int X = e + 1;
			
			// LI: current streak {e, e + 1, e + 2, ..., X - 1} of length X - e. 
			while (set.contains(X)) { X++; }
			
			// updating longest streak length
			if (max < (X - e)) { max = X - e; }
		}
		
		return max;
	}
	public static void main(String[] args) {
		int[] A = {1,7,9,4,1,7,4,8,7,1};
		
		System.out.println("Longest Streak: " + longestStreak(A));
	}

}
