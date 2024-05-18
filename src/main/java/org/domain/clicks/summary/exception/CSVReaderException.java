package org.domain.clicks.summary.exception;

public class CSVReaderException extends RuntimeException {
    public CSVReaderException(String message) {
        super(message);
    }

    public CSVReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
