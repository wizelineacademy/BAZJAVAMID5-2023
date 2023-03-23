package iterator;

public class Channel {

    private double frequency;
    private String type;

    public Channel(double freq, String type){
        this.frequency = freq;
        this.type = type;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return "Frequency = " + this.frequency + ", Type = " + this.type;
    }
}