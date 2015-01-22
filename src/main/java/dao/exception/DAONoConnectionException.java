package dao.exception;

public class DAONoConnectionException extends Exception{
    public DAONoConnectionException(String message) {
        super(message);
    }
}
