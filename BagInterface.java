public interface BagInterface <T> {
	// gets current number of entries in the bag and returns an integer
	public int getCurrentSize();
	
	// sees if the bag is empty and returns true or false
	public boolean isEmpty();
	
	// adds an entry to the bag and returns true or false
	public boolean add(T newEntry);
	
	// removes an unspecified entry from the bag and returns the entry or null
	public T remove();
	
	// removes a specified entry from the bag and returns true or false
	public boolean remove(T anEntry);
	
	// removes all entries in the bag
	public void clear();
	
	// counts the number of times an entry appears in the bag and returns an integer
	public int getFrequencyOf(T anEntry);
	
	// tests if the bag contains an entry and returns true or false
	public boolean contains(T anEntry);
	
	// retrieves all entries and makes a newly allocated array with the entries or an empty array
	public T[] toArray();
	
	// the union is all entries of both bags combined into a new bag
	public BagInterface<T> union(BagInterface<T> everything);
	
	// the intersection is a new bag of entries that occurred in both bags
	public BagInterface<T> intersection(BagInterface<T> commonItems);
	
	// the difference is the entries left over after removing duplicates in a new bag
	public BagInterface<T> difference(BagInterface<T> leftOver);
	
}