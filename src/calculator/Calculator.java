/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author jazza
 */
public class Calculator {

    JTextArea screen;

    public JTextArea getScreen() {
        return screen;
    }

    private String currentCalculatorString = "";
    // the current index of the deepest parentheses (full calculation)
    private int FindexOfCurrFullCalculation = -1;
    private int SindexOfCurrFullCalculation = -1;
    
    // the current index of the first calculation in deepest parentheses (mini calculation)    
    // * -> / -> + -> -
    private int FindexOfCurrCalculation = -1;
    private int SindexOfCurrCalculation = -1;

    private String showedCalculation = "";
    
    public Calculator(JTextArea screen) {
        this.screen = screen;
    }

    public void addToCurrentCalculatorString(String symboll) {

        if (symboll.equals("/")){
            this.showedCalculation += "÷";
        } else if (symboll.equals("*")){
            this.showedCalculation += "×";
        } else {
            this.showedCalculation += symboll;
        }

        this.currentCalculatorString += symboll;
        this.screen.setText(this.showedCalculation);
    }

    public void setCurrentCalculatorString(String currentCalculatorString) {
        this.currentCalculatorString = currentCalculatorString;
    }
    
    

    public String getCurrentCalculatorString() {
        return currentCalculatorString;
    }
    
    public String calculateCurrentString(){
        
        String bigCal = currentCalculatorString;
        
        String currFullCal = "312";

        while(true){    

            // this is for saving the original full string so that we can replace it 
            String savingTheOriginal = getDeepestParentheses(bigCal);
            
            // string following the solving of the mini Compound calculation            
            currFullCal = getDeepestParentheses(bigCal);
            while (true) {
                // for a binary operation anwser
                float miniAns;
                // for the binary operation calculation
                String cal;
                
                // replacing any 2 minuses if they are beside each other
                currFullCal = currFullCal.replace("--", "+");
                
                // checking and handeling subtracting
                currFullCal = checkForMinus(currFullCal);
                
                
                System.out.println(currFullCal);
                           
                if (!findCalculationWithSymboll(currFullCal, '*').equals("None")){                    
                    cal = findCalculationWithSymboll(currFullCal, '*');
                    miniAns = miniCalculate(cal); 
                } else if (!findCalculationWithSymboll(currFullCal, '/').equals("None")){
                    cal = findCalculationWithSymboll(currFullCal, '/');
                    miniAns = miniCalculate(cal);                    
                } else if (!findCalculationWithSymboll(currFullCal, '+').equals("None")){
                    cal = findCalculationWithSymboll(currFullCal, '+');
                    miniAns = miniCalculate(cal);                    
                } else if (!findCalculationWithSymboll(currFullCal, '%').equals("None")) {
                    cal = findCalculationWithSymboll(currFullCal, '%');
                    miniAns = miniCalculate(cal);
                } else {
                    break;               
                }
                currFullCal = currFullCal.replace(cal, ""+miniAns);                
                                                                
            }
            
            bigCal = bigCal.replace(savingTheOriginal, currFullCal);

            bigCal = handelMultiplingParanthisis(bigCal);


            if (findBiggestPerenthses(bigCal).equals("None")){
                break;
            } else {
                String withP = "("+getDeepestParentheses(bigCal)+")";
                String withoutP = getDeepestParentheses(bigCal);

                bigCal = bigCal.replace(withP, withoutP);
            }
        
        }
        
        
        return currFullCal;
    }
    

    
    private String findBiggestPerenthses(String curr){
        
        String fistparentheses = "None";
        int indexOfFirstParenthses = -2;
        int indexOfSecondParenthses = -1;
        int numberOfIgnoredParentheses = 0;
        
        
        for (int i = 0; i < curr.length(); i++) {



            if (curr.charAt(i) == '('){
                if (indexOfFirstParenthses == -2){
                    indexOfFirstParenthses = i;
                } else {
                    numberOfIgnoredParentheses++;
                } 
            }
            
            if (curr.charAt(i) == ')'){
                if (numberOfIgnoredParentheses == 0){
                    indexOfSecondParenthses = i;
                    // Getting the string inside the biggest parentheses
                    // the string we want starts after the first (
                    fistparentheses = curr.substring(indexOfFirstParenthses+1, indexOfSecondParenthses);
                    this.FindexOfCurrCalculation = indexOfFirstParenthses;
                    this.SindexOfCurrCalculation = indexOfSecondParenthses;
                } else {
                    numberOfIgnoredParentheses--;
                }
            }
            
        }
        
        return fistparentheses;
    }
    
