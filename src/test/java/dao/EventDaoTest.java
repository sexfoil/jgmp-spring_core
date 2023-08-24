package dao;

import model.Event;
import model.EventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.EventStorage;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventDaoTest {

    private final EventDao eventDao = new EventDao();

    @BeforeEach
    void init() {
        EventStorage eventStorage = new EventStorage("");
        TestData.storeEventsData(eventStorage.getStorageMap());
        eventDao.setEventStorage(eventStorage);
    }

    @Test
    void getEventByIdTest_shouldReturnEvent() {
        long id = 1L;
        Event event = eventDao.getEventById(id);

        assertNotNull(event);
        assertEquals(id, event.getId());
    }

    @Test
    void getEventByIdTest_shouldReturnNull() {
        long id = 1000L;
        Event event = eventDao.getEventById(id);

        assertNull(event);
    }

    @Test
    void getEventsByTitle_shouldReturnEventsList() {
        String title = "Event";
        String title2 = "Event_2";
        List<Event> events = eventDao.getEventsByTitle(title);
        List<Event> events2 = eventDao.getEventsByTitle(title2);

        assertFalse(events.isEmpty());
        assertFalse(events2.isEmpty());
        assertEquals(5, events.size());
        assertEquals(1, events2.size());
    }

    @Test
    void getEventsByTitle_shouldReturnEmptyList() {
        String title = "Not_Event";
        List<Event> events = eventDao.getEventsByTitle(title);

        assertTrue(events.isEmpty());
    }

    @Test
    void getEventsForDay_shouldReturnEventsList() {
        Date day = new Date((169200 + 2 * 10L) * 10_000_000L);
        List<Event> events = eventDao.getEventsForDay(day);

        assertFalse(events.isEmpty());
        assertEquals(1, events.size());
        assertEquals(day, events.get(0).getDate());
    }

    @Test
    void getEventsForDay_shouldReturnEmptyList() {
        Date day = new Date();
        List<Event> events = eventDao.getEventsForDay(day);

        assertTrue(events.isEmpty());
    }

    @Test
    void createEvent_shouldReturnEvent() {
        Event event = new EventImpl();
        Event createdEvent = eventDao.createEvent(event);

        assertNotNull(createdEvent);
        assertEquals(6L, event.getId());
    }

    @Test
    void updateEvent_shouldReturnEvent() {
        Event event = eventDao.getEventById(1L);
        event.setTitle("Updated_title");
        Event updateEvent = eventDao.updateEvent(event);

        assertNotNull(updateEvent);
        assertTrue(updateEvent.getTitle().contains("Updated_title"));
    }

    @Test
    void updateEvent_shouldReturnNull() {
        Event event = TestData.getEvent(999);
        Event updateEvent = eventDao.updateEvent(event);

        assertNull(updateEvent);
    }

    @Test
    void deleteEvent_shouldReturnTrue() {
        long id = 1L;
        boolean isDeleted = eventDao.deleteEvent(id);
        Event event = eventDao.getEventById(id);

        assertTrue(isDeleted);
        assertNull(event);
    }
}
