/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jazza
 */
public class myButton extends JButton {
    
    JPanel panel;
    private String Text;
    private static int nextXLocatoin = 30;
    private static int nextYLocatoin = 200;
    private static int numberOfButtonsInLine = 0;
    private Calculator calculator;
    private String val;

    // to know if the calculation have been solved and printed to the screen or not
    // if it has --> true if not --> false
    private static boolean answered;
    public myButton(JPanel panel, String Text, Calculator calculator) {
        
        super();

        this.panel = panel;
        this.Text = Text;
        this.calculator = calculator;



        this.val = (Text.equals("×")) ? "*" : Text;
        this.Text = (Text.equals("/")) ? "÷" : Text;

        // setting the action
        // setting the text
        this.setText(this.Text);
        this.setFont(new Font("Monospaced", Font.PLAIN, 35));
        this.setForeground(Color.white);
        this.setPreferredSize(new Dimension(85, 85));
        this.setLocation(nextXLocatoin, nextYLocatoin);
        this.setBackground(Color.gray);
        if (this.Text.equals("=")){
            this.setBackground(Color.decode("#980000"));
        }
        this.setBorderPainted(false);
        this.panel.add(this);
        this.addActionListener(e -> buttonPressed());

        this.addMouseListener(new java.awt.event.MouseAdapter() {


            public void mouseEntered(java.awt.event.MouseEvent evt) {

                if (Text.equals("=")){
                    setBackground(Color.decode("#760000"));
                }else {
                    setBackground(Color.darkGray);
                }

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (Text.equals("="))
                    setBackground(Color.decode("#980000"));
                else
                    setBackground(Color.gray);
            }
        });

        myButton.numberOfButtonsInLine++;
        myButton.nextXLocatoin += 100;

        if (numberOfButtonsInLine == 4){
            myButton.nextYLocatoin += 100;
            myButton.nextXLocatoin = 30;
            myButton.numberOfButtonsInLine = 0;
        }
        
    }

    private void buttonPressed(){
        if (this.Text.equals("=")){
            if (!answered) {
                JTextArea screen = calculator.getScreen();
                try {
                    String ans = calculator.calculateCurrentString();
                    screen.setText(screen.getText() + "\n\n\t" + ans);
                    answered = true;
                } catch (Exception e){
                    screen.setText(screen.getText() + "\n\n\t" + "Wrong input");
                }
            }
        } else if (this.Text.equals("AC")){
            calculator.clear();
            answered = false;
        } else {
            calculator.addToCurrentCalculatorString(this.val);
            answered = false;

        }
    }

    
}
