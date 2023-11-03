public final class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	} 

	public int getCurrentSize() {
		return numberOfEntries;
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.next = firstNode;
		firstNode = newNode;
		numberOfEntries++;
		return true;
	}

	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next;
			numberOfEntries--;
		}
		return result;
	}
	
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null) {
			nodeN.data = firstNode.data;
			firstNode = firstNode.next;
			numberOfEntries--;
			result = true;
		}
		return result;
	} 

	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}

	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int counter = 0;
		Node currentNode = firstNode;
		while ((counter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				frequency++;
			}
			counter++;
			currentNode = currentNode.next;
		}
		return frequency;
	} 
	
	public boolean contains(T anEntry) {
		boolean doesContain = false;
		Node currentNode = firstNode;
		while (!doesContain && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				doesContain = true;
			}
			else {
				currentNode = currentNode.next;
			}
		}
		return doesContain;
	}
	
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] result = (T[]) new Object[numberOfEntries];
		int i = 0;
		Node currentNode = firstNode;
		while ((i < numberOfEntries) && (currentNode != null)) {
			result[i] = currentNode.data;
			i++;
			currentNode = currentNode.next;
		}
		return result;
	}

	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			}
			else {
				currentNode = currentNode.next;
			}
		}
		return currentNode;
		}
	
	private class Node {
		private T data;
		private Node next;
		private Node(T dataPortion) {
			this(dataPortion, null);
		} 
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} 
	}
	
	
	/*
	 * creates 2 arrays: here and there
	 * for every entry in each array, the entry gets added to the result bag
	 * the result bag contains the combined contents of the bag that received the call and the method argument 
	 * the result bag is returned
	 */
	public BagInterface<T> union(BagInterface<T> everything) {
	   BagInterface<T> result = new LinkedBag <>();
	   T[] here = this.toArray();
	   for (T entry : here) {
		   result.add(entry);
	   }
	   T[] there = everything.toArray();
	   for (T entry : there) {
		   result.add(entry);
	   }
	   return result;
	}
	
	/*
	 * creates 2 arrays: here and there, and 2 bags: tempBag and result
	 * the here array stores the contents of the bag that received the call in tempBag
	 * the there array stores the contents of the method argument in result
	 * for each entry in the there array, the if statement checks if the entry is also in the here array and adds it to the result bag if it contains the entry
	 * the result bag is returned
	 */
	public BagInterface<T> intersection(BagInterface<T> commonItems) {
		   BagInterface<T> tempBag = new LinkedBag <>();
		   BagInterface<T> result = new LinkedBag <>();
		   T[] here = this.toArray();
		   for (T entry : here) {
			   tempBag.add(entry);
		   }
		   T[] there = commonItems.toArray();
		   for (T entry : there) {
			   if(tempBag.contains(entry)){
				   if(result.contains(entry)) {
					   ;
				   }
				   else {
					   result.add(entry);
				   }
			   }
		   }
		   return result;
	   }
	
	/*
	 * creates 2 arrays: here and there
	 * the here array stores the contents of the bag that received the call in result
	 * the there array removes the contents of the method argument from result
	 * for each entry in the there array, the if statement checks if the entry is already in the result bag and removes it from result, or does nothing if it is not
	 * the result bag is returned
	 */
	public BagInterface<T> difference(BagInterface<T> leftOver) {
	   BagInterface<T> result = new LinkedBag <>();
	   T[] here = this.toArray();
	   for (T entry : here) {
		   result.add(entry);
	   }
	   T[] there = leftOver.toArray();
	   for (T entry : there) {
		   if(result.contains(entry)) {
			   result.remove(entry);
		   }
	   }
	   return result;
	}
}