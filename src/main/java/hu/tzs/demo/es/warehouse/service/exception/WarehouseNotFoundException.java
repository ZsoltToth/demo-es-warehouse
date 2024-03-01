package hu.tzs.demo.es.warehouse.service.exception;

public class WarehouseNotFoundException extends Exception {

    public WarehouseNotFoundException() {
        super();
    }

    public WarehouseNotFoundException(String message) {
        super(message);
    }

    public WarehouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
