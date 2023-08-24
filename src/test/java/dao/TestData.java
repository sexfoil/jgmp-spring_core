package dao;

import model.*;

import java.util.Date;
import java.util.Map;

public class TestData {

    public static Map<Long, Event> storeEventsData(Map<Long, Event> map) {
        for (int i = 1; i < 6; i++) {
            map.put((long) i, getEvent(i));
        }
        return map;
    }

    public static Event getEvent(int num) {
        Event event = new EventImpl();
        event.setId(num);
        event.setTitle("Event_" + num);
        event.setDate(new Date((169200 + num * 10L) * 10_000_000L));
        return event;
    }

    public static Map<Long, Ticket> storeTicketsData(Map<Long, Ticket> map) {
        for (int i = 1; i < 6; i++) {
            map.put((long) i, getTicket(i));
        }
        return map;
    }

    private static Ticket getTicket(int num) {
        Ticket ticket = new TicketImpl();
        ticket.setId(num);
        ticket.setEventId(10 + num);
        ticket.setUserId(num);
        ticket.setPlace(100 + num);
        ticket.setCategory(Ticket.Category.BAR);
        return ticket;
    }

    public static Map<Long, User> storeUsersData(Map<Long, User> map) {
        for (int i = 1; i < 6; i++) {
            map.put((long) i, getUser(i));
        }
        return map;
    }

    private static User getUser(int num) {
        User user = new UserImpl();
        user.setId(num);
        user.setEmail("user_" + num + "@mail.com");
        user.setName("user_" + num + "_name");
        return user;
    }
}
