package calculator;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ListenerTests {
    private Character[] input;
    private String expectedResult;
    private MyActionListener myActionListener;

    public ListenerTests(Pair parameters) {
        this.input = parameters.getInput();
        this.expectedResult = parameters.getResult();
    }

    @Before
    public void initialize() {
        myActionListener = new MyActionListener(new Calculator());
    }

    @Parameterized.Parameters
    public static Collection pressingScenarios() {
        return Arrays.asList(
                new Pair(new Character[]{'0', '/', '0'}, "Unspecified value."),
                new Pair(new Character[]{'1', '2', '/', '0'}, "Division by zero!"),
                new Pair(new Character[]{'1', '2', '3', '-', '*', '-', '5', '0', '='}, "73"),
                new Pair(new Character[]{'1', '0', '/', '+', '/', '*', '/', '4', '='}, "2,5000"));
    }

    @Test
    public void testMyActionListenerScenarios() {
        Arrays.stream(input).forEach(myActionListener::onClick);
        Assertions.assertEquals(expectedResult, myActionListener.getRememberedText());
    }

}
