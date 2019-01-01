package rsn170330.sp04;

import java.util.*;
import java.math.BigInteger;

public class Election {

	public static void main(String[] args) {
		String[] input = {"Joe", "Mary", "Mary", "Joe"};
		System.out.println(electionWinner(input));
	}

	public static String electionWinner(String[] votes) {

		int len = votes.length;

		TreeMap<String, Integer> map = new TreeMap<>();
		for (int i = 0; i < len; i++) {

			if (map.containsKey(votes[i])) {

				map.put(votes[i], map.get(votes[i]) + 1);

			} else {
				map.put(votes[i], 1);
			}

		}

		int max = -1;
		String ans = "";

		for (String s: map.keySet()) {
			if (max <= map.get(s)) {
				max = map.get(s);
				ans = s;
			}
		}

		return ans;
	}

	public static long minHealth(List<Integer> dungeon) {
		// int[] output = new int[2];
		int lengthA = dungeon.size();
		
		if (lengthA < 1)
			return 0;
		for (int i=0; i< lengthA; i++) { 
			dungeon.set(i, dungeon.get(i) * -1); 
		}
		//int first = 0;
		//int last = 0;
		long previousSum = dungeon.get(0);
		long sum = dungeon.get(0);
		long maxSum = dungeon.get(0);
		
		for (int i = 1; i < lengthA; i++) {
			sum = previousSum + dungeon.get(i);
			
			// When previousSum is of NO USE
			if (previousSum < 0) {
				//first = i;
				sum = dungeon.get(i);
			}

			// When we get a bigger fish
			if (sum >= maxSum) {
				//last = i;
				maxSum = sum;
			}

			previousSum = sum;
		}
		//System.out.println("(f,l): "+first+" "+last);
		return maxSum;
	}
	
	public static int drawingEdge(int n) {
        
        Long edges = new Long(n*(n-1)/2); // edges = nC2
        Integer md = new Integer(1000000007);
        
        BigInteger two = new BigInteger("2");
        BigInteger e = new BigInteger(edges.toString());
        BigInteger b = new BigInteger(md.toString());
        
        BigInteger p = two.modPow(e, b);
        
        String s = p.toString();
        
        
        return Integer.parseInt(s);
    }

}
