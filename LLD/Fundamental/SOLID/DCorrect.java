package LLD.Fundamental.SOLID;


// ---------------------------------- Dependency Inversion Principle ----------------------------------
// Class should depend on interfaces / abstract classes rather than concrete classes

// ---------------------------------- Correct Example ----------------------------------
interface EmailClient {
    public void sendEmail(String message);
}

class GmailClient implements EmailClient {
    public void sendEmail(String message) {
        System.out.println("Gmail: Sending email: " + message);
    }
}

class OutlookClient implements EmailClient {
    public void sendEmail(String message) {
        System.out.println("Email: Sending email: " + message);
    }
}

class EmailService {
    private EmailClient emailClient; // Concrete class depends on interface

    public EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void sendEmail(String message) {
        emailClient.sendEmail(message);
    }
}

class Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailService(new GmailClient());
        emailService.sendEmail("Hello, this is a test email , from gmail");

        emailService = new EmailService(new OutlookClient());
        emailService.sendEmail("Hello, this is a test email , from outlook");
    }
}
