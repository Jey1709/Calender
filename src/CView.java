
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Paint;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CView extends Scene {

	public CControler controler;
	int actualmonth;
	Color labcolor;
	Color textcolor;
	Color filltextcolor;
	Color selectcolor;
	Paint actualcolor;//cornercolor before select
	Insets corner = new Insets(0.0);
	Insets cornerSelect = new Insets(2.5);
	public double minSizeWidth = 50;
	public double minSizeHeight = 50;
	public double maxSizeWidth = 200;
	public double maxSizeHeight = 200;
	public Label monthlab;
	Button backbtn;
	Button forwardbtn;
	Button addpersonbtn;
	public HBox personline = new HBox();
	public HBox functionline = new HBox();
	public HBox dateline = new HBox();
	public HBox line1 = new HBox();
	public HBox line2 = new HBox();
	public HBox line3 = new HBox();
	public HBox line4 = new HBox();
	public HBox line5 = new HBox();
	public HBox line6 = new HBox();
	List<Label> monthlabellist;
	List<Label> labellist;
	
	/**
	 * Constructor of View
	 * @param parent is the root Node of the scene
	 * @param controler is the instance of the CControler class
	 */
	public CView(VBox parent, CControler controler) {
		super(parent);
		this.controler = controler;
		init(parent);
	}

	/**
	 * initialising the window elements and add the Node elements to root
	 * @param parent is the root Node of the scene
	 */
	public void init(VBox parent) {
	
		labcolor = Color.BLACK;
		textcolor = Color.LIMEGREEN;
		filltextcolor = Color.GREY;
		selectcolor = Color.GREY;
		parent.getChildren().add(personline);
		parent.getChildren().add(functionline);
		parent.getChildren().add(dateline);
		parent.getChildren().add(line1);
		parent.getChildren().add(line2);
		parent.getChildren().add(line3);
		parent.getChildren().add(line4);
		parent.getChildren().add(line5);
		parent.getChildren().add(line6);
		
		addpersonbtn = new Button(" + ");
		
		
		backbtn = new Button("<<");
		BackgroundFill fills = new BackgroundFill(Color.DARKGRAY, null, null);
		Background value = new Background(fills);
		backbtn.setBackground(value);
		backbtn.setTextFill(Color.RED);
		addpersonbtn.setTextFill(Color.RED);
		
		monthlab = new Label();
		monthlab.setTextFill(Color.DARKBLUE);
		monthlab.setMinWidth(284);
		monthlab.setAlignment(Pos.BASELINE_CENTER);
		forwardbtn = new Button(">>");
		forwardbtn.setBackground(value);
		forwardbtn.setTextFill(Color.RED);

		personline.getChildren().addAll(addpersonbtn);
		functionline.getChildren().addAll(backbtn, monthlab, forwardbtn);
		
		monthlabellist = new LinkedList<Label>();
		labellist = new LinkedList<Label>();

		Label mo = new Label("Mo");
		Label tu = new Label("Tu");
		Label we = new Label("We");
		Label th = new Label("Th");
		Label fr = new Label("Fr");
		Label sa = new Label("Sa");
		Label su = new Label("Su");

		mo.setMinSize(minSizeWidth, minSizeHeight);
		tu.setMinSize(minSizeWidth, minSizeHeight);
		we.setMinSize(minSizeWidth, minSizeHeight);
		th.setMinSize(minSizeWidth, minSizeHeight);
		fr.setMinSize(minSizeWidth, minSizeHeight);
		sa.setMinSize(minSizeWidth, minSizeHeight);
		su.setMinSize(minSizeWidth, minSizeHeight);
		monthlabellist.add(mo);
		monthlabellist.add(tu);
		monthlabellist.add(we);
		monthlabellist.add(th);
		monthlabellist.add(fr);
		monthlabellist.add(sa);
		monthlabellist.add(su);

		for (int i = 0; i < 7; i++) {
			Label lab = monthlabellist.get(i);
			lab.setMinSize(minSizeWidth, minSizeHeight);
			lab.setMaxSize(maxSizeWidth, maxSizeHeight);

			BackgroundFill fill = new BackgroundFill(labcolor, null, corner);

			Background bg = new Background(fill);

			lab.setTextFill(Color.RED);
			lab.setAlignment(Pos.BASELINE_CENTER);
			lab.setBackground(bg);
			dateline.getChildren().add(lab);

		}

	}

	/**
	 * Sets the properties of each labelelement 
	 * @param list contains the numbers of the month
	 * @param start index of the first day of the month
	 * @param end index of the last day of the month
	 */
	public void fillCalendarList(List<Integer> list, int start, int end) {

		for (int i = 0; i <= list.size() - 1; i++) {
			Label lab = new Label("" + list.get(i));
			lab.setMinSize(minSizeWidth, minSizeHeight);
			lab.setMaxSize(maxSizeWidth, maxSizeHeight);
			BackgroundFill fill = new BackgroundFill(labcolor, null, corner);
			Background bg = new Background(fill);
			if(i<start || i > end)
				lab.setTextFill(filltextcolor);
			else 
				lab.setTextFill(textcolor);
			lab.setBackground(bg);
			labellist.add(lab);
		}
		generateCalender();

	}
	
	/**
	 * Adds the daylabel elements to the field
	 */
	public void generateCalender() {

		for (Label label : labellist) {

			label.setAlignment(Pos.BASELINE_CENTER);

			if (this.line1.getChildren().size() < 7)
				this.line1.getChildren().add(label);

			else if (this.line2.getChildren().size() < 7)
				this.line2.getChildren().add(label);

			else if (this.line3.getChildren().size() < 7)
				this.line3.getChildren().add(label);

			else if (this.line4.getChildren().size() < 7)
				this.line4.getChildren().add(label);

			else if (this.line5.getChildren().size() < 7)
				this.line5.getChildren().add(label);
			
			else if (this.line6.getChildren().size() < 7)
				this.line6.getChildren().add(label);
		}
	}

	/**
	 * Displays the actual month as string on the windowlabel
	 * @param month to display
	 * @param year to display
	 */
	public void setActualMonthLabel(int month,int year) {
		actualmonth = month;
		switch (actualmonth) {
		case 1:
			monthlab.setText("JANUAR"+" " + year);
			break;
		case 2:
			monthlab.setText("FEBRUAR"+" " + year);
			break;
		case 3:
			monthlab.setText("MÄRZ"+" " + year);
			break;
		case 4:
			monthlab.setText("APRIL"+" " + year);
			break;
		case 5:
			monthlab.setText("MAY"+" " + year);
			break;
		case 6:
			monthlab.setText("JUNI"+" " + year);
			break;
		case 7:
			monthlab.setText("JULI"+" " + year);
			break;
		case 8:
			monthlab.setText("AUGUST"+" " + year);
			break;
		case 9:
			monthlab.setText("SEPTEMBER"+" " + year);
			break;
		case 10:
			monthlab.setText("OKTOBER"+" " + year);
			break;
		case 11:
			monthlab.setText("NOVEMBER"+" " + year);
			break;
		case 12:
			monthlab.setText("DEZEMBER"+" " + year);
			break;
		}
	}

	/**
	 * clears the displayed days
	 */
	public void clearCalendar() {
		this.labellist.clear();
		this.line1.getChildren().clear();
		this.line2.getChildren().clear();
		this.line3.getChildren().clear();
		this.line4.getChildren().clear();
		this.line5.getChildren().clear();
		this.line6.getChildren().clear();
	}
	
	/**
	 * selects the label of the actual day
	 * @param i index of actual day
	 */
	public void selectActualDayLabel(int i){
		Label lab = labellist.get(i);
		Paint actualcolor = lab.getBackground().getFills().get(0).getFill();
		Border border = modifyBorder(Color.RED);
		lab.setBorder(border);
		lab.setBackground(new Background(new BackgroundFill(actualcolor,null,null)));
	}
	
	public void setBirthdayLabel(int i){
		if(i<0) ;
		
		Label lab = labellist.get(i);
		lab.setBackground(new Background(new BackgroundFill(Color.DARKGREEN,null,corner)));
	}
	
	/**
	 * set the backgroundobject 
	 * @param color sets the color of the background 
	 * @return the backgroundobject with modified color
	 */
	public Background modifyBackgroundcolor(Object color){
		
		BackgroundFill fills = new BackgroundFill((Color)color, null, corner);
		Background bg = new Background(fills);
		return bg;
	}
	
	//change the borderlayout of each labelobject at once 
	public Border modifyBorder(Color color){
		Paint cornercolor = color;
		BorderWidths widths = new BorderWidths(2);
		Border border = new Border(new BorderStroke(cornercolor,BorderStrokeStyle.SOLID,null, widths));
		return border;
	}
	
	
	public void selectLabel(int index){
		actualcolor = labellist.get(index).getBackground().getFills().get(0).getFill();
		labellist.get(index).setBorder(modifyBorder(selectcolor));
	}
	
	public void disselectLabel(int index){
		labellist.get(index).setBorder(modifyBorder((Color)actualcolor));
	}
	
	
}
