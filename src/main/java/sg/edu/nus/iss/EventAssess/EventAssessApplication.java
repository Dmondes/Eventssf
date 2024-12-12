package sg.edu.nus.iss.EventAssess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.EventAssess.model.Event;
import sg.edu.nus.iss.EventAssess.service.DatabaseService;

@SpringBootApplication
public class EventAssessApplication implements CommandLineRunner {

	@Autowired
	private DatabaseService dbService;

	public static void main(String[] args) {
		SpringApplication.run(EventAssessApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Event> events = dbService.readFile(
				"/Users/desmond/Desktop/Visa/02_ssf/wkshps/wkshps/EventAssess/src/main/resources/events.json"); ///Users/desmond/Desktop/Visa/02_ssf/wkshps/wkshps/EventAssess/src/main/resources/events.json"
		for (Event event : events) {
			dbService.saveRecord(event);
		}

	}
}
