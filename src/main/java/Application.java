import config.AppConfig;
import facade.BookingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Application is started...");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);

        bookingFacade.getEventsByTitle("Event", 10, 10);

        Date day = new Date(1691620000000L);
        bookingFacade.getEventsForDay(day, 10, 10);


        bookingFacade.deleteEvent(1L);
        bookingFacade.deleteEvent(10L);
        log.info("Application is finished.");

    }
}
