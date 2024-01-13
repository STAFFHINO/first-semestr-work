package todoapp.exceptions;

public class TodoException extends RuntimeException{
    public TodoException(String message){
        super(message);
    }
}