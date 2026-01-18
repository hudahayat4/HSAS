package customer;

import java.sql.Timestamp;
import java.util.Random;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class VerifyService {

    // Generate & send code
    public String generateAndSendCode(String email) {
        try {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Timestamp expiry = new Timestamp(now.getTime() + (5 * 60 * 1000)); // 5 minit expiry

            // Generate 6-digit code
            String code = String.valueOf(100000 + new Random().nextInt(900000));

            // Simpan ke DB
            CustomerDAO.saveVerificationCode(email.trim(), code, expiry);

            // Log untuk debug
            System.out.println("generateAndSendCode → Email: " + email + " | Code: " + code + " | Expiry: " + expiry);

            // Hantar email
            sendVerificationEmail(email.trim(), code);

            return "Verification code has been sent to " + email;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to generate verification code.";
        }
    }

    // Verify code
    public boolean verifyCode(String email, String inputCode) {
        try {
            boolean valid = CustomerDAO.isCodeValid(email.trim(), inputCode.trim());
            System.out.println("verifyCode → Email: " + email + " | Input: " + inputCode + " | Valid: " + valid);

            if (valid) {
                CustomerDAO.markAsVerified(email.trim());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Send email via Gmail SMTP
    private void sendVerificationEmail(String to, String code) {
        final String fromEmail = "juz.care.26@gmail.com"; // Gmail company email
        final String appPassword = "dwxx fxxd cqit icnq"; // Gmail App Password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Juzcare Pharmacy"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your Juzcare Verification Code");
            message.setText("Your verification code is: " + code +
                            "\n\nThis code will expire in 5 minutes.");

            Transport.send(message);
            System.out.println("sendVerificationEmail → Email sent successfully to " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}