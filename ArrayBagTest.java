import java.util.Arrays;
public class ArrayBagTest {
	public static void main(String[] args) {
		BagInterface<String> bag1 = new ResizeableArrayBag <>();
	    BagInterface<String> bag2 = new ResizeableArrayBag <>();
		BagInterface<Integer> intBag1 = new ResizeableArrayBag <>();
		BagInterface<Integer> intBag2 = new ResizeableArrayBag <>();
		
	    bag1.add("A");
		bag1.add("B");
		bag1.add("C");
		bag2.add("B");
		bag2.add("B");
		bag2.add("D");
		bag2.add("E");
		
		intBag1.add(1);
		intBag1.add(2);
		intBag1.add(3);
		intBag2.add(2);
		intBag2.add(2);
		intBag2.add(4);
		intBag2.add(5);
		
		System.out.println("Union: " + Arrays.toString(bag1.union(bag2).toArray()));
	    System.out.println("Intersection: " + Arrays.toString(bag1.intersection(bag2).toArray()));
	    System.out.println("Difference: " + Arrays.toString(bag1.difference(bag2).toArray()));
	    System.out.println("Difference: " + Arrays.toString(bag2.difference(bag1).toArray()));
	    
		System.out.println("Union: " + Arrays.toString(intBag1.union(intBag2).toArray()));
	    System.out.println("Intersection: " + Arrays.toString(intBag1.intersection(intBag2).toArray()));
	    System.out.println("Difference: " + Arrays.toString(intBag1.difference(intBag2).toArray()));
	    System.out.println("Difference: " + Arrays.toString(intBag2.difference(intBag1).toArray()));
    }
}