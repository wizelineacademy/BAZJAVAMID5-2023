package iterator;

public class Client {

    public static void main(String[] args) {
        ChannelCollection channels = populateChannels();
        Iterator baseIterator = channels.iterator("ALL");
        while (baseIterator.hasNext()) {
            Channel c = baseIterator.next();
            System.out.println(c.toString());
        }
        System.out.println("******");

        // Channel Type Iterator
        Iterator englishIterator = channels.iterator("SPANISH");
        while (englishIterator.hasNext()) {
            Channel c = englishIterator.next();
            System.out.println(c.toString());
        }
    }

    private static ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, "ENGLISH"));
        channels.addChannel(new Channel(99.5, "SPANISH"));
        channels.addChannel(new Channel(100.5, "FRENCH"));
        channels.addChannel(new Channel(101.5, "ENGLISH"));
        channels.addChannel(new Channel(102.5, "SPANISH"));
        channels.addChannel(new Channel(103.5, "FRENCH"));
        channels.addChannel(new Channel(104.5, "ENGLISH"));
        channels.addChannel(new Channel(105.5, "SPANISH"));
        channels.addChannel(new Channel(106.5, "FRENCH"));
        return channels;
    }
}
