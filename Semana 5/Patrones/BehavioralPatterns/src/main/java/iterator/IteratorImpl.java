package iterator;

import java.util.List;

public class IteratorImpl implements Iterator {

    private String type;
    private List<Channel> channels;
    private int position;

    public IteratorImpl(String type, List<Channel> channelsList) {
        this.type = type;
        this.channels = channelsList;
    }

    @Override
    public boolean hasNext() {
        while (position < channels.size()) {
            Channel c = channels.get(position);
            if (c.getType().equals(type) || type.equals("ALL")) {
                return true;
            } else
                position++;
        }
        return false;
    }

    @Override
    public Channel next() {
        Channel c = channels.get(position);
        position++;
        return c;
    }

}