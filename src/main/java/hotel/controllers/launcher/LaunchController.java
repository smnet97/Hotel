//package hotel.controllers.launcher;
//
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import hotel.Paths;
//import javafx.animation.PauseTransition;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//public class LaunchController implements Initializable {
//
//    @FXML
//    private ProgressBar progressbar;
//    @FXML
//    private ImageView BGLuncher;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        PauseTransition delay = new PauseTransition(Duration.seconds(2.18));
//        delay.setOnFinished((ActionEvent event) -> {
//            System.out.println("begin");
//            goToLogin(event);
//            System.out.println("goToLogin Done");
//        });
//        delay.play();
//    }
//
//    private void goToLogin(ActionEvent event) {
//        try {
//            //Load new FXML and assign it to scene
//            Parent root = FXMLLoader.load(getClass().getResource(Paths.LOGINVIEW));
//            //create empty new stage
//            Stage window = new Stage();
//            Stage stage = new Stage();
//
////            Scene scene = new Scene(root);
//            Image icon = new Image(this.getClass().getResourceAsStream("../../photos/user.png"));
//            stage.getIcons().add(icon);
////            stage.setScene(scene);
//
//            stage.setScene(new Scene(root, 790, 590));
//            stage.setResizable(false);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.show();
//            //set the stage properties
////            JFXDecorator decorator = new JFXDecorator(window, root, false, false, true);
////            String uri = getClass().getResource("dectaorStyle.css").toExternalForm();
//            //Create new scene and add new layout in it
////            Scene scene = new Scene(decorator);
////            scene.getStylesheets().add(uri);
////            int width = 690, height = 620;
////            window.setScene(scene);
////            window.setMaxHeight(height);
////            window.setMinHeight(height);
////            window.setMaxWidth(width);
////            window.setMinWidth(width);
////            window.setResizable(false);
////            Image icon = new Image(getClass().getResourceAsStream("/img/login_icon.png"));
////            window.getIcons().add(icon);
////            window.show();
//            root.requestFocus();
//            ((Stage) BGLuncher.getScene().getWindow()).close();
//        } catch (IOException ex) {
//            System.out.println("Error load loginFXML !");
//        }
//    }
//}
