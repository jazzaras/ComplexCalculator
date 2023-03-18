/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author jazza
 */
public class Main extends JFrame{
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("The Greatest Calculator Ever Made");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // pnanel
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#262626"));
        frame.add(panel);

        // making the textarea -- AKA -- calculator screen
        JTextArea screen = new JTextArea(3,16);
        screen.setEditable(false);
        screen.setFont(new Font("Serif", Font.BOLD, 30));
        screen.setBackground(Color.lightGray);
        panel.add(screen);


        // define the calculator
        Calculator calculator = new Calculator(screen);


        myButton[] buttons = {new myButton(panel, "(", calculator),
                new myButton(panel, ")", calculator),
                new myButton(panel, "%", calculator),
                new myButton(panel, "/", calculator),
                new myButton(panel, "7", calculator),
                new myButton(panel, "8", calculator),
                new myButton(panel, "9", calculator),
                new myButton(panel, "×", calculator),
                new myButton(panel, "4", calculator),
                new myButton(panel, "5", calculator),
                new myButton(panel, "6", calculator),
                new myButton(panel, "-", calculator),
                new myButton(panel, "1", calculator),
                new myButton(panel, "2", calculator),
                new myButton(panel, "3", calculator),
                new myButton(panel, "+", calculator),
                new myButton(panel, ".", calculator),
                new myButton(panel, "0", calculator),
                new myButton(panel, "AC", calculator),
                new myButton(panel, "=", calculator),

        };



//        myButton sub = new myButton(panel, "-", calculator);
//
//        JButton start1 = new JButton("Start") {
//            {
//                setSize(150, 75);
//                setMaximumSize(getSize());
//            }
//        };

//        panel.add(start1);

        frame.pack();
        frame.setSize(450,650);








    }

}
