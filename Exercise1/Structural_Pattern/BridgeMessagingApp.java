import java.util.Scanner;

// Implementor (Message Sending Protocol)
interface MessageSender {
    void sendMessage(String message, String recipient);
}

// Concrete Implementor 1: SMS
class SMSSender implements MessageSender {
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

// Concrete Implementor 2: Email
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

// Refined Abstraction 1: Text Message
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

// Refined Abstraction 2: Image Message
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

        // Choose sender
        MessageSender sender;
        if (protocol.equals("sms")) sender = new SMSSender();
        else sender = new EmailSender();

        // Choose message type
        Message message;
        if (type.equals("text")) message = new TextMessage(sender);
        else message = new ImageMessage(sender);

        // Send the message
        message.send(content, recipient);
    }
}