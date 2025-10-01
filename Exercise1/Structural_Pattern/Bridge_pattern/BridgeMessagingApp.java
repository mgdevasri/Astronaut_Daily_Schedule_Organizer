import java.util.Scanner;

interface MessageSender {
    void sendMessage(String message, String recipient);
}
//sms
class SMSSender implements MessageSender {
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

//  Email
class EmailSender implements MessageSender {
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}

// Abstraction
abstract class Message {
    protected MessageSender sender;

    public Message(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void send(String message, String recipient);
}

//  Text Message
class TextMessage extends Message {
    public TextMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String message, String recipient) {
        System.out.println("[TextMessage]");
        sender.sendMessage(message, recipient);
    }
}

// Image Message
class ImageMessage extends Message {
    public ImageMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String message, String recipient) {
        System.out.println("[ImageMessage]");
        sender.sendMessage(message, recipient);
    }
}

// Client
public class BridgeMessagingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter recipient: ");
        String recipient = sc.nextLine();

        System.out.print("Enter message content: ");
        String content = sc.nextLine();

        System.out.print("Choose message type (text/image): ");
        String type = sc.nextLine().toLowerCase();

        System.out.print("Choose protocol (sms/email): ");
        String protocol = sc.nextLine().toLowerCase();

       
        MessageSender sender;
        if (protocol.equals("sms")) sender = new SMSSender();
        else sender = new EmailSender();

       
        Message message;
        if (type.equals("text")) message = new TextMessage(sender);
        else message = new ImageMessage(sender);

        // Send the message
        message.send(content, recipient);
    }
}