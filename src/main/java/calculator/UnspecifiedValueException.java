package calculator;

public class UnspecifiedValueException extends Exception {
    private String text;

    UnspecifiedValueException(String text) {
        this.text = text;
    }
    String getText() {
        return text;
    }
}
