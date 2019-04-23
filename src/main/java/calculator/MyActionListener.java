package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {

    private char signOperator = '0';
    private int argument = 0;
    private double result = 0;
    private boolean isPreviousNumeric = false;
    String text = "";

    private void setDefaults() {
        signOperator = '0';
        argument = 0;
        result = 0;
        isPreviousNumeric = false;

    }

    private void printParameters() {
        System.out.println(signOperator);
        System.out.println("res " + result);
        System.out.println("arg " + argument);
        System.out.println(isPreviousNumeric);
    }

    private void displayText(String text) {
        Calculator.textField.setText(text);
    }

    private void checkSignOperator() {
        if (signOperator == '0') result = argument;
        else if (signOperator == '+') result += argument;
        else if (signOperator == '-') result -= argument;
        else if (signOperator == '*') result *= argument;
        else if (signOperator == '/') result /= argument;
    }

    private void concat() {
        if (signOperator != '=') {
            checkSignOperator();
        }
    }

    private void settingsSignOperators(char pressed) {
        signOperator = pressed;
        isPreviousNumeric = false;
        argument = 0;
    }

    public void actionPerformed(ActionEvent arg0) {

        char pressed = arg0.getActionCommand().charAt(0);

        if (pressed != '=' && pressed != 'C') {
            text = text + String.valueOf(pressed);
            displayText(text);
        }

        switch (pressed) {
            case '+':
                concat();
                settingsSignOperators(pressed);
                break;
            case '-':
                concat();
                settingsSignOperators(pressed);
                break;
            case '*':
                concat();
                settingsSignOperators(pressed);
                break;
            case '/':
                concat();
                settingsSignOperators(pressed);
                break;
            case 'C':
                setDefaults();
                System.out.println();
                text = "";
                displayText(String.valueOf(text));
                break;
            case '=':
                checkSignOperator();
                settingsSignOperators(pressed);

                displayText(String.valueOf(result));
                System.out.print("\n" + result);
                text = String.valueOf(result);
                break;

            default: // for digits

                if (signOperator == '=') {
                    if (argument != 0) {
                        argument = Integer.parseInt(String.valueOf(argument) + String.valueOf(pressed));
                    } else {
                        argument = Integer.parseInt(String.valueOf(pressed));
                    }
                    result = argument;
                    text = String.valueOf((int) result);
                    displayText(text);
                } else if (argument != 0) {
                    argument = Integer.parseInt(String.valueOf(argument) + String.valueOf(pressed));
                    displayText(text);

                } else {

                    try {
                        argument = Integer.parseInt(String.valueOf(pressed));
                        if (argument == 0 && signOperator == '/')
                            throw new DivisionByZeroException("\nDivision by zero, cannot perform this action.");
                    } catch (DivisionByZeroException de) {
                        argument = 1;
                        signOperator = '=';
                        System.err.println(de.getText());
                        displayText(String.valueOf(result));
                        argument = 0;
                    }

                    isPreviousNumeric = true;

                }

                break;

        }
    }

}