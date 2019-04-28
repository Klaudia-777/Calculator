package calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator {
    private static final MyActionListener myActionListener = new MyActionListener();
    static JTextField textField = new JTextField();

    public static JButton[][] setButtons() {
        JButton[][] jButtonTable = new JButton[4][4];

        jButtonTable[0][0] = new JButton("1");
        jButtonTable[0][1] = new JButton("2");
        jButtonTable[0][2] = new JButton("3");
        jButtonTable[0][3] = new JButton("+");

        jButtonTable[1][0] = new JButton("4");
        jButtonTable[1][1] = new JButton("5");
        jButtonTable[1][2] = new JButton("6");
        jButtonTable[1][3] = new JButton("-");

        jButtonTable[2][0] = new JButton("7");
        jButtonTable[2][1] = new JButton("8");
        jButtonTable[2][2] = new JButton("9");
        jButtonTable[2][3] = new JButton("*");

        jButtonTable[3][0] = new JButton("0");
        jButtonTable[3][1] = new JButton("=");
        jButtonTable[3][2] = new JButton("C");
        jButtonTable[3][3] = new JButton("/");

        return jButtonTable;
    }


    public static void setPane(Container pane) {
        JButton[][] jButtonTable = setButtons();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                jButtonTable[i][j].addActionListener(myActionListener);
                pane.add(jButtonTable[i][j]);
            }
        }
    }


    public static void createAndShowGUI() {
        JFrame jf = new JFrame("Calculator");
        textField.addActionListener(myActionListener);

        Font bigFont = textField.getFont().deriveFont(Font.PLAIN, 28f);
        textField.setFont(bigFont);

        GridLayout gridLayout = new GridLayout(4, 4);
        JPanel jPanel = new JPanel();

        jPanel.setLayout(gridLayout);
        setPane(jPanel);
        gridLayout.layoutContainer(jPanel);

        jf.getContentPane().add(textField, BorderLayout.NORTH);
        jf.getContentPane().add(jPanel, BorderLayout.CENTER);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setLocation(dim.width/2-jf.getSize().width/2, dim.height/2-jf.getSize().height/2);
        jf.setSize(300,250);
        jf.setResizable(false);
    }


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }


}
