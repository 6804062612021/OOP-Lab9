//package calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleCal {
    public static void main(String[] args) {
        JFrame converter = new NewFrame();
        converter.setTitle("Simple Calculator");
        converter.setSize(400,400);
        converter.setLocationRelativeTo(null);
        converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        converter.setVisible(true);
    }
}

class NewFrame extends JFrame{
    JLabel lb = new JLabel("0");
    String[] text = {"√","^2","+-","C",
                    "7","8","9","+",
                    "6","5","4","-",
                    "1","2","3","*",
                    "0",".","=","/"};
    JButton button;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    
    class Listener implements ActionListener {
        double num = 0;
        String pendingOperator = "";
        boolean startNewNumber = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            String temp = e.getActionCommand();
            try {
                if (!temp.equals(".")) {
                    Double.parseDouble(temp);
                }
                if (startNewNumber || lb.getText().equals("0")) {
                    if (temp.equals(".")) {
                        lb.setText("0.");
                    } else {
                        lb.setText(temp);
                    }
                    startNewNumber = false;
                } else {
                    if (temp.equals(".") && lb.getText().contains(".")) {
                        return; 
                    }
                    lb.setText(lb.getText()+temp);
                }
            } catch (NumberFormatException err) {
                double currentVal = Double.parseDouble(lb.getText());
                
                switch(temp) {
                    case "√":
                        lb.setText(Double.toString(Math.sqrt(currentVal)));
                        startNewNumber = true;
                        break;
                    case "^2":
                        lb.setText(Double.toString(Math.pow(currentVal,2)));
                        startNewNumber = true;
                        break;
                    case "+-":
                    double toggled = currentVal * -1;
                    // Format integer results cleanly
                    lb.setText(toggled % 1 == 0 ? String.valueOf((long) toggled) : String.valueOf(toggled));
                    break;

                // Two-operand operators (+, -, *, /)
                case "+":
                case "-":
                case "*":
                case "/":
                    num = currentVal;
                    pendingOperator = temp;
                    startNewNumber = true;
                    break;

                case "=":
                    if (!pendingOperator.isEmpty()) {
                        double result = calculate(num, currentVal, pendingOperator);
                        lb.setText(result % 1 == 0 ? String.valueOf((long) result) : String.valueOf(result));
                        pendingOperator = "";
                        startNewNumber = true;
                    }
                    break;

                case "C":
                    lb.setText("0");
                    num = 0;
                    pendingOperator = "";
                    startNewNumber = true;
                    break;
                }
            }
        }
        
    }
    
    private double calculate(double op1, double op2, String operator) {
        switch (operator) {
            case "+": return op1 + op2;
            case "-": return op1 - op2;
            case "*": return op1 * op2;
            case "/": return op2 != 0 ? op1 / op2 : 0; // Avoid division by zero
            default: return op2;
        }
    }
    
    NewFrame() {
        setLayout(new BorderLayout());
        p1.setLayout(new GridLayout(5,4,5,5));
        
        p2.add(lb);
        
        Listener listener = new Listener();
        
        for (int i=0; i<20; i++) {
            button = new JButton(text[i]);
            p1.add(button);
            button.addActionListener(listener);
        }
        
        add(p2,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
    }
}
