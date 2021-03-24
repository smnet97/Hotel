package hotel.controllers.confirmtaionData;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ConfirmtaionController {
    public Label roomType;
    public Label roomSize;
    public Label givenDate;
    public Label dueDate;
    public Label numberDays;
    public Label roomNumber;
    public Label cost;
    public Label totalPrice;
    public Button saveButton;
    public Button cancel;

    public void onCancel(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void onSaveRoomBooking(ActionEvent actionEvent) {

    }
}
