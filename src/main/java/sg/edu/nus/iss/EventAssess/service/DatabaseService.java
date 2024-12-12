package sg.edu.nus.iss.EventAssess.service; // rename as redisRepo, no service layer

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

import sg.edu.nus.iss.EventAssess.model.Event;
import sg.edu.nus.iss.EventAssess.repo.KeyRepo;
import sg.edu.nus.iss.EventAssess.repo.ListRepo;

@Service
public class DatabaseService {
    @Autowired
    ListRepo listRepo;

    @Autowired
    KeyRepo keyRepo;

    public List<Event> readFile(String filename) {
        try (BufferedReader breader = new BufferedReader(new FileReader(filename))) {
            System.out.println("File Contents:");
            String line;
            while ((line = breader.readLine()) != null) {
                System.out.println(line);
            } 
            JsonReader reader = Json.createReader(new FileReader(filename));
            JsonArray eventArray = reader.readArray();
            List<Event> events = new ArrayList<>();
            for (JsonValue eventValue : eventArray) {
                JsonObject eventJson = eventValue.asJsonObject();
                Event event = createEvent(eventJson.toString());
                events.add(event);
            }
            return events;
        } catch (Exception e) {
            System.err.println("Error loading todo data: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public Event createEvent(String eventJsonString) {
        JSONObject eventJson = new JSONObject(eventJsonString);
        Event event = new Event();
        event.setEventId(eventJson.getInt("eventId"));
        event.setEventName(eventJson.getString("eventName"));
        event.setEventSize(eventJson.getInt("eventSize"));
        event.setEventDate(new Date(eventJson.getLong("eventDate")));
        event.setParticpants(eventJson.getInt("particpants"));
        return event;
    }

    public void addAllToBackList(String key, List<Object> values) {
        listRepo.addAllToBackList(key, values);
    }

    public List<Object> getList(String key) {
        return listRepo.getList(key);
    }


    public List<Event> getAllEvents() {
        List<Object> events = getList("events");
        List<Event> eventList = new ArrayList<>();
        
        if (events != null) {
            for (Object EventJson : events) {
                try {
                    // Convert JSON string into ToDo object using JSONObject
                    JSONObject jsonObject = new JSONObject(EventJson.toString()); // Changed 'json' to 'EventJson'
                    Event todo = new Event();
                    todo.setEventId(jsonObject.optInt("eventId")); // optint use default if int missing, getInt throws exception
                    todo.setEventName(jsonObject.optString("eventName"));
                    todo.setEventSize(jsonObject.optInt("eventSize"));
                    Date eventDate = new Date(jsonObject.getLong("eventDate"));
                    todo.setEventDate(eventDate);
                    eventList.add(todo); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return eventList;
    }



    public void saveRecord(Event event) {
        String key = "events";
        listRepo.addToBackList(key, event.toJsonString()); //remember stringserizalizer only accepts strings
    }

    public Long getNumberofEvents() {
        return listRepo.sizeOfList("events");
    }

    public void getEvent(Integer index) {
        listRepo.getValueAtIndex("events", index);
    }
    
}
