package PriorityQueue;

import java.util.ArrayList;

public class PriorityQueue {
	private Job[] heap;
	private int heap_size;
	
	public int getHeapSize() {
		return heap_size;
	}
	
	public PriorityQueue() {
		heap = new Job[5];
		heap_size = 0;
	}	
	public void swap(int i, int j) {
		// O(1)
		Job temp = heap[i];
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
	public void buildMinHeap(Job[] jobs) {
		// O(nlog(n)) since we are calling minHeapify n/2 times and minHeapify takes log(n)
		// override old heap with new values
		
		heap = new Job[jobs.length + 1];
		heap_size = 0;
		
		for (int i = 0; i < jobs.length; i++) {
			heap[i + 1] = jobs[i];
			heap_size++;
		}
		for (int i = heap_size / 2; i > 0; i--) {
			minHeapify(i);
		}
	}
	
	public boolean remove(String name) {
		// O(n) to find the node to remove it
		if(heap_size == 0) return false;
		int index = -1;
		for (int i = 1; i < heap_size; i++) {
			if(heap[i].getName().equals(name)) {
				index = i;
				break;
			}
		}
		if(index == -1) return false;
		
		
		swap(index, heap_size);
		heap_size--;
		minHeapify(index);
		ensureCapacity();
		return true;
	}
	public Job getMaximum() {
		// O(n) (n is the number of nodes in the heap)
		if(heap_size == 0) return null;
		Job max = heap[heap_size];
		for (int i = (heap_size/2) + 1; i <= heap_size; i++) {
			if(max.compareTo(heap[i]) < 0) max = heap[i];
		}
		
		return max;
	}
	public Job getMinimum() {
		// O(1)
		if(heap_size == 0) return null;
		return heap[1];
	}

	private Job extractMin() {
		// O(lg(n))
		if(heap_size == 0) return null;
		Job min = heap[1];
		swap(1, heap_size);
		heap_size--;
		minHeapify(1);
		
		return min;
	}
	public Job[] sortDecreasing() {
		// O(nlog(n))
		Job[] original_heap = heap.clone();
		int original_heap_size = heap_size;
		
		Job[] jobs = new Job[heap_size];
		for (int i = jobs.length - 1; i >= 0; i--) {
			jobs[i] = extractMin();
		}
		heap = original_heap;
		heap_size = original_heap_size;
		return jobs;
	}
	public Job[] sortIncreasing() {
		//O(nlog(n))
		Job[] original_heap = heap.clone();
		int original_heap_size = heap_size;
		
		Job[] jobs = new Job[heap_size];
		for (int i = 0 ; i < jobs.length; i++) {
			jobs[i] = extractMin();
		}
		
		heap = original_heap;
		heap_size = original_heap_size;
		return jobs;
	}

	
	private int getHeight(int i) {
		// O(lg(n))
		if(heap_size == 0) return 0;
		if(i >= heap_size) return 0;
		return 1 + getHeight(left(i));
	}
	public ArrayList<ArrayList<String>> getDrawableTree() {
		// O(2^lg(n)) = O(n)
		int tree_height = getHeight(1);
		ArrayList<ArrayList<String>> tree = new ArrayList<ArrayList<String>>();
		int w = 1;
		for (int i = 0; i < tree_height + 1; i++) {
			ArrayList<String> level =  new ArrayList<String>();
			for (int j = 0; j < Math.pow(2, i); j++) {
				if(w > heap_size) {
					level.add("");
				}else {
					level.add(heap[w].specialString());
					w++;
				}
			}
			tree.add(level);
		}
		return tree;
	}

	public void swim(int i) {
		// O(log(n))
		if(i <= 1) return;
		if(heap[parent(i)].compareTo(heap[i]) > 0 || heap[parent(i)].compareTo(heap[i]) == 0) {
			swap(parent(i), i);
			swim(parent(i));
		}
	}
	public void ensureCapacity() {
		// O(n)
		if(heap_size < heap.length/2) {
			Job[] new_heap = new Job[(heap.length/2) + 1];
			for (int i = 1; i < new_heap.length; i++) {
				new_heap[i] = heap[i];
			}
			heap = new_heap;
		}else {
			Job[] new_heap = new Job[(heap.length*2) + 1];
			for (int i = 1; i < heap.length; i++) {
				new_heap[i] = heap[i];
			}
			heap = new_heap;
		}
	}
	public void insert(Job j) {
		// O(log(n)) AMORTIZED (ensureCapacity runs only once every n inserts and it takes O(n) thus we can amortize it) 
		if(heap_size + 1 >= heap.length) {
			ensureCapacity();
		}
		heap[heap_size + 1] = j;
		heap_size++;
		swim(heap_size);
	}

	public boolean changePriority(String name) {
		// O(n)
		
		// O(n) to find the element
		if(heap_size == 0) return false;
		int index = -1;
		for (int i = 1; i < heap_size; i++) {
			if(heap[i].getName().equals(name)) {
				index = i;
				break;
			}
		}
		if(index == -1) return false;
		
		// O(log(n))
		heap[index].setLength(heap[1].getLength());
		swim(index);
		
		return true;
	}
}
