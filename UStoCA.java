//package converter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UStoCA {
    public static void main(String[] args) {
        JFrame converter = new NewFrame();
        converter.setTitle("Convert US Dollars to Canadian Dollars");
        converter.setSize(400,150);
        converter.setLocationRelativeTo(null);
        converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        converter.setVisible(true);
    }
}

class NewFrame extends JFrame{
    JLabel lbUS = new JLabel("US Dollars");
    JTextField inUS = new JTextField(10);
    JLabel lbCA = new JLabel("Canadian Dollars");
    JTextField inCA = new JTextField(10);
    JButton convert = new JButton("Convert");
    
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    
    class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double USD;
            if (e.getActionCommand().equals("Convert")) {
                if (inUS.getText().isEmpty()) {
                    inUS.setText(Double.toString(0.0));
                }
                USD = Double.parseDouble(inUS.getText());
                
                double CAD = USD * 1.5;
                inCA.setText(Double.toString(CAD));
            }
        }
        
    }
    
    NewFrame() {
        setLayout(new BorderLayout());
        p1.setLayout(new GridLayout(1,2,5,5));
        p2.setLayout(new FlowLayout());
        p3.setLayout(new GridLayout(2,1,5,5));
        p4.setLayout(new GridLayout(2,1,5,5));
        
        p1.add(p3,BorderLayout.WEST);
        p1.add(p4,BorderLayout.CENTER);
        p3.add(lbUS);
        p3.add(lbCA);
        p4.add(inUS);
        p4.add(inCA);
        p2.add(convert);
        
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
        
        Listener listener = new Listener();
        convert.addActionListener(listener);
    }
}
