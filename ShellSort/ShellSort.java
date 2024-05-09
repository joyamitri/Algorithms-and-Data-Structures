package ShellSort;

public class ShellSort {
    public static void exchange(int[] array, int i, int j) {
		
		int temp = array[i];
		
		array[i] = array[j];
		
		array[j] = temp;
	}

    public static void shellSort(int[] array) {
		
		int gap = array.length/2;
		
		while(gap > 0) {
			
			for(int i = gap; i < array.length; i++) {
				
				for(int j = i; j >= gap; j-= gap) {
					
					if(array[j] < array[j - gap])
						
						exchange(array, j, j - gap);
				}
			}
			
			gap /= 2;
		}
	}
	
	public static void printArr(int[] arr) {
		
		for(int w = 0; w < arr.length; w++) {
			
			System.out.println(arr[w]);
		}
	}
	
	public static void main(String[] args) {
		
		int[] arr = {23, 34, 62, 37, 48, 68, 74, 65, 10, 45};

        shellSort(arr);
		printArr(arr);
	}
}
