package csi2300;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;




public class App extends Application {
   public void start(Stage primaryStage) {

      Text flag = new Text();
      int level = 4;
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
            Addition addition = new Addition(result, answer, question, flag, level);

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
            Subtract subtract = new Subtract(result1, answer1, question1, null, 0);
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

      //Creating a menu bar and adding menu to it.
      MenuBar menuBar = new MenuBar(menu);
      menuBar.setTranslateX(3);
      menuBar.setTranslateY(3);
      //Setting the stage
      //Group root = new Group(menuBar);
      Scene scene = new Scene(menuBar, 595, 355, Color.BEIGE);
      primaryStage.setTitle("Calculation Crunch");
      primaryStage.setScene(scene);
      primaryStage.show();


   }
   public static void main(String args[]){
      launch(args);
   }
}