    private String getDeepestParentheses(String curr){
        
        while (true){
            if (findBiggestPerenthses(curr).equals("None")){
                break;
            }
            curr = findBiggestPerenthses(curr);
            
        }
        
        return curr;
    }
    
    private String findCalculationWithSymboll(String fullString, char sym){
        
        String finalCalculation = "None";
        int symbollIndex = -1;
        
        // working
        for (int i = 0; i < fullString.length(); i++) {
            if(fullString.charAt(i) == sym){
                symbollIndex = i;
//                System.out.println("symboll index: " + i);                
                break;
            } 
        }
        
        if((symbollIndex)==-1){
            return finalCalculation;
        }
        
        // by default it should be the first index
        FindexOfCurrCalculation = 0;        
        
        for (int i = symbollIndex-1; i >= 0 ; i--) {
            
            // if we hit a non digit or not a dot
            if (!(fullString.charAt(i)+"").matches("\\d|\\.|\\-")){
                FindexOfCurrCalculation = i+1;
//                System.out.println("first index of cal: " + FindexOfCurrCalculation);
                break;
            }
        }

        // by default it should be the last index
        SindexOfCurrCalculation = fullString.length()-1;        
        
        for (int i = symbollIndex+1; i < fullString.length(); i++){
            
            
            // if we hit a non digit or not a dot
            if (!(fullString.charAt(i)+"").matches("\\d|\\.|\\-")){
                SindexOfCurrCalculation = i-1;
//                System.out.println("second index of cal: " + SindexOfCurrCalculation);                
                break;
            }
        }        
        
        
        finalCalculation = fullString.substring(FindexOfCurrCalculation, SindexOfCurrCalculation+1);
        
        
        
        return finalCalculation;
    }

    private float miniCalculate(String cal) {

        String symboll = "none";
        
        for (int i = 0; i < cal.length(); i++) {
            if (!(cal.charAt(i) + "").matches("\\d|\\.|\\-")){
                symboll = cal.charAt(i) + "";                
                break;
            }
        }
        
//        System.out.println(cal);
        String[] symbolls = cal.split("\\"+symboll);
        
        
        float first = Float.parseFloat(symbolls[0]);
        float second = Float.parseFloat(symbolls[1]);
        
        
        if (symboll.equals("*")){
            return first * second;
        } else if (symboll.equals("/")) {
            return first / second;
        } else if (symboll.equals("+")){
            return first + second;
        } else if (symboll.equals("%")){
            return first % second;
        }
        
        return 0;
    }

    public String checkForMinus(String currFullCal) {
        
        StringBuilder ourCurrFullCal = new StringBuilder(currFullCal);
        
        for (int i = 0; i < ourCurrFullCal.length(); i++) {
            if (ourCurrFullCal.charAt(i) == '-'){
                if (i != 0){
                    if ((ourCurrFullCal.charAt(i-1)+"").matches("\\d")){
                       ourCurrFullCal.replace(i, i+1, "+-");
                    }
                }
            }
        }
        
        return ourCurrFullCal.toString();
    }

    private String handelMultiplingParanthisis(String curr){

        // turning it to a string builder
        StringBuilder currBuld = new StringBuilder(curr);

        for (int i = 0; i < currBuld.length(); i++) {
            if (currBuld.charAt(i) == '(' && i != 0){
                if ((currBuld.charAt(i-1)+"").matches("\\d")){
                    currBuld.insert(i, "*");
                }
            } else if (currBuld.charAt(i) == ')' && i != currBuld.length()-1){
                if ((currBuld.charAt(i+1)+"").matches("\\d")){
                    currBuld.insert(i+1, "*");
                }
            }
        }


        // returing and replacing all )( with a )*(
        return currBuld.toString().replace(")(", ")*(");
    }

    public void clear() {
        this.currentCalculatorString = "";
        this.showedCalculation = "";
        this.screen.setText(showedCalculation);
    }
}
