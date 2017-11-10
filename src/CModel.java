import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;

public class CModel {

	List<Meeting> meetingList = new LinkedList<Meeting>();
	private int selectedday = 0;
	private int start = 0;
	private int end = 0;
	private int selectedlabel = -1; // the index of selected label
	CDate date = new CDate();
	Jsonextractor ex = new Jsonextractor();

	public CModel() {
		date.setActualDate();
	}

	public void addPerson(Meeting newPerson) {
		meetingList.add(newPerson);

	}

	public List<Integer> generateCalendarList(int year, int month) {

		List<Integer> calvalues = new LinkedList<Integer>();
		int actualmonthdays = date.amountOfDays(month, year);
		int offset = getOffset();

		while (offset < getPreviousMonthDays())
			calvalues.add(++offset);
		this.start = calvalues.size();
		for (int i = 1; i <= actualmonthdays; i++) {
			calvalues.add(i);
		}

		this.end = calvalues.size() - 1;

		for (int i = 1; calvalues.size() < 42; i++) {
			calvalues.add(i);
		}

		return calvalues;
	}

	public int getOffset() {
		int offsetAmount = date.getFirstDayOFMonth();
		if (offsetAmount == 0) {
			offsetAmount = 7;
		}
		return getPreviousMonthDays() - offsetAmount;
	}

	public int getPreviousMonthDays() {
		int prevmonth = date.getMonth() - 1;
		if ((prevmonth) < 1)
			prevmonth = 12;
		return date.amountOfDays(prevmonth, date.getYear());
	}

	public void setPreviousMonth() {
		int prevmonth = date.getMonth();
		prevmonth -= 1;
		if ((prevmonth) < 1) {
			prevmonth = 12;
			date.setYear(date.getYear() - 1);
			checkAndModifyLeapBirthday();
			checkAndRemodifyLeapBirthday();
		}
		date.setMonth(prevmonth);
	}

	public void setNextMonth() {
		int nextmonth = date.getMonth();
		nextmonth += 1;
		if ((nextmonth) > 12) {
			nextmonth = 1;
			date.setYear(date.getYear() + 1);
			checkAndModifyLeapBirthday();
			checkAndRemodifyLeapBirthday();
		}
		date.setMonth(nextmonth);
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public int getSelectedLabel() {
		return selectedlabel;
	}

	public void setSelectedLabel(int selectedlabel) {
		this.selectedlabel = selectedlabel;
	}

	public int getSelectedDay() {
		return selectedday;
	}

	public void setSelectedDay() {
		this.selectedday = getSelectedLabel() - getStart() + 1;
	}

	public boolean isMatch(Calendar comparedate) {
		boolean ret = false;
		if (comparedate.get(Calendar.YEAR) == this.date.getYear()
				&& (comparedate.get(Calendar.MONTH) + 1) == (date.getMonth()))
			ret = true;
		return ret;
	}
	
	public List<Meeting> getList(){
		List<Meeting> meetingListTemp = new LinkedList<Meeting>();
		for(Meeting meeting : this.meetingList){
			meetingListTemp.add(meeting);
		}
		return meetingListTemp;
	}
	
	public void checkAndModifyLeapBirthday(){
		if(date.isLeapYear() ){	
			for(Meeting meeting:meetingList){
				if(meeting.isOnLeapYear()){
					meeting.getDate().setDay(29);
					meeting.getDate().setMonth(2);
				}
			}
		}
	}
	
	public void checkAndRemodifyLeapBirthday(){
		if(!date.isLeapYear() ){	
			for(Meeting meeting:meetingList ){
				if(meeting.isOnLeapYear() ){
					meeting.getDate().setDay(1);
					meeting.getDate().setMonth(3);
				}
			}
		}
	}
	
	public boolean addMeeting(Meeting meeting){
		return meetingList.add(meeting);
	}
	
	public void save() {
		for(Meeting meeting:meetingList){
			if(meeting.isOnLeapYear()){
				meeting.getDate().setDay(29);
				meeting.getDate().setMonth(2);
			}
		}
		ex.saveList();
	}

	
	@SuppressWarnings("unchecked")
	public void readfile() {
		
			try {
				this.meetingList = (List<Meeting>) ex.readList();
				checkAndModifyLeapBirthday();
				checkAndRemodifyLeapBirthday();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			

	}

}
