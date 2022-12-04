import jdk.jshell.JShell;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Calculator {

    private JPanel Calculator;
    private JTextField resultText;
    private JButton clearBtn;
    private JButton btn7;
    private JButton leftBracketBtn;
    private JButton btn1;
    private JButton historyBtn;
    private JButton divideBtn;
    private JButton multiplyBtn;
    private JButton subtractBtn;
    private JButton addBtn;
    private JButton equalBtn;
    private JButton rightBracketBtn;
    private JButton btn8;
    private JButton btn9;
    private JButton btn5;
    private JButton btn6;
    private JButton btn2;
    private JButton btn3;
    private JButton btn0;
    private JButton dotBtn;
    private JButton btn4;
    private JButton deleteBtn;
    public List<String> historyList = new ArrayList<>();
    public boolean equalClicked = false;

    public Calculator(){
        clearBtn.addActionListener(new ClearBtnClicked());
        deleteBtn.addActionListener(new DeleteBtnClicked());
        btn0.addActionListener(new NumberBtnClicked(btn0.getText()));
        btn1.addActionListener(new NumberBtnClicked(btn1.getText()));
        btn2.addActionListener(new NumberBtnClicked(btn2.getText()));
        btn3.addActionListener(new NumberBtnClicked(btn3.getText()));
        btn4.addActionListener(new NumberBtnClicked(btn4.getText()));
        btn5.addActionListener(new NumberBtnClicked(btn5.getText()));
        btn6.addActionListener(new NumberBtnClicked(btn6.getText()));
        btn7.addActionListener(new NumberBtnClicked(btn7.getText()));
        btn8.addActionListener(new NumberBtnClicked(btn8.getText()));
        btn9.addActionListener(new NumberBtnClicked(btn9.getText()));
        dotBtn.addActionListener(new NumberBtnClicked(dotBtn.getText()));
        addBtn.addActionListener(new NumberBtnClicked(addBtn.getText()));
        subtractBtn.addActionListener(new NumberBtnClicked(subtractBtn.getText()));
        multiplyBtn.addActionListener(new NumberBtnClicked(multiplyBtn.getText()));
        divideBtn.addActionListener(new NumberBtnClicked(divideBtn.getText()));
        leftBracketBtn.addActionListener(new NumberBtnClicked(leftBracketBtn.getText()));
        rightBracketBtn.addActionListener(new NumberBtnClicked(rightBracketBtn.getText()));
        equalBtn.addActionListener(new EqualBtnClicked());
        historyBtn.addActionListener(new HistoryBtnClicked());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().Calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class ClearBtnClicked implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resultText.setText("");
        }
    }

    private class DeleteBtnClicked implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String text = resultText.getText();
            if(text.length() > 0){
                resultText.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    private class NumberBtnClicked implements ActionListener {
        private final String value;

        public NumberBtnClicked(String value) {
            this.value = value;
        }

        public void actionPerformed(ActionEvent e) {
            if(equalClicked){
                resultText.setText("");
                equalClicked = false;
            }
            resultText.setText(resultText.getText() + value);
        }
    }

    private class EqualBtnClicked implements ActionListener{
        JShell js = JShell.builder().build();

        public void actionPerformed(ActionEvent e){
            String text = resultText.getText();
            if(text.length() > 0){
                resultText.setText(js.eval(text).get(0).value());
            }
            if(Objects.equals(resultText.getText(), "")){
//                JOptionPane.showMessageDialog(null, "Invalid input");
                resultText.setText("Syntax Error");
            } else {
                historyList.add(text + " = " + resultText.getText());
            }
            equalClicked = true;
        }
    }

    private class HistoryBtnClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder displayHistory = new StringBuilder();
            if (historyList.size() > 0) {
                for (String s : historyList) {
                    displayHistory.append(s).append("\n");
                }
                JOptionPane.showMessageDialog(null, displayHistory.toString(), "History", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
