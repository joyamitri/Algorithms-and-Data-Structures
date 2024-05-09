package Functions;

public class Functions {
    private class LinkedList{
		private class Node{
			private int val;
			private Node next;
			public Node(int v) {
				setVal(v);
				setNext(null);
			}
			public Node getNext() {
				return next;
			}
			public void setNext(Node next) {
				this.next = next;
			}
			public int getVal() {
				return val;
			}
			public void setVal(int val) {
				this.val = val;
			}
		}
	
		private Node header;
		private Node last;
		
		public LinkedList() {header = null; last = null;}
		public void add(int v) {
			Node n = new Node(v);
			if(header == null) {
				header = n;
				last = n;
			}
			else {
				last.setNext(n);
				last = n;
			}
		}
		
		public int getLast() {
			// i can just return last.val, but dr. azar said no :/
			if(header == null) return -1;
			Node curr = header;
			while(curr.getNext() != null) {
				curr = curr.getNext();
			}
			
			return curr.getVal();
		}
	}
	
	
	public static int partA(int i, int j, int[][] matrix) {
		// get value at matrix[i][j]
		// O(1)

		return matrix[i][j];
	}
	
	public static void partB(int[] array) {
		// reverse array "IN PLACE"
		
		// Each iteration we increment i and decrement j, thus 2 elements have been reverse, thus input size - 2
		// T(N) = T(N - 2) + 1          AND  T(0) = 1
		// T(N) = T(N - 2K) + K   assume  n=2k => k=n/2
		// T(N) = T(0) + N/2 =  O(N)

		if(array.length <= 1) return;
		partBHelper(array, 0, array.length - 1);
	}
	public static void partBHelper(int[] array, int i, int j) {
		if(i>j) return;
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
		partBHelper(array, i+1, j-1);
	}
	
	
	
	public static int partC(int[] array) {
		// return largest value in array
		// T(N) = T(N-1) + 1
		// T(N) = T(N-K) + K  ASSUME N=K 
		// T(N) = 1 + N = O(N)
		
		return partCHelper(array, Integer.MIN_VALUE, 0);
	}
	public static int partCHelper(int[] array, int curr_max, int i) {
		if(i >= array.length) return curr_max;
		return partCHelper(array, curr_max > array[i] ? curr_max : array[i], i+1);
	}
	
	
	
	public static int partD(int[] array) {
		// return the last value in the array
		// O(1)
		if(array.length > 1){
			return array[array.length - 1];
		}
		return 0;
	}
	
	public static int partE(LinkedList L) {
		// return the last value in the linked list
		// T(N) = T(N-1) + 1
		// T(N) = T(N-K) + K   ASSUME N = K
		// T(N) = 1 + N = O(N)
		return L.getLast();
	}
	
	public static void main(String[] args) {
		
		// **** PART A **********
		
		System.out.println("------- PART A ------\n");
		int[][] matrix = {{1, 2, 3}, {1, 3, 4}, {4, 2, 1}, {5, 2, 5}};
		System.out.println(Functions.partA(3,2, matrix)); // Expect 5
		
		// *********************
		
		// **** PART B **********
		
		System.out.println("------- PART B ------\n");
		int[] array1 = {1, 2, 3, 4, 5, 6};
		Functions.partB(array1);
		System.out.print("[ ");
		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i] + " ,");
		}
		System.out.println(" ]");
		
		// *********************
		
		// **** PART C **********
		
		System.out.println("------- PART C ------\n");
		int[] array2 = {101, 420, 51, -12, 5, 12};
		System.out.println(Functions.partC(array2)); // Expect 420
		
		// *********************
		
		// **** PART D **********
		
		System.out.println("------- PART D ------\n");
		int[] array3 = {101, 420, 51, -12, 5, 12};
		System.out.println(Functions.partD(array3)); // Expect 12
		
		// *********************
		
		// **** PART E **********
		
		System.out.println("------- PART E ------\n");
		Functions q = new Functions();
		LinkedList L = q.new LinkedList();
		L.add(14);
		L.add(51);
		L.add(2);
		System.out.println(Functions.partE(L)); // Expect 2
		
		// *********************

	}
}
