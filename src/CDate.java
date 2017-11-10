import java.util.Calendar;

public class CDate{

	
	private int day = 0;
	private int month = 0;
	private int year = 0;
	
	public CDate(){
		
	}
	
	public CDate(int day, int month,int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean isLeapYear(){
		if (this.year % 4 == 0)
			return true;
		else
			return false;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	@SuppressWarnings("static-access")
	public void setActualDate(){
		Calendar calendar = Calendar.getInstance();
		setDay(calendar.get(Calendar.DATE));
		setMonth(calendar.get(Calendar.MONTH)+1);
		setYear(calendar.get(calendar.YEAR));
	}
	
	public int getFirstDayOFMonth(){
		Calendar temp = Calendar.getInstance();
		int retval = 0;
		
		temp.set(getYear(),getMonth()-1,1);
		int dot = temp.get(Calendar.DAY_OF_WEEK);
		
		switch(dot){
			case Calendar.MONDAY:retval = 0;break;
			case Calendar.TUESDAY:retval = 1;break;
			case Calendar.WEDNESDAY:retval = 2;break;
			case Calendar.THURSDAY:retval = 3;break;
			case Calendar.FRIDAY:retval = 4;break;
			case Calendar.SATURDAY:retval = 5;break;
			case Calendar.SUNDAY:retval = 6;break;
			default : retval = 0;break;
		}
		return retval;
	}
	
	public int amountOfDays(int month, int year) {

		int amount = 0;

		switch (month) {
	
		case 2:
			if (isLeapYear())
				amount = 29;
			else
				amount = 28;
			break;
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:	
		case 10:
		case 12:	
			amount = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			amount = 30;
			break;
		default:
			amount = 30;
			break;

		}

		return amount;
	}
	

}
