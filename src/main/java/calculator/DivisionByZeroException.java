package calculator;

public class DivisionByZeroException extends RuntimeException {
    private String text;

    public DivisionByZeroException(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
