package csi2300;

import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;


class Addition {

    private Label question;
    private Label answer;
    private Text flag;
    private TextField resu1lt;

    int resu1;
    int resu2 = -1;

    Random rand;
    int number1;
    int number2;

    int counter = 1;
    int LEVEL = 10;
    int level;

    //int n;
    String op;

    Addition(TextField resu1lt, Label answer, Label question, Text flag, int level, String op) {
        this.question = question;
        this.answer = answer;
        this.resu1lt = resu1lt;
        this.flag = flag;
        this.level = level;
        this.op = op;
    }


    public void loadText(Pane gp) {

        Text questiontxt = new Text("Question: ");
        Text answertxt = new Text("Answer: ");
        

        gp.getChildren().add(questiontxt);
        gp.getChildren().add(answertxt);
        gp.getChildren().add(question);
        gp.getChildren().add(resu1lt);
        gp.getChildren().add(answer);
        gp.getChildren().add(flag);

        questiontxt.setLayoutX(50);
        questiontxt.setLayoutY(200);
        question.setLayoutX(110);
        question.setLayoutY(200);

        answertxt.setLayoutX(50);
        answertxt.setLayoutY(230);
        answer.setLayoutX(110);
        answer.setLayoutY(230);
        flag.setLayoutX(260);
        flag.setLayoutY(245);

        resu1lt.setLayoutX(50);
        resu1lt.setLayoutY(255);

        rand = new Random(); 
        randomNum();

    }

    public void submitBtn(Pane gp) {

        Button submit = new Button("Summit");
        TranslateTransition transition = new TranslateTransition();
        Circle cir = new Circle();

        gp.getChildren().add(submit);
        gp.getChildren().add(cir);

        submit.setLayoutX(260);
        submit.setLayoutY(300);

        cir.setFill(Color.DARKSALMON);
        cir.setRadius(30);
        cir.setLayoutX(500);
        cir.setLayoutY(550);
        
        submit.setDefaultButton(true);
        submit.setOnAction(e -> {

            if (counter < LEVEL) { 

                try {

                    if (resu1lt.getText().isEmpty()) {

                        Alert alert = new Alert(AlertType.ERROR, "Please enter a number");
                        alert.showAndWait();
                        resu2 = -1;
                        answer.setText("");
                        Platform.runLater(() -> resu1lt.requestFocus());

                    } else {

                        resu2 = Integer.parseInt(resu1lt.getText());

                        if (resu1 == resu2) {
                                    
                            transition.setAutoReverse(false);
                            transition.setNode(cir);
                            
                            cir.setLayoutX(500);
                            cir.setLayoutY(550 - 50 * counter);

                            transition.play();

                            counter++;
                            
                            answer.setText(number1 + op + number2 + " = " + resu1lt.getText());
                            flag.setText("Correct");
                            //flag.setStyle("-fx-color: green");
                            //flag = new Text("Correct!");
                            flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                            flag.setFill(Color.GREEN);
                            resu1lt.clear();
                        }
                        else {
                            answer.setText(number1 + op + number2 + " = " + resu1lt.getText());
                            flag.setText("Incorrect");
                            flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                            flag.setFill(Color.RED);
                            resu1lt.clear();
                        } 
                    }
                    
                } catch(NumberFormatException ex){ 
                    ;
                }

                
            } else {
                    
                    answer.setText(number1 + op + number2 + " = " + resu1lt.getText());
                    flag.setText("Correct");
                    flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                    flag.setFill(Color.GREEN);
                    resu1lt.clear();

                    transition.setAutoReverse(false);
                    transition.setNode(cir);
                    transition.play();
                    cir.setLayoutY(50);


                    if (counter == LEVEL)
                        display();

                    cir.setLayoutY(550);
                    counter = 1;
                    resu1lt.clear();
                    question.setText("");
                    answer.setText("");
                    flag.setText("");
            }

            //Platform.runLater(() -> resu1lt.requestFocus());
        });
    }

