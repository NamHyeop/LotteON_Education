package LotteON_Education.subject.baseballteamsubject.Exception;

public class NotExistUser extends RuntimeException{
    public NotExistUser() {
        super();
    }

    public NotExistUser(String message) {
        super(message);
    }

    public NotExistUser(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistUser(Throwable cause) {
        super(cause);
    }
}
