package sg.edu.nus.iss.EventAssess.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.json.Json;

public class Event {
    private Integer eventId;
    private String eventName;
    private Integer eventSize;
    private Date eventDate; // in milliseconds
    private Integer particpants; // number of people who have registered to attend event
    
    public Event(Integer eventId, String eventName, Integer eventSize, Date eventDate, Integer particpants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.particpants = particpants;
    }

    public Event() {
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventSize() {
        return eventSize;
    }

    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getParticpants() {
        return particpants;
    }

    public void setParticpants(Integer particpants) {
        this.particpants = particpants;
    }

    public String toJsonString() {
        return Json.createObjectBuilder()
            .add("eventId", eventId)
            .add("eventName", eventName)
            .add("eventSize", eventSize)
            .add("eventDate", eventDate.getTime())
            .add("particpants", particpants)
            .build().toString();
    }

    public String simpleDate(){
         if (eventDate != null) {
            long epochMilliseconds = eventDate.getTime();
            Date retrievedDate = new Date(epochMilliseconds);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(retrievedDate);
        }
        return "";
    }
    

}
