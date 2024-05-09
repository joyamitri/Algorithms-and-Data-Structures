package CountSum;

public class CountSum {
    /* Explanation:
	 * 
	 * Subproblem: M(i) is maximum continuous sum from 0 -> i INCLUDING number at i
	 * 
	 * Solution: pick number @ i alone or with the sum before it or if the addition makes the sum negative then put a 0 since 
	 * it will not be picked because whatever number after it is, it will be taken alone if it is positive since with the 
	 * negative sum, it will be less than if it was taken alone. Store the current max in a variable and update it if a bigger
	 * one appears.
	 * 
	 * Relation: if array[i]+DP[i-1]>0: DP[i]=array[i]+DP[i-1]
	 * 			 else DP[i]=0
	 * 
	 * Topological order => increasing i from 1 to length of array
	 * 
	 * Base case => DP[0] = array[0];
	 * 
	 * Time complexity: O(n)
	 */
	
	public static void maximumContSum(int[] array) {
		int[] dp = new int[array.length];
		int end=0;
		int temp_start=-1;
		int start=-1;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<array.length;i++){
			if(i==0){
				dp[i]=array[i];
				
			}else{
				dp[i]=dp[i-1]+array[i];
			}
			if(max<dp[i]){
				max=dp[i];
				end=i;
			}
			if(dp[i]<0){
				dp[i]=0;
				if(temp_start<end){
					start=temp_start;
				}					
				temp_start=-1;
			}else{
				if(temp_start==-1){
					temp_start=i;
				}
			}
		}
		if(temp_start<end && start!=temp_start){
			start=temp_start;
		}
		System.out.print("Largest sum contiguous subarray: [ ");
		int i=start;
		while(i<=end){
			if(i==end){
				System.out.print(array[i] +" ");
			}else{
				System.out.print(array[i] + ", ");
			}
			i++;
		}
		System.out.println(" ]");
		if(max<0){
			System.out.println("All numbers are negative and the biggest one is: "+ max);
		}else{
			System.out.println("Max: "+max);
		}
	}
	public static void printArray(int[] array) {
		System.out.print("Array: [ ");
		for (int i = 0; i < array.length; i++) {
			if(i==array.length-1){
				System.out.print(array[i]+" ");
			}else{
				System.out.print(array[i] + ", ");
			}
		}
		System.out.println(" ]");
	}
	public static void main(String[] args) {
		int[] array = {-2, 1,-3, 4,-1, 2, 1, -5 , 4};
		printArray(array);
		maximumContSum(array);
	}
}
