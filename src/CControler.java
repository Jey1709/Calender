import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CControler {

	protected static final String HBox = null;
public CModel model;
	public CView view;
	List<Meeting> birthdaylist = new LinkedList<Meeting>();
	
	public CControler() {
		createGUI();
		this.model = new CModel();
		loadFile();
		update();
		displaybirthdays(model.date.getDay() + model.getStart() - 1);
	}

	public CView createGUI() {
		this.view = new CView(new VBox(), this);
		return view;
	}

	public void nextMonth() {
		this.model.setNextMonth();
		birthdaylist.clear();
	}

	public void prevMonth() {
		this.model.setPreviousMonth();
		birthdaylist.clear();
	}

	public void setListener() {

		this.view.backbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				prevMonth();
				update();
			}
		});

		this.view.forwardbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				nextMonth();
				update();
			}
		});
		
		this.view.addpersonbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Label fnlab = new Label("First Name:");
				Label lnlab = new Label("Last Name:");
				Label bdaylab = new Label("Birthday:    ");
				TextField firstNameField = new TextField ();
				TextField lastNameField = new TextField ();
				TextField birthdayField = new TextField ();
				HBox hb1 = new HBox();
				HBox hb2 = new HBox();
				HBox hb3 = new HBox();
				hb1.setSpacing(10);
				hb2.setSpacing(10);
				hb3.setSpacing(10);
				VBox vb = new VBox();
				hb1.getChildren().addAll(fnlab, firstNameField);
				hb2.getChildren().addAll(lnlab, lastNameField);
				hb3.getChildren().addAll(bdaylab, birthdayField);
				vb.getChildren().addAll(hb1,hb2,hb3);
				
				Stage stage = new Stage();
				Scene scene = new Scene(vb);
				stage.setScene(scene);
				stage.show();
			}
		});

		for (Label lab : view.labellist) {
			lab.setOnMousePressed(new EventHandler<Event>() {

				public void handle(Event event) {
					selectLabel(view.labellist.indexOf(lab));
				}

			});
		}
	}

	public void selectLabel(int labindex) {
		if (model.getSelectedLabel() == -1) {
			view.selectLabel(labindex);
			model.setSelectedLabel(labindex);
			model.setSelectedDay();
			displaybirthdays(labindex);
			
		} else {
			int previndex = model.getSelectedLabel();
			view.disselectLabel(previndex);
			selectActualDay();
			view.selectLabel(labindex);
			model.setSelectedLabel(labindex);
			model.setSelectedDay();
			displaybirthdays(labindex);
		}
		
		int overflow = getOverflow(labindex - model.getStart() + 1);
		if(overflow < 0){
			prevMonth();
			update();
			selectLabel(model.getEnd()+overflow+1);
		}else if(overflow > 0){
			nextMonth();
			update();
			selectLabel(model.getStart()+overflow-1);
		}
	}
	
	
	

	public void update() {

		int year = model.date.getYear();
		int month = model.date.getMonth();
		List<Integer> list = this.model.generateCalendarList(year, month);
		view.clearCalendar();
		this.view.fillCalendarList(list, model.getStart(), model.getEnd());
		this.view.setActualMonthLabel(month, model.date.getYear());
		selectBirthDays();
		selectActualDay();
		setListener();
	}

	public void selectActualDay() {

		if (model.isMatch(Calendar.getInstance())) {
			int day = model.date.getDay() + model.getStart() - 1;
			view.selectActualDayLabel(day);
		}
	}
	
	public void selectBirthDays() {
		List<Meeting>birthdays = model.getList();
		int day = 0;
		int actualmonth = this.model.date.getMonth();
		int comparemonth = 0;
		for(Meeting meeting: birthdays){
			comparemonth = meeting.getDate().getMonth();
			if(actualmonth == comparemonth){
				day = meeting.getDate().getDay();
				
				if(!birthdaylist.contains(meeting)){
					birthdaylist.add(meeting);
				}
				day += model.getStart() -1;
				view.setBirthdayLabel(day);
			}	
		}
	}
	
	public void displaybirthdays(int selectedlabel){
		
		int day = selectedlabel- model.getStart()+1;
		for(Meeting birthday:birthdaylist){
			if(birthday.getDate().getDay() == day)
				System.out.println(birthday.getFirstName()+ " "+birthday.getLastName());
		}
	}
	
	
	
	public int getOverflow(int overflow) {
		
		int ret = 0;
		if (overflow < 1) {
			ret = overflow-1;
		} else if (overflow > (model.getEnd() - model.getStart() + 1)) {
			overflow %= model.date.amountOfDays(model.date.getMonth(), model.date.getYear());
			ret = overflow;
		}
		return ret;

	}
	
	public boolean addToMeetingList(String firstName,String lastName,int day, int month, int year){
		Meeting m = new Meeting();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		CDate date = new CDate(day,month,year);
		if(date.isLeapYear() && date.getDay() == 29 && date.getMonth() == 2)
			m.setOnLeapYear(true);
		m.setDate(date);
		return model.addMeeting(m);
	}
	
	public void loadFile(){
		model.readfile();
	}
	
	public void saveState(){
		model.save();
	}

}
