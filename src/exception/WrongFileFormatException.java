package exception;

public class WrongFileFormatException extends Exception {
    public WrongFileFormatException() {
        super("Wrong input file format.");
    }
}
