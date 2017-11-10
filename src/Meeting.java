
public class Meeting {
	
	
	private String firstName= "";
	private String lastName=""; 
	private CDate date=null;
	private boolean onLeapYear = false;

	public Meeting(){
		
	}
	
	public Meeting(String fName, String lName, CDate bDay) {

		this.firstName = fName; this.lastName = lName; this.date = bDay;

	}
	
	public Meeting(String description, CDate date) {

		this.firstName  = description; this.date = date;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CDate getDate() {
		return date;
	}

	public void setDate(CDate date) {
		this.date = date;
	}
	
	public boolean isOnLeapYear(){
		return this.onLeapYear;
	}
	
	public void setOnLeapYear(boolean value){
		this.onLeapYear = value;
	}
	
	public String toString(){
		return this.getFirstName() + " " + this.getLastName() + " " + this.getDate().getDay()+ "." + this.getDate().getMonth()+ "." + this.getDate().getYear();
	}
	
	
}
