/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author rashe
 */
public class Calculator extends Application {

    private TextField outputText = new TextField();
    private Shape rectCont = new Rectangle(50, 50);
    private Button calcButton = new Button("Calculate");
    String currentString = "";
    
    
    Parent calc(){
        Pane root = new Pane();
        outputText.setTranslateX(25);
        outputText.setTranslateY(20);
        outputText.setDisable(true);
        outputText.setStyle("-fx-opacity: 1;");
        outputText.setPrefWidth(300);
        outputText.setPrefHeight(30);
        calcButton.setPrefWidth(130);
        calcButton.setPrefHeight(50);
        calcButton.setTranslateY(350);
        calcButton.setTranslateX(114);
        calcButton.setOnMouseClicked(e -> calculate());
        
        
        
        
        
        
        root.getChildren().add(outputText);
        root.getChildren().add(numberGrid());
       root.getChildren().add(operatorGrid());
        root.getChildren().add(extraButtons());
        root.getChildren().add(calcButton);
        return root;
    }
    
        public Pane numberGrid(){
            Pane gridPane = new Pane();
            
           
         ArrayList<Shape>numbers = new ArrayList();
         ArrayList<Text>nums = new ArrayList();
         int count = 1;
         for(int i = 1; i < 4; i++){
             
             for(int j = 1; j < 4; j++){
                 Button numButtons = new Button();
                 numButtons.setTranslateX(i*1.2 * 65);
                 numButtons.setTranslateY(j * 70);
                 numButtons.setPrefWidth(50);
                 numButtons.setPrefHeight(50);
              
                 numButtons.setText(String.valueOf(count));
                 final int num = count;
                 numButtons.setOnMouseClicked(e -> addToString(String.valueOf(num)));
                  count = count + 3;
                 
                 
                 gridPane.getChildren().add(numButtons);
                 
             }
             count = i + 1;
         }
         gridPane.setTranslateX(-40);
         //gridPane.getChildren().addAll(numbers);
        // gridPane.getChildren().addAll(nums);
         return gridPane;
     }
        
     public Pane operatorGrid(){
         Pane operatorPane = new Pane();
         operatorPane.setTranslateX(270);
         
         
         for(int i = 1; i < 6; i++){
             Button opButtons = new Button();
             opButtons.setPrefWidth(50);
             opButtons.setPrefHeight(50);
             opButtons.setTranslateY(i * 70);
            
             
             if(i == 1){
                 opButtons.setText("+");
                 opButtons.setOnMouseClicked(e -> addToString("+"));
             }else if(i ==2){
                opButtons.setText("-");
                opButtons.setOnMouseClicked(e -> addToString("-"));
             }else if( i ==3){
                 opButtons.setText("*");
                 opButtons.setOnMouseClicked(e -> addToString("*"));
             }else if(i==4){
                 opButtons.setText("/");
                 opButtons.setOnMouseClicked(e -> addToString("/"));
             }else{
                 opButtons.setText("__");
                 opButtons.setOnMouseClicked(e -> addToString(" "));
             }
              operatorPane.getChildren().add(opButtons);
         }
         
         
         return operatorPane;
     }
     
     public Pane extraButtons(){
         Pane exPane = new Pane();
         exPane.setTranslateX(116);
         exPane.setTranslateY(280);
         
         for(int i = 0; i < 2; i++){
             Button exButtons = new Button();
             exButtons.setPrefWidth(50);
             exButtons.setPrefHeight(50);
             
             exButtons.setTranslateX(i*1.2  * 65);
             
             if(i == 0){
                 exButtons.setText("0");
                 exButtons.setOnMouseClicked(e -> addToString("0"));
             }else{
                 exButtons.setText("C");
                 exButtons.setOnMouseClicked(e -> clearExpString());
             }
             
             exPane.getChildren().addAll(exButtons);
         }
         
        
         
         return exPane;
     }
        
        
      public void addToString(String str){
          System.out.println(str);
          currentString = currentString + str;
          outputText.setText(currentString);
      }
      
      public void clearExpString(){
          currentString = "";
          outputText.setText(currentString);
      }
      
      public void calculate(){
          System.out.println("calculating");
          ArrayList<String>strArray = new ArrayList();
          String items = ""; 
          currentString = currentString + " ";
          for(int i = 0; i < currentString.length(); i++){
            if(currentString.charAt(i) != ' '){
                items = items + currentString.charAt(i);
            }else{
                strArray.add(items);
                items = "";
            }
            
            
        }
          
         outputText.setText(result(strArray));
         currentString = "";
      }
    
    public static void main(String[] args) {
        
        launch(args);
    
    }
    
    public static String result(ArrayList<String>expr){
        Stack<Double>stack = new Stack<>();
        String result = "";
        
        try{
            for(int i = 0; i < expr.size(); i++){
                if(!expr.get(i).equals("+" ) && !expr.get(i).equals("-" ) && !expr.get(i).equals("*" )&& !expr.get(i).equals("/" )){
                stack.push(Double.parseDouble(expr.get(i)));
                }else{
                double operOne = stack.pop();
                double operTwo = stack.pop();
                
                if(expr.get(i).equals("+")){
                    stack.push(operOne + operTwo);
                }else if(expr.get(i).equals("-")){
                    stack.push(operTwo - operOne);
                }else if (expr.get(i).equals("*")){
                    stack.push(operOne * operTwo);
                }else if(expr.get(i).equals("/")){
                    stack.push(operTwo / operOne);
                }else{
                    
                    }
                }
            }
            result = String.valueOf(stack.pop());
            
        }catch(Exception e){
            result = "Invalid expression";
        }
        
        return result;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(calc(), 350, 410));
        stage.setTitle("Post-Fix Calculator");
        stage.show();
    }
    
}
