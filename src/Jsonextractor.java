import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;

public class Jsonextractor {

	private Gson gson = new Gson();
	private String path = "C://Users//Jey//workspace//Calender//src//example.json";
	private Meetinglist readList = new Meetinglist();
	
	public List<?> readList() throws IOException  {
		
		Reader reader = new FileReader(new File(this.path));
		readList = gson.fromJson(reader, Meetinglist.class);
		return readList.getList();
	}
	
	
	public void saveList(){
		
		try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(readList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


	
	
	
	

}
