package exception;

public class WrongFormatException extends Exception {
    public WrongFormatException() {
        super("Wrong input data format.");
    }
}
