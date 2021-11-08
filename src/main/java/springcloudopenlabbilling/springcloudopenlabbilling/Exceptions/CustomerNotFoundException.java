package springcloudopenlabbilling.springcloudopenlabbilling.Exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String messages) {
        super(messages);
    }
}
