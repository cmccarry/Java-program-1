import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T> {
   private T[] bag;
   private static final int DEFAULT_CAPACITY = 4;
   private int totalCapacity;
   private int numberOfEntries;

   public ResizeableArrayBag()
   {
       this(DEFAULT_CAPACITY);
   }

   @SuppressWarnings("unchecked")
   public ResizeableArrayBag(int capacity) {
       totalCapacity = capacity;
       numberOfEntries = 0;
       T[] tempBag = (T[]) new Object[capacity];
       bag = tempBag;
   }

   @SuppressWarnings("unchecked")
   public ResizeableArrayBag(ResizeableArrayBag <T> nextBag)
   {
       T[] tempBag = (T[]) new Object[nextBag.getCapacity()];
       totalCapacity = nextBag.getCapacity();
       bag = tempBag;
       numberOfEntries = nextBag.numberOfEntries;
       for (int index = 0; index < nextBag.numberOfEntries; ++index) {
           T entryShift = (T) nextBag.bag[index];
           bag[index] = entryShift;
       }
   }

   public int getCurrentSize() {
       return numberOfEntries;
   }

   public int getCapacity() {
       return totalCapacity;
   }

   public void resize(int newCapacity) {
       if (totalCapacity > newCapacity) {
           return;
       }
       else {
           bag = Arrays.copyOf(bag, newCapacity);
           totalCapacity = newCapacity;
       }
   }

   public boolean isEmpty() {
       return numberOfEntries == 0;
   }

   
   public boolean isFull() {
       if (totalCapacity == numberOfEntries) {
           return true;
       }
       return false;
   }

   public boolean add(T newEntry) {
       if (isFull()) {
           resize(2 * totalCapacity);
           bag[numberOfEntries] = newEntry;
           numberOfEntries++;
           return true;
       }
       else {
           bag[numberOfEntries] = newEntry;
           numberOfEntries++;
           if (isFull()) {
               return true;
           }
           else {
               return false;
           }
       }
   }

   public T remove() {
       return null;
   }
   
   public boolean remove(T anItem) {
       int i = 0;
       while (i < numberOfEntries) {
           if (bag[i].equals(anItem)) {
               if (i < numberOfEntries) {
                   bag[i] = bag[numberOfEntries-1];
                   bag[numberOfEntries-1] = null;
                   numberOfEntries--;
                   return true;
               }
           }
           i++;
       }
       return false;
   }

   public void clear()
   {
       for (int i = 0; i < numberOfEntries; i++) {
           bag[i] = null;
       }
       numberOfEntries = 0;
   }

   public int getFrequencyOf(T anItem) {
       int counter = 0;
       for (int i = 0; i < numberOfEntries; i++) {
           if (bag[i].equals(anItem)) {
               counter++;
           }
       }
       return counter;
   }
   
   public boolean contains(T anItem) {
       if (isEmpty()) {
    	   return false;
       }
       else {
           int i = 0;
           while (i < numberOfEntries) {
               if (bag[i] == anItem) {
                   return true;
               }
               i++;
           }
       }
       return false;
   }

   @SuppressWarnings("unchecked")
   public T[] toArray() {
       T[] result = (T[]) new Object[numberOfEntries];
       for (int i = 0; i < numberOfEntries; i++) {
           result[i] = bag[i];
       }
       return result;
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
	   BagInterface<T> tempBag = new ResizeableArrayBag <>();
	   BagInterface<T> result = new ResizeableArrayBag <>();
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
	   BagInterface<T> result = new ResizeableArrayBag <>();
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