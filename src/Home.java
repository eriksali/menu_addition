import java.util.Random;

import javafx.animation.TranslateTransition;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;


class Home {

    private Stage primaryStage;
    // private Label answer;
    // private TextField result;

    Home (Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void display (){
        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            //Creating a menu
            Menu menu = new Menu("Option");
            //Creating menu Items
            MenuItem item1 = new MenuItem("Addition");
            MenuItem item2 = new MenuItem("Subtract");
            //Adding all the menu items to the menu
            menu.getItems().addAll(item1, item2);
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
    }
}

