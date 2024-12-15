package LLD.Fundamental.SOLID;

// ---------------------------------- Dependency Inversion Principle ----------------------------------
// Class should depend on interfaces or abstract classes rather than concrete classes

// ---------------------------------- Wrong Example ----------------------------------
class GmailClient {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

class EmailService{
    private GmailClient gmailClient ; // This is wrong classes should on depend on concrete classes

    public EmailService(GmailClient gmailClient){
        this.gmailClient = gmailClient;
    }

    public void sendEmail(String message){
        gmailClient.sendEmail(message);
    }
}