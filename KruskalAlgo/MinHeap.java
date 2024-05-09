package KruskalAlgo;

public class MinHeap {
    private Edge[] heap;
	private int heap_size;
	
	
	
	public int getHeapSize() {
		return heap_size;
	}
	
	public MinHeap() {
		heap = new Edge[5];
		heap_size = 0;
	}	
	public void swap(int i, int j) {
		// O(1)
		Edge temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}	
	public int left(int i) {
		// O(1)
		return 2 * i;
	}
	public int right(int i) {
		// O(1)
		return 2 * i + 1;
	}
	public int parent(int i) {
		// O(1)
		return i / 2;
	}
	
	public boolean isEmpty() {
		return heap_size == 0;
	}
	
	private void minHeapify(int i) {
		// O(height(i)), thus worst case, if i==1, then this in the worst case would take O(log(n))
		
		int l = left(i);
		int r = right(i);
		int smallest_idx = i;
		if( l <= heap_size && heap[smallest_idx].compareTo(heap[l]) > 0) {
			smallest_idx = l;
		}
		if( r <= heap_size && heap[smallest_idx].compareTo(heap[r]) > 0) {
			smallest_idx = r;
		}
		if(smallest_idx != i) {
			swap(smallest_idx, i);
			minHeapify(smallest_idx);
		}
	}
	public void buildMinHeap(Edge[] edges) {
		// O(n)
		// override old heap with new values
		
		heap = new Edge[edges.length + 1];
		heap_size = 0;
		
		for (int i = 0; i < edges.length; i++) {
			heap[i + 1] = edges[i];
			heap_size++;
		}
		for (int i = heap_size / 2; i > 0; i--) {
			minHeapify(i);
		}
	}

	public Edge extractMin() {
		// O(lg(n))
		if(heap_size == 0) return null;
		Edge min = heap[1];
		swap(1, heap_size);
		heap_size--;
		minHeapify(1);
		
		return min;
	}

	public void swim(int i) {
		// O(log(n))
		if(i <= 1) return;
		if(heap[parent(i)].compareTo(heap[i]) > 0 || heap[parent(i)].compareTo(heap[i]) == 0) {
			swap(parent(i), i);
			swim(parent(i));
		}
	}
}
