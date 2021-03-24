package hotel.controllers.adminPageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminPageController {
    public Button bronButton;
    public Button customersTable;
    public Button roomsTable;

    public void onCustomerListShow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/costumersList/costumers_list.fxml"));
        Node node = (Node) actionEvent.getSource();
        BorderPane template = (BorderPane) node.getScene().getRoot();
        template.setCenter(root);
    }

    public void onBookingRoom(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/bookingList/booking_list.fxml"));
        Node node = (Node) actionEvent.getSource();
        BorderPane template = (BorderPane) node.getScene().getRoot();
        template.setCenter(root);
    }

    public void onRooms(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/roomsList/roomsList.fxml"));
        Node node = (Node) actionEvent.getSource();
        BorderPane template = (BorderPane) node.getScene().getRoot();
        template.setCenter(root);
    }

    public void onBookingTable(ActionEvent actionEvent) throws IOException {

    }
}
