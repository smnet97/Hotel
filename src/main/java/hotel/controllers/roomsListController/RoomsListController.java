package hotel.controllers.roomsListController;

import hotel.models.Customers;
import hotel.models.Rooms;
import hotel.services.RoomsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.RoundingMode;
import java.util.List;

public class RoomsListController {
    public Button delete;
    public TableView tableRooms;
    private RoomsService roomsService = RoomsService.getInstance();

    public void initialize(){
        TableColumn<Integer, Rooms> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Integer, Rooms> roomNumberColumn = new TableColumn<>("Room number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        TableColumn<Integer, Rooms> roomTypeIdColumn = new TableColumn<>("Room type");
        roomTypeIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomTypeID"));
        TableColumn<Integer, Rooms> roomSizeIdColumn = new TableColumn<>("Room size");
        roomSizeIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomSizeId"));
        TableColumn<String, Rooms> priceDayColumn = new TableColumn<>("Room price day");
        priceDayColumn.setCellValueFactory(new PropertyValueFactory<>("priceDay"));
        TableColumn<String, Rooms> roomFloorColumn = new TableColumn<>("Room floor");
        roomFloorColumn.setCellValueFactory(new PropertyValueFactory<>("roomFloor"));

        tableRooms.getColumns().addAll(idColumn, roomNumberColumn, roomTypeIdColumn, roomSizeIdColumn,
                priceDayColumn, roomFloorColumn);

        loadTable();
    }
    private void loadTable() {
        tableRooms.getItems().clear();
        List<Rooms> list = roomsService.getAll();

        tableRooms.getItems().addAll(list);
    }
    public void onDelete(ActionEvent actionEvent) {
        SelectionModel<Rooms> selectionModel = tableRooms.getSelectionModel();
        if (selectionModel != null){
            Rooms rooms = selectionModel.getSelectedItem();
            if (rooms != null){
                roomsService.delete(rooms);
                loadTable();
            }
        }
    }
}
