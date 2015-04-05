
public class Student {
	private String name;
	private int units;
	private int[] grades;

	
	public String getName(){
		return name;
	}
	
	public int getUnits(){
		return units;
	}
	
	public int[] getGrades(){
		return grades;
	}
	
	public void setName(String nStr){
		name = nStr;
	}
	
	public void setUnits(int u){
		units = u;
	}
	
	public void setGrades(int[] gArray){
		grades = gArray;
	}
}
