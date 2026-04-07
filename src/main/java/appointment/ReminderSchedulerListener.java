package appointment;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ReminderSchedulerListener implements ServletContextListener {

    private ReminderSchedulerListener scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Bila Tomcat start projek, scheduler akan hidup
        scheduler = new ReminderSchedulerListener();
        System.out.println("ReminderScheduler started...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Optional: stop scheduler bila server shutdown
        scheduler = null;
        System.out.println("ReminderScheduler stopped...");
    }
}
