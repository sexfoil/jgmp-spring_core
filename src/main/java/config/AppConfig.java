package config;

import dao.EventDao;
import dao.TicketDao;
import dao.UserDao;
import facade.BookingFacade;
import facade.BookingFacadeImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import repository.EventStorage;
import repository.TicketStorage;
import repository.UserStorage;
import service.EventService;
import service.TicketService;
import service.UserService;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${app.storage.event.path}")
    private String eventStorageFilePath;
    @Value("${app.storage.ticket.path}")
    private String ticketStorageFilePath;
    @Value("${app.storage.user.path}")
    private String userStorageFilePath;


    @Bean
    public BookingFacade bookingFacade() {
        return new BookingFacadeImpl(eventService(), ticketService(), userService());
    }


    @Bean
    public EventStorage eventStorage() {
        return new EventStorage(eventStorageFilePath);
    }

    @Bean
    public TicketStorage ticketStorage() {
        return new TicketStorage(ticketStorageFilePath);
    }

    @Bean
    public UserStorage userStorage() {
        return new UserStorage(userStorageFilePath);
    }

    @Bean
    public EventService eventService() {
        return new EventService();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public EventDao eventDao() {
        return new EventDao();
    }

    @Bean
    public TicketDao ticketDao() {
        return new TicketDao();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }
}
