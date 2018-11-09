public class BinaryHeap {

	int arr[];
	int size;

	public BinaryHeap () {
		// Create data structure and initalize size to 0
		this.arr = new int[10];
		size = 0;
	}

	void add (int pri) {
		// Next element goes in the next available spot, aka the size of the array
		arr[size++] = pri;
		// If the size is greater than or equal to the array length, going to need more space
		if (size >= arr.length)
			grow_array();

		// Get the index of the position you just inserted an element
		int index = size - 1;

		// Parent of that element
		int parent = (index - 1) / 2;
		// While the parent is greater than index and the index isn't the root
		while (index > 0 && arr[parent] > arr[index]) {
			// Keep swapping
			swap(parent, index);
			index = parent;
			parent = (index - 1) / 2;
		}
	}


	void grow_array() {
		int tempArr[] = new int[arr.length*2];

		// Copy array elements over to an array 2x the size
		for (int i = 0; i < arr.length; i++) {
			tempArr[i] = arr[i];
		}
		
		arr = tempArr;
	}

	int remove() {
		// Can't remove an element that doesn't exist, return -1
		if (size == 0) {
			return -1;
		}

		// Remove the earliest position element
		int pri = arr[0];
		
		// Keep is a complete tree
		arr[0] = arr[--size];		
		shiftdown(0);

		// Return the priority
		return pri;
	}

	void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	void shiftdown(int parent) {
		// Make sure that there are children to actually comapre with
		if (parent * 2 + 1 < size) {
			int child = parent * 2 + 1;
			// Check if there's a right child to compare to
			if (parent * 2 + 2 < size) {
				// If there is, compare the children
				if (arr[child+1] < arr[child] && arr[child+1] != 0) {
					child++;
				}
			}

			// Compare the parent and child, and swap until elements are in order
			if (arr[parent] > arr[child] && arr[child] != 0) {
				swap(parent, child);
				shiftdown(child);
			}
		}
	}
}
