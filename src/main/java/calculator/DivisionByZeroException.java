package calculator;

class DivisionByZeroException extends RuntimeException {
    private String text;

    DivisionByZeroException(String text) {
        this.text = text;
    }
    String getText() {
        return text;
    }
}
