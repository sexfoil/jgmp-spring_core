package it;

import config.AppConfig;
import facade.BookingFacade;
import model.Event;
import model.EventImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
public class ITBookingFacadeImpl {

    @Autowired
    private BookingFacade bookingFacade;


    @Test
    void test() {
        assertEquals(6, getCurrentEvents().size());

        boolean isDeleted = bookingFacade.deleteEvent(1L);
        assertTrue(isDeleted);
        assertEquals(5, getCurrentEvents().size());

        Event newEvent = new EventImpl();
        newEvent.setTitle("Event_new");

        bookingFacade.createEvent(newEvent);
        assertEquals(6, getCurrentEvents().size());
        assertEquals(7, bookingFacade.getEventsByTitle("Event_new", 10, 10).get(0).getId());
    }

    private List<Event> getCurrentEvents() {
        return bookingFacade.getEventsByTitle("Event", 10, 10);
    }
}
