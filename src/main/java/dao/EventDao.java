package dao;

import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repository.EventStorage;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventDao {

    private EventStorage eventStorage;

    public Event getEventById(long id) {
        return eventStorage.getStorageMap().get(id);
    }

    public List<Event> getEventsByTitle(String title) {
        return eventStorage.getStorageMap().values().stream()
                .filter(event -> event.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsForDay(Date day) {
        return eventStorage.getStorageMap().values().stream()
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
    }

    public Event createEvent(Event event) {
        long id = eventStorage.getNextId();
        event.setId(id);
        eventStorage.getStorageMap().put(id, event);
        return getEventById(id);
    }

    public Event updateEvent(Event event) {
        Event oldEvent = getEventById(event.getId());
        if (oldEvent == null) {
            return null; // better to use Optional
         }
        eventStorage.getStorageMap().put(oldEvent.getId(), event);
        return getEventById(oldEvent.getId());
    }

    public boolean deleteEvent(long eventId) {
        Event removed = eventStorage.getStorageMap().remove(eventId);
        return removed != null;
    }

    @Autowired
    void setEventStorage(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }
}
