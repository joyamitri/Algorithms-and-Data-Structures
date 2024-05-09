package IthOrder;

public class minHeap {
    class MinHeap{
		int array[];
		int heap_size;
		public MinHeap(int [] a) {
			//O(nlog(n))
			
			array = new int[a.length + 1];
			heap_size = a.length;
			for (int i = 1; i < array.length; i++) {
				array[i] = a[i - 1];
			}
			buildHeap();
		}
		private int right(int i) {
			return 2 * i + 1;
		}
		private int left(int i) {
			return 2 * i;
		}
		private void minHeapify(int i) {
			// O(height(i)) => O(log(n)) worst case

			int l = left(i);
			int r = right(i);
			int smallest;
			if(l <= heap_size && array[l] < array[i]) {
				smallest=l;
			}else smallest = i;
			if(r <= heap_size && array[r] < array[smallest]) {
				smallest=r;
			}
			if(smallest != i) {
				int temp = array[i];
				array[i] = array[smallest];
				array[smallest] = temp;
				minHeapify(smallest);
			}
		}
		private void buildHeap() {
			// O(nlog(n))
			for (int i = array.length/2; i >= 1; i--) {
				minHeapify(i);
			}
		}
		public int extarctMin() {
			// O(height(1)) = O(height of tree) = O(log(n))
			
			if(heap_size <= 0) return Integer.MIN_VALUE;
			int min = array[1];
			array[1] = array[heap_size--];
			minHeapify(1);
			return min;
		}
	}
	public static void main(String[] args) {
		minHeap q2 = new minHeap();
		int[] array = {2,5,6,8,3,10,11,13};
		System.out.println("6th smallest element is: " + q2.orderStatistics(array, 6));
		System.out.println("1st smallest element is: " + q2.orderStatistics(array, 1));
		System.out.println("2nd smallest element is: " + q2.orderStatistics(array, 2));
		System.out.println("8th smallest element is: " + q2.orderStatistics(array, 8));
	}
	
	
	
	// O(MAX(k * log(N), N)) worst case if K = N => O(N*log(N))
	private int orderStatistics(int[] array, int k) {
		
		// O(n) to build heap 
		MinHeap mh = new MinHeap(array);
		
		int orderStatistic = array[0];

		// O(k * log(n))
		for (int i = 0; i < k; i++) {
			orderStatistic = mh.extarctMin();
		}
		return orderStatistic;
	}
}
