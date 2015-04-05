
public class JavaGenerics {
	public static void main(String[] args){
		
		Integer[] iarrary = {1, 2, 3, 4};
		Character[] carrary={'a', 'b', 'c', 'd'};
		
		Integer i = printArray(iarrary);
		System.out.printf("last element of array: %s", i);
		System.out.println();
		Character c = printArray(carrary);
		System.out.printf("last element of array: %s", c);
		System.out.println();
		System.out.println(max(43, 56, 78));
		System.out.println(max("Apple", "Orange", "banana"));
	}
	
	public static <T> T printArray(T[] arry){
		T result = arry[0];
		for (T a: arry){
			System.out.printf("%s ", a);
			result = a;
		}
		System.out.println();
		return result;
	}

	public static <T extends Comparable<T>> T max(T a, T b, T c){
		T m = a;
		if (b.compareTo(a) > 0){
			m = b;
		}
		
		if (c.compareTo(m) > 0){
			m = c;
		}
		
		return m;
	}
}
