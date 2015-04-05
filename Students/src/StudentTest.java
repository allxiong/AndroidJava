
public class StudentTest {
	public static void main(String[] args){
		Student John = new Student();
		John.setName("John Smith");
		John.setUnits(12);
		int j_grades[] = {75, 95, 86, 91, 67, 100};
		John.setGrades(j_grades);

		//print out John's name
		System.out.println("Name: "+John.getName());
		//print out John's units
		System.out.println("total units: "+John.getUnits());
		//print out John's grades
		int[] grades = John.getGrades();
		for (int i=0; i<grades.length; i++){
			System.out.println(John.getName()+" grade: "+grades[i]);
		}
	}
}
