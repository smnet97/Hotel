package hotel.controllers.homeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.IOException;

public class HomeController {
    public Button roomServices;
    public Button bookingRoom;
    public Button restoran;

    public void onActionRoomBooking(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/roomBooking/room.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Xona bron qilish");
            Image icon = new Image(this.getClass().getResourceAsStream("../../photos/001-hotel service.png"));
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


    public void onActionRoomBookingImg(MouseEvent mouseEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/roomBooking/room.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Xona bron qilish");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void onRoomAction(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/services/servicesRoom.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Xona xizmati");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
    public void onRoomActionImg(MouseEvent mouseEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/services/servicesRoom.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Xona xizmati");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void onRestoran(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/resBooking/res_booking.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Restoran joy bron qilish");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void onRestoranImg(MouseEvent mouseEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/resBooking/res_booking.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Restoran joy bron qilish");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
}
