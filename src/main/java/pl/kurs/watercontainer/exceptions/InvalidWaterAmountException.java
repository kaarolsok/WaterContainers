package pl.kurs.watercontainer.exceptions;

public class InvalidWaterAmountException extends RuntimeException{
    public InvalidWaterAmountException(String message) {
        super(message);
    }
}
