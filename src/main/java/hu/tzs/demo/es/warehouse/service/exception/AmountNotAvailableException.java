package hu.tzs.demo.es.warehouse.service.exception;

public class AmountNotAvailableException extends Exception {

    public AmountNotAvailableException() {
    }

    public AmountNotAvailableException(String message) {
        super(message);
    }

    public AmountNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
