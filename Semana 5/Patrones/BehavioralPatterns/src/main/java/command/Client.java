package command;

public class Client {

    public static void main(String[] args) {
        Receiver openedFile = new Receiver();

        Invoker copyToolbarButton = new Invoker();
        copyToolbarButton.setCommand(new CopyCommand(openedFile));
        copyToolbarButton.executeCommand();

        Invoker pasteToolbarButton = new Invoker();
        pasteToolbarButton.setCommand(new PasteCommand(openedFile));
        pasteToolbarButton.executeCommand();


        Invoker cutMouseButton = new Invoker();
        cutMouseButton.setCommand(() -> openedFile.cut());
        cutMouseButton.executeCommand();

        Invoker pasteMouseButton = new Invoker();
        pasteMouseButton.setCommand(() -> openedFile.paste());
        pasteMouseButton.executeCommand();
    }
}
