package hotel.controllers.bookingListController;

import hotel.models.Booking;
import hotel.services.BookingService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.print.Book;
import java.util.List;

public class BookingListController {
    public Button delete;
    public TableView tableBooking;
    private BookingService service = BookingService.getInstance();
    public void initialize(){
        TableColumn<Integer, Booking> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Integer, Booking> customerIdColumn = new TableColumn<>("Customer id");
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        TableColumn<Integer, Booking> roomTypeColumn = new TableColumn<>("Room type id");
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        TableColumn<Integer, Booking> roomSizeColumn = new TableColumn<>("Room size id");
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));
        TableColumn<String, Booking> givenDateColumn = new TableColumn<>("Olingan kun");
        givenDateColumn.setCellValueFactory(new PropertyValueFactory<>("givenDate"));
        TableColumn<String, Booking> dueDateColumn = new TableColumn<>("Oxirgi kun");
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        TableColumn<Integer, Booking> roomNumberColumn = new TableColumn<>("Room number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        TableColumn<String, Booking> cardNumberColumn = new TableColumn<>("Karta raqamin");
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        TableColumn<String, Booking> cardDateColumn = new TableColumn<>("Karta muddati");
        cardDateColumn.setCellValueFactory(new PropertyValueFactory<>("cardDate"));

        tableBooking.getColumns().addAll(idColumn, customerIdColumn, roomTypeColumn, roomSizeColumn,
                givenDateColumn, dueDateColumn, roomNumberColumn, cardNumberColumn, cardDateColumn);

        loadTable();
    }

    private void loadTable() {
        tableBooking.getItems().clear();
        List<Booking> list = service.getAll();
        tableBooking.getItems().addAll(list);
    }

    public void onDelete(ActionEvent actionEvent) {
    }
}
