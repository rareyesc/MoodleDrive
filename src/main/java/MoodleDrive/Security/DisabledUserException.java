package MoodleDrive.Security;

import org.springframework.security.core.AuthenticationException;

public class DisabledUserException extends AuthenticationException {
    public DisabledUserException(String msg) {
        super(msg);
    }
    public DisabledUserException(String msg, Throwable t) {
        super(msg, t);
    }
}
