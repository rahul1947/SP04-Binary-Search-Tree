## Short Project SP04: Binary Search Tree

1. Implementation of Binary Search Tree,
2. Implementation of Bounded-size Stack, 
3. Implementation of BST Map (like a TreeMap) on top of BST class, and 
4. Solution to the 3 problems using TreeMap/ TreeSet. 

#### Author
* [Rahul Nalawade](https://github.com/rahul1947)

#### Date
* September 30, 2018
* January 09, 2019

_______________________________________________________________________________
### Problems:

#### A. Team Task: 

**Problem 1.** 
   Implement binary search trees.  Starter code: BinarySearchTree.java.
   
**Solution:** [BinarySearchTree.java](https://github.com/rahul1947/SP04-Binary-Search-Tree/blob/master/BinarySearchTree.java)

#### B. Optional tasks (individual) - Bounded Size Stack, BSTMap: 

**Problem 2.** 
   Additional tasks on BST:
   Implement a bounded-sized stack using arrays with the operations push, pop, and isEmpty.
   Use it to implement iterator(), without copying the elements into another data structure
   like array or list. 
   
   The problem can be solved using just O(h) extra space for stack of ancestors,
   where h is the height of the tree.  
   
   In the iterator's constructor, find the height of the tree and allocate an array of 
   size h for the stack.
   
   Implement floor(), ceiling(), predecessor() and successor() methods also.

**Solution:** [BoundedStack.java](https://github.com/rahul1947/SP04-Binary-Search-Tree/blob/master/BoundedStack.java)
 
**Problem 3.**
   Implement BSTMap (like a TreeMap), on top of BST class.  Starter code: BSTMap.java
   
**Solution:** [BSTMap.java](https://github.com/rahul1947/SP04-Binary-Search-Tree/blob/master/BSTMap.java)

#### c. Optional tasks (individual) - Problems on TreeMap/TreeSet: 

The following problems should be solved using TreeMap/TreeSet and other data structures
in the Java library. **Do not use hashing (HashMap/HashSet).**

**Problem 4.** 
   Given an array A of integers, and an integer X, find how many pairs of 
   elements of A sum to X:
```
   static int howMany(int[] A, int X) { // RT = O(nlogn).
      // How many indexes i,j (with i != j) are there with A[i] + A[j] = X?
      // A is not sorted, and may contain duplicate elements
      // If A = {3,3,4,5,3,5} then howMany(A,8) returns 6
   }
```
**Solution:** [HowMany.java](https://github.com/rahul1947/SP04-Binary-Search-Tree/blob/master/HowMany.java)


**Problem 5.**
   Given an array A, return an array B that has those elements of A that
   occur exactly once, in the same order in which they appear in A:
```
   static<T extends Comparable<? super T>> T[] exactlyOnce(T[] A) { 
      // RT = O(nlogn).
      // Ex: A = {6,3,4,5,3,5}.  exactlyOnce(A) returns {6,4}
   }
```
**Solution:** [ExactlyOnce.java](https://github.com/rahul1947/SP04-Binary-Search-Tree/blob/master/ExactlyOnce.java)


**Problem 6.** 
   Given an array A of integers, find the length of a longest streak of
   consecutive integers that occur in A (not necessarily contiguously):
```
   static int longestStreak(int[] A) { // RT = O(nlogn).
      // Ex: A = {1,7,9,4,1,7,4,8,7,1}.  longestStreak(A) return 3,
      // corresponding to the streak {7,8,9} of consecutive integers
      // that occur somewhere in A.
   }
```
**Solution:** [LongestStreak.java](https://github.com/rahul1947/SP04-Binary-Search-Tree/blob/master/LongestStreak.java)
