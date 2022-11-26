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


//class Loader {
public class Subtract {

    private Label question;
    private Label answer;
    //private Label flag;
    private Text flag;
    private TextField result;
    private int level;

    int sum1;
    int sum2 = -1;

    Random rand;
    int number1;
    int number2;

    int counter = 1;
    //counter = 550/
    //int LEVEL = 3;
    Subtract(TextField result, Label answer, Label question, Text flag, int level) {
        this.question = question;
        this.answer = answer;
        this.result = result;
        this.flag = flag;
        this.level = level;
    }


    public int loadQuestion(int number1, int number2) {
        question.setText(number1 + " x " + number2 + " = ?");
        return number1 * number2;
    }


    public int loadAnswer(TextField result, Label answer) {
        int res = Integer.parseInt(result.getText());
        answer.setText(result.getText());
        return res;     
    }


    public void loadText(Pane gp) {

        Text questiontxt = new Text("Question: ");
        Text answertxt = new Text("Answer: ");
        

        gp.getChildren().add(questiontxt);
        gp.getChildren().add(answertxt);
        gp.getChildren().add(question);
        gp.getChildren().add(result);
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

        result.setLayoutX(50);
        result.setLayoutY(255);

        rand = new Random(); 
        number1 = rand.nextInt(10); 
        number2 = rand.nextInt(10);

        question.setText(number1 + " x " + number2);

        // AnswerPrompt prompt = new AnswerPrompt(
        //     gp.getScene().getWindow()
        // );

        // String res = prompt.getResult();

        // result.setText(res);

    }



    public void submitBtn(Pane gp) {

        Button submit = new Button("Summit");
        TranslateTransition transition = new TranslateTransition();
        Circle cir = new Circle();

        gp.getChildren().add(submit);
        gp.getChildren().add(cir);

        submit.setLayoutX(260);
        submit.setLayoutY(270);

        cir.setFill(Color.DARKSALMON);
        cir.setRadius(30);
        cir.setLayoutX(500);
        cir.setLayoutY(550);
        
        submit.setDefaultButton(true);
        submit.setOnAction(e -> {

            if (counter < level) { 

                try {
                    sum1 = loadQuestion(number1, number2);
                    if (result.getText().isEmpty()) {
                        Alert alert = new Alert(AlertType.ERROR, "Please enter a number");
                        alert.showAndWait();
                        sum2 = -1;
                        answer.setText("");
                        Platform.runLater(() -> result.requestFocus());
                    } else {
                        sum2 = loadAnswer(result, answer);
                    }
                    
                } catch(NumberFormatException ex){ 
                    ;
                }

                
                if (!(result.getText().isEmpty())) {

                    //System.out.println(sum1);
                    //System.out.println(sum2);

                    if (sum1 == sum2) {
                                
                        transition.setAutoReverse(false);
                        transition.setNode(cir);
                        
                        cir.setLayoutX(500);
                        cir.setLayoutY(550 - 500/level * counter);

                        transition.play();

                        counter++;
                        
                        answer.setText(number1 + " x " + number2 + " = " + result.getText());
                        flag.setText("Correct");
                        flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                        flag.setFill(Color.GREEN);
                        result.clear();
                    }
                    else {
                        answer.setText(number1 + " x " + number2 + " = " + result.getText());
                        flag.setText("Incorrect");
                        flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                        flag.setFill(Color.RED);
                        result.clear();
                    } 
                }
                
            } else {
                    transition.setAutoReverse(false);
                    transition.setNode(cir);
                    transition.play();
                    cir.setLayoutY(50);
                    System.out.println(counter);
                    System.out.println(level);
                    if (counter == level)
                        display();
                    cir.setLayoutY(550);
                    counter = 1;
                    result.clear();
                    question.setText("");
                    answer.setText("");
                    flag.setText("");
            }

        });
    }

    public void renewBtn(Pane gp) {

        Button continuebtn = new Button("Continue");
        
        continuebtn.setOnAction(e -> {
            number1 = rand.nextInt(10); 
            number2 = rand.nextInt(10);

            question.setText(number1 + " x " + number2);
            result.clear();
            answer.setText("");
            flag.setText("");
            sum1 = 0;
            sum2 = 0;
            Platform.runLater(() -> result.requestFocus());
        });

        gp.getChildren().add(continuebtn);
        continuebtn.setLayoutX(260);
        continuebtn.setLayoutY(300);
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
                   Label question = new Label("");
                   Label answer = new Label();
                   TextField result = new TextField();
                   Addition addition = new Addition(result, answer, question, flag, counter);
       
                   Scene scene = new Scene(pane, 600, 600);
       
                   primaryStage.setTitle("Space game!");
                   primaryStage.setScene(scene);
                   primaryStage.show();
                   addition.loadText(pane);
                   addition.submitBtn(pane);
                   addition.renewBtn(pane);
                   addition.homeBtn(pane, primaryStage);
               }
            });
               
            item2.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                   
                   Pane pane1 = new Pane();
                   Label question1 = new Label("");
                   Label answer1 = new Label();
                   TextField result1 = new TextField();
                   Subtract subtract = new Subtract(result1, answer1, question1, flag, counter);
                   Scene scene1 = new Scene(pane1, 600, 600);
                   //Home home = new Home();
       
                   primaryStage.setTitle("Space game!");
                   primaryStage.setScene(scene1);
                   primaryStage.show();
                   subtract.loadText(pane1);
                   subtract.submitBtn(pane1);
                   subtract.renewBtn(pane1);
                   subtract.homeBtn(pane1, primaryStage);
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
        Scene scene= new Scene(layout, 250, 160);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
        
    }
    
}
