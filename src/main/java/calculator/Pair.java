package calculator;

import lombok.Value;

@Value
public class Pair {
    Character [] input;
    String result;

    public Pair(Character[] input, String result) {
        this.input = input;
        this.result = result;
    }
}
