package ch.fhnw.swa.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntryNotFoundException extends Exception{
    public EntryNotFoundException(String message) {
        super(message);
    }
    public EntryNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
