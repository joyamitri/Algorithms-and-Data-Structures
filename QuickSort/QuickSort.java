package QuickSort;

public class QuickSort {
    public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int partition(int[] array, int left, int right) {
		
		// O(n)   we will touch O(n) elements in the array (specifically in worst case we will touch n + 3 elements)
		
		int pivot_index = left;
		int left_pointer = left +1;
		int right_pointer = right;
		
		System.out.print("Before:	");
		printWithPivot(array, pivot_index);
		
		while (left_pointer <= right_pointer) {
			// important to have left_pointer <= right_pointer, otherwise this becomes an O(n^2) 
			while (left_pointer < right && left_pointer <= right_pointer && array[left_pointer] <= array[pivot_index]) {
				left_pointer++;
			}
			// important to have right_pointer >= left_pointer, otherwise this becomes an O(n^2) 
			while (right_pointer >= left_pointer && right_pointer >= left_pointer && right_pointer > left && array[right_pointer] > array[pivot_index]) {
				right_pointer--;
			}
			if (left_pointer < right_pointer) {
				swap(array, left_pointer, right_pointer);
			}else {
				break;
			}
		}
		swap(array, pivot_index, right_pointer);
		return right_pointer;
	}
	public void sort(int[] array, int left, int right) {
		if (right - left < 1) {
			return;
		}

		int rank = partition(array, left, right);
		
		System.out.print("After:	");
		printWithPivot(array, rank);

		sort(array, left, rank - 1);
		sort(array, rank + 1, right);

	}

	public void printWithPivot(int[] array, int pivot_index) {
		System.out.print("[ ");
		for (int i = 0; i < array.length; i++) {
			System.out.print((i == pivot_index ? "|_" : "") + array[i] + (i == pivot_index ? "_|" : "")
					+ (i == array.length - 1 ? "" : ", "));
		}
		System.out.println(" ]\n");
	}

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		
		// O(nlg(n)) Expected Average case 
		
		// O(n^2) (this input produces worst case) 
		int[] array = {10, 9, 8, 7, 6, 5};
		qs.sort(array, 0, array.length - 1);
	}
}
