package factorymethod.factory;

import factorymethod.notifications.EmailNotification;
import factorymethod.notifications.Notification;
import factorymethod.notifications.PushNotification;
import factorymethod.notifications.SMSNotification;

public class NotificationFactory {

    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        switch (channel) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown channel " + channel);
        }
    }
}