    public void renewBtn(Pane gp) {

        Button continuebtn = new Button("Continue");
        
        continuebtn.setOnAction(e -> {

            randomNum();
            resu1lt.clear();
            answer.setText("");
            flag.setText("");

            Platform.runLater(() -> resu1lt.requestFocus());
        });

        gp.getChildren().add(continuebtn);
        continuebtn.setLayoutX(260);
        continuebtn.setLayoutY(270);
    }


    public void randomNum() {
                        
        int n = rand.nextInt(1,5);

        number1 = rand.nextInt(0,10);
        number2 = rand.nextInt(0,10);

        switch (n) {

            case 1:     question.setText(number1 + " + " + number2 + " = ?");
                        op = " + ";
                        resu1 = number1 + number2;
                        break;
                    
            case 2:     question.setText(number1 + " - " + number2 + " = ?");
                        op = " - ";                   
                        resu1 = number1 - number2;
                        break;
                    
            case 3:     question.setText(number1 + " * " + number2 + " = ?");
                        op = " * ";
                        resu1 = number1 * number2;
                        break;
                    
            case 4:     while (number2 == 0)
                            number2 = rand.nextInt(0,10);
                        question.setText(number1 + " / " + number2 + " = ?");
                        op = " / ";
                        resu1 =  number1 / number2;
                        break;
            default:    op = "";
                    
        }

    }

    
    public void homeBtn(Pane gp, Stage primaryStage) {

        Button btnExit = new Button("Home");
        btnExit.setOnAction(e -> {
            //Creating a menu
            Menu menu = new Menu("Option");
            //Creating menu Items
            MenuItem item1 = new MenuItem("Addition");
            MenuItem item2 = new MenuItem("Subtract");
            //Adding all the menu items to the menu
            menu.getItems().addAll(item1, item2);

            item1.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    Pane pane = new Pane();
                    Label question = new Label();
                    question.setText("");
                    Label answer = new Label();
                    TextField resu1lt = new TextField();
                    String op = "";
                    Addition addition = new Addition(resu1lt, answer, question, flag, level, op);
        
                    Scene scene = new Scene(pane, 600, 600);
        
                    primaryStage.setTitle("Space game!");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    addition.loadText(pane);
                    addition.randomNum();
                    addition.submitBtn(pane);
                    addition.renewBtn(pane);
                    addition.homeBtn(pane, primaryStage);
               }
            });
               
            item2.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                   
                    Pane pane = new Pane();
                    Label question = new Label();
                    question.setText("");
                    Label answer = new Label();
                    TextField resu1lt = new TextField();
                    String op = "";
                    Addition addition = new Addition(resu1lt, answer, question, flag, level, op);
        
                    Scene scene = new Scene(pane, 600, 600);
        
                    primaryStage.setTitle("Space game!");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    addition.loadText(pane);
                    addition.randomNum();
                    addition.submitBtn(pane);
                    addition.renewBtn(pane);
                    addition.homeBtn(pane, primaryStage);
                }
             });
             
            MenuBar menuBar = new MenuBar(menu);
            menuBar.setTranslateX(3);
            menuBar.setTranslateY(3);
            //Setting the stage
            //Group root = new Group(menuBar);
            Scene scene = new Scene(menuBar, 595, 355, Color.BEIGE);
            primaryStage.setTitle("Calculation Crunch");
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        gp.getChildren().add(btnExit);
        btnExit.setLayoutX(260);
        btnExit.setLayoutY(330);
    }


    public static void display()
    {
        Stage popupwindow=new Stage();
            
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Finish Line");      
            
        Label label= new Label(""); 
        //label.setStyle("-fx-color: blue");
        label.setFont(new Font("Arial", 30)); 
        label.setTextFill(Color.color(0, 0, 1));    
        label.setText("Excellent!");   
            
        Button button= new Button("OK");   
        button.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 250, 160);
            
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        
    }
    
}
