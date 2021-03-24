package hotel;


import hotel.models.Customers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Customers currentCustomer;
    public static ObservableList<Customers> tableCostumers = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent parent = FXMLLoader.load(getClass().getResource("/userInterface/CreateProjectScreen.fxml"));

        Parent root = FXMLLoader.load(getClass().getResource("views/enter/enter.fxml"));
        primaryStage.setTitle("Welcome to Hilton hotel");
        Image icon = new Image(this.getClass().getResourceAsStream("photos/login.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 790, 590));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
