package top.haidong556.ac.exception;

public class ArgsException extends BaseException{
    public ArgsException( String msg) {
        super(1222, msg);
    }

    public ArgsException(String msg, Throwable cause) {
        super(222, msg, cause);
    }
}
