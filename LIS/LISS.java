package LIS;

public class LISS {
    /* Explanation:
	 * 
	 * Subproblem: LIS(i) is longest increasing subsequence from i -> array.length  INCLUDING number at i
	 * 
	 * Idea: pick greatest LIS in front of i
	 * 
	 * Relation: If array[i] < array[j] and LIS[i]<LIS[j]+1: LIS[i]=LIS[j]+1 for j going from 0 to i-1
	 * 
	 * Base case: Initialize one dimensional array of the size of the original array with all values equal to 1
	 * 
	 * Topological order => increasing i from 1 till array.length
	 * 
	 * Original problem solution => store the maximum so far (maximum between DP[0] => DP[length-1]) in a variable and return it.
	 * 
	 * Time complexity: O(n^2)
	 */
	public static void LIS(int[] array){
		int[] A=new int[array.length];
		int[] B=new int[array.length];
		print(array);
		int i=0;
		int max_index=0;
		int max = 0;
		for (i = 0; i < array.length; i++)
            A[i] = 1;
		for (i = 0; i < array.length; i++)
            B[i] = i;
		for (i = 1; i < array.length; i++)
	            for (int j = 0; j < i; j++)
	                if (array[i] > array[j] && A[i] < A[j] + 1){
	                    A[i] = A[j] + 1;
	                    B[i]=j;
	                    if(max<A[i]){
	                    	max=A[i];
	                    	max_index=i;
	                    }
	               }
		i=max_index;
		int[] lis=new int[max];
		int j=lis.length-1;
		do{
			lis[j]=array[i];
			i=B[i];
			j--;
			if(i==B[i]){
				lis[j]=array[i];
			}
		}while(i!=B[i]);
		System.out.print("The longest increasing subsequence is: ");
		print(lis);
		System.out.println("The length of the LIS is: "+ max);
	}
	public static void print(int[] array){
		for(int i =0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	public static void main(String[] args){
		int[] array={5,3,8,11,7,18,20,14};
		LIS(array);
	}
}
