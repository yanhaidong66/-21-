package top.haidong556.ac.exception;

public class DataBaseException extends BaseException{

    public DataBaseException(String msg) {
        super(1333, msg);
    }

    public DataBaseException(String msg, Throwable cause) {
        super(1333, msg, cause);
    }
}
