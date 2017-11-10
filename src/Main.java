
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{

	public static void main(String[] args) {
		
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		CControler controler = new CControler();
		stage.setScene(controler.view);
		stage.show();	
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override public void handle(WindowEvent t) {
		        controler.saveState();
		    }
		});
	}
}
