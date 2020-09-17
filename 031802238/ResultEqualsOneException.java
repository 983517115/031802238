/**
 * 非相同文本，查重率却为1
 */
public class ResultEqualsOneException extends Exception{

    public ResultEqualsOneException() {
    }

    public ResultEqualsOneException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
