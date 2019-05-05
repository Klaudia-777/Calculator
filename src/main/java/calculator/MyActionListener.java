package calculator;

import lombok.Getter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

@Getter
public class MyActionListener implements ActionListener {

    DecimalFormat df = new DecimalFormat("####0.0000");

    private Calculator calculator;
    private char signOperator = '0';
    private char rememberedChar = '0';

    private int argument = 0;
    private int rememberedArgument = 0;

    private double result = 0;
    private double rememberedResult = 0;

    private String text = "";
    private String rememberedText = "";
    private boolean isPreviousNumeric = true;

    public MyActionListener(Calculator calculator) {
        this.calculator = calculator;
    }

    private void setDefaults() {                        // clears calculators settings
        text = "0";
        rememberedText = "";
        isPreviousNumeric = false;
        displayText(text);
        signOperator = '0';
        rememberedChar = '0';
        argument = 0;
        result = 0;
    }

    private boolean checkIfDouble(double result) {      // checks if the current result value is double (true) or int (false)
        return result - (int) result != 0;
    }

    private void changeTextResult(double result) {      // if result is double sets text as double format number, if not - displays int format
        if (checkIfDouble(result)) {
            text = String.valueOf(df.format(result));
            rememberedText = text;
        } else {
            text = String.valueOf((int) result);
            rememberedText = text;
        }
    }

    private void displayText(String text) {             // displays result on calculator screen
        calculator.textField.setText(text);
    }

    private void disableOperationButtons() {
        for (int i = 0; i < 4; i++) {
            calculator.jButtonTable[i][3].setEnabled(false);
        }
    }

    private void enableOperationButtons() {
        for (int i = 0; i < 4; i++) {
            calculator.jButtonTable[i][3].setEnabled(true);
        }
    }

    private void equalsRepeated(char rememberedChar) {  // repeats last operation when '=' pressed repeatedly
        if (isPreviousNumeric)
            switch (rememberedChar) {
                case '+':
                    result += rememberedArgument;
                    break;
                case '-':
                    result -= rememberedArgument;
                    break;
                case '*':
                    result *= rememberedArgument;
                    break;
                case '/':
                    result /= rememberedArgument;
                    break;
            }
    }

    private void concat(char pressed) {                 // executed every time when operator sign is changed in a row
        isPreviousNumeric = false;
        rememberedResult = result;
        if (pressed != '=')
            rememberedChar = pressed;
        if (signOperator != '=') {
            checkSignOperator();
        }
    }

    private void checkSignOperator() {                  // performs proper action depending on operator sign
        if (signOperator == '0') result = argument;
        else if (signOperator == '+') {
            result = rememberedResult;
            result += argument;
            changeTextResult(result);
            displayText(text);
        } else if (signOperator == '-') {
            result = rememberedResult;
            result -= argument;
            changeTextResult(result);
            displayText(text);
        } else if (signOperator == '*') {
            result = rememberedResult;
            if (argument != 0)
                result *= argument;
            changeTextResult(result);
            displayText(text);
        } else if (signOperator == '/') {
            result = rememberedResult;
            if (argument != 0)
                result /= argument;
            changeTextResult(result);
            displayText(text);
        } else if (signOperator == '=') {
            equalsRepeated(rememberedChar);
        }
    }

    private void settingsSignOperators(char pressed) {     // executed every time when operator button is pressed, resets argument value
        signOperator = pressed;
        argument = 0;
    }

    void onClick(char pressed) {
        if (Character.isDigit(pressed)) {
            if (!text.equals("0")) {
                text += String.valueOf(pressed);
                rememberedText = text;
                displayText(text);
            } else {
                text = String.valueOf(pressed);
                rememberedText = text;
                displayText(text);
            }
        }
        switch (pressed) {
            case '+':
                concat(pressed);
                text = "";
                rememberedText = text;
                settingsSignOperators(pressed);
                break;
            case '-':
                concat(pressed);
                text = "";
                rememberedText = text;
                settingsSignOperators(pressed);
                break;
            case '*':
                concat(pressed);
                text = "";
                rememberedText = text;
                settingsSignOperators(pressed);
                break;
            case '/':
                concat(pressed);
                text = "";
                rememberedText = text;
                settingsSignOperators(pressed);
                break;
            case 'C':
                setDefaults();
                break;
            case '=':
                checkSignOperator();
                settingsSignOperators(pressed);
                changeTextResult(result);
                displayText(text);
                break;

            default: // for digits
                isPreviousNumeric = true;
                rememberedResult = result;
                if (signOperator == '=') {
                    if (argument != 0) {
                        argument = Integer.parseInt(String.valueOf(argument) + String.valueOf(pressed));

                    } else {
                        argument = Integer.parseInt(String.valueOf(pressed));
                    }

                    rememberedArgument = argument;
                    result = argument;
                    text = String.valueOf((int) result);
                    rememberedText = text;
                    displayText(text);
                } else if (argument != 0) {
                    argument = Integer.parseInt(String.valueOf(argument) + String.valueOf(pressed));
                    rememberedArgument = argument;
                    displayText(text);

                } else {

                    try {
                        argument = Integer.parseInt(String.valueOf(pressed));
                        if (argument == 0 && signOperator == '/') {
                            if (result == 0) throw new UnspecifiedValueException("Unspecified value.");
                            throw new DivisionByZeroException("Division by zero!");
                        }
                    } catch (UnspecifiedValueException ue) {
                        disableOperationButtons();
                        setDefaults();
                        displayText(ue.getText());
                        rememberedText = ue.getText();
                        text = "";
                    } catch (DivisionByZeroException de) {
                        disableOperationButtons();
                        setDefaults();
                        displayText(de.getText());
                        rememberedText = de.getText();

                        text = "";
                    }
                    rememberedArgument = argument;
                }
                break;
        }
    }

    public void actionPerformed(ActionEvent arg0) {        // main handling events function body
        char pressed = arg0.getActionCommand().charAt(0);
        enableOperationButtons();
        onClick(pressed);
    }

}