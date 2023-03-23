package command;

public class PasteCommand implements Command {

    private Receiver receiver;

    public PasteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.paste();
    }
}
