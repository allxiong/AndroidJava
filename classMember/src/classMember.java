
public class classMember {
	String name;
	static int members;

	static{
		System.out.println("I am in the static block");
		members = 1;
	}

	public static void main(String[] args){
		classMember John = new classMember();
		John.name = "John Smith";
		John.members++;
		System.out.println("\nJohn.name: "+John.name);
		System.out.println("\nJohn.members: "+John.members);
		
		//create student Mary
		classMember Mary = new classMember();
		Mary.name = "Mary White";
		John.members++;
		System.out.println("\nMary.members: "+Mary.members);

		
	}
}
