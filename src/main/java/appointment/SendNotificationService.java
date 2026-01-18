package appointment;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class SendNotificationService {

    private final String fromEmail = "juz.care.26@gmail.com";
    private final String appPassword = "dwxx fxxd cqit icnq";

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });
    }

    private boolean sendEmail(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(fromEmail, "Health Screening Appointment Reminder"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Reminder email sent to " + to);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reminder 2 days before
    public boolean sendTwoDaysReminder(String email, String apptDate, String apptTime) {
        String subject = "Health Screening Appointment Reminder – Your appointment in 2 days";
        String body = "Dear Customer,\n\n" +
                      "This is a reminder that you have a health screening appointment scheduled in 2 days:\n" +
                      "Date: " + apptDate + "\nTime: " + apptTime + "\n\n" +
                      "Please ensure you arrive on time.\n\n" +
                      "Kind regards,\nJuzCare Pharmacy Team";
        return sendEmail(email, subject, body);
    }

    // Reminder 1 day before
    public boolean sendOneDayReminder(String email, String apptDate, String apptTime) {
        String subject = "Health Screening Appointment Reminder – Your appointment tomorrow";
        String body = "Dear Customer,\n\n" +
                      "This is a reminder that you have a health screening appointment scheduled for tomorrow:\n" +
                      "Date: " + apptDate + "\nTime: " + apptTime + "\n\n" +
                      "We look forward to seeing you.\n\n" +
                      "Kind regards,\nJuzCare Pharmacy Team";
        return sendEmail(email, subject, body);
    }

    // Reminder 2 hours before
    public boolean sendTwoHoursReminder(String email, String apptDate, String apptTime) {
        String subject = "Health Screening Appointment Reminder – Your appointment in 2 hours";
        String body = "Dear Customer,\n\n" +
                      "This is a reminder that your health screening appointment will take place in 2 hours:\n" +
                      "Date: " + apptDate + "\nTime: " + apptTime + "\n\n" +
                      "Please make your way to the clinic in good time.\n\n" +
                      "Best regards,\nJuzCare Pharmacy Team";
        return sendEmail(email, subject, body);
    }
}
