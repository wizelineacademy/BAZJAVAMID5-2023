package command;

public class CopyCommand implements Command {

    private Receiver receiver;

    public CopyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.copy();
    }
}
