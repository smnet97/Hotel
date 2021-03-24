package hotel.controllers.roomBookingController;

import hotel.Main;
import hotel.controllers.FormActionType;
import hotel.converter.DateConverter;
import hotel.converter.IConverter;
import hotel.models.Booking;
import hotel.models.Orders;
import hotel.services.BookingService;
import hotel.services.RoomsService;
import hotel.services.managers.ConnectionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class RoomBookingController {
    public RadioButton birinchiKlass;
    public RadioButton turistikKlass;
    public RadioButton iqtisodKlass;
    public RadioButton lyuksKlass;

    public DatePicker giveDate;
    public DatePicker dueDate;
    public ComboBox roomID;
    public TextField cardNumber;
    public TextField cardDate;
    public Button saqlashButton;
    public ToggleGroup roomType;
    public Button home;
    public ComboBox room_size;
    public Label viewRoomType;
    public Label viewRoomSize;
    public Label viewGiveDate;
    public Label viewDueDate;
    public Label viewNumberDays;
    public Label viewRoomNumber;
    public Label viewCost;
    public Label totalPrice;
    public Button saveButton;
    public Button cancel;

    private RoomsService service = RoomsService.getInstance();
    private BookingService bookingService = BookingService.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    IConverter iConverter = null;
    public  static DateConverter dateConverter = new DateConverter();
    private int roomTypeId = 0;
    private int roomSizeId = 0;
    private static int temp= 0;
    public static Booking booking = new Booking();
    public static Orders order = new Orders();
    public FormActionType type = FormActionType.CREATE;

    @FXML
    public void initialize() {
        if (temp==0) {
            room_size.getItems().add("1 kishilik");
            room_size.getItems().add("2 kishilik");
            room_size.getItems().add("3 kishilik");
            room_size.getItems().add("4 kishilik");

        }else if (temp==1){
            viewRoomType.setText(order.getRoomNameType());
            viewRoomSize.setText(order.getRoomNameSize());
            viewGiveDate.setText(String.valueOf(order.getGivenDate()));
            viewDueDate.setText(String.valueOf(order.getDueDate()));
            viewNumberDays.setText(String.valueOf(order.getDays()));
            viewRoomNumber.setText(String.valueOf(order.getRoomNumber()));
            viewCost.setText(String.valueOf(order.getRoomPriceDay()));
            totalPrice.setText(String.valueOf(order.getTotalPrice() + "$"));
//            viewRoomType.setText(String.format("%s", roomTypeName));
//            viewRoomSize.setText(String.format("%s",roomSizeName));
//            viewGiveDate.setText(String.valueOf(booking.getGivenDate()));
//            viewDueDate.setText(String.valueOf(booking.getDueDate()));
//            viewNumberDays.setText(String.format("%s", days));
//            viewRoomNumber.setText(String.valueOf(booking.getRoomNumber()));
//            viewCost.setText(String.format("%s", price));
//            totalPrice.setText(String.valueOf(booking.getTotlaPrice() + "$"));

        }
    }


    public void onActionHome(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/home/home.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Hilton");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void  onChangeClass(ActionEvent actionEvent) {

    }

    public void onRoomSize1(ActionEvent actionEvent) {
    }

    public void onRoomSize2(ActionEvent actionEvent) {
    }

    public void onRoomSize3(ActionEvent actionEvent) {
    }

    public void onSaveRoomBooking(ActionEvent actionEvent) throws ParseException {

        if (type == FormActionType.CREATE) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Kiritilgan ma'lumotlar saqlansinmi ?", ButtonType.YES, ButtonType.NO);
            confirm.setHeaderText("Diqqat!!!");
            confirm.showAndWait();
            if (confirm.getResult() == ButtonType.YES) {


                    Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/home/home.fxml"));
                        root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Hilton");
                        stage.setScene(new Scene(root, 790, 590));
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.YES);
            alert.setHeaderText("Mijoz ma'lumotlari o'zgardi !");
            alert.showAndWait();

            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        }
        bookingService.save(booking);
    }

    public static LocalDate Date_TO_LocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDate res = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return res;
    }
    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

  /*  try {
        LocalDate birthDate = birthDatePicker.getValue();
        LocalDate currentDate = currentDatePicker.getValue();
        Period period = Period.between(birthDate, currentDate);
        if (period.getDays() < 0) {
            showErrorAlert();
            resetData();
            return;
        }
        daysLabel.setText(String.valueOf(period.getDays()));
        monthLabel.setText(String.valueOf(period.getMonths()));
        yearLable.setText(String.valueOf(period.getYears()));
    } catch (Exception e) {
        showErrorAlert();
        resetData();
    }*/

    public void onRoomSize(ActionEvent actionEvent) {
        if (birinchiKlass.isSelected()){
            roomTypeId = 1;
        }
        if (turistikKlass.isSelected()){
            roomTypeId = 2;
        }
        if (iqtisodKlass.isSelected()){
            roomTypeId = 3;
        }
        if (lyuksKlass.isSelected()){
            roomTypeId = 4;
        }

        if (room_size.getValue().equals("1 kishilik")){
            roomSizeId = 1;
        }
        if (room_size.getValue().equals("2 kishilik")){
            roomSizeId = 2;
        }
        if (room_size.getValue().equals("3 kishilik")){
            roomSizeId = 3;
        }
        if (room_size.getValue().equals("4 kishilik")){
            roomSizeId = 4;
        }
        try {
            String query = "SELECT id from rooms WHERE room_type_id = ? and room_size_id = ?";
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(roomTypeId));
            preparedStatement.setString(2, String.valueOf(roomSizeId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                roomID.getItems().add(resultSet.getString("id"));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void onRoomId(ActionEvent actionEvent) {

    }




    public void onActionHome1(MouseEvent mouseEvent) {
        System.out.println("mouseEvent");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/home/home.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Hilton");
            stage.setScene(new Scene(root, 790, 590));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

//    public int price = 0;
//    public int total = 0;

    public String roomTypeName = "";
    public String roomSizeName = "";
    public static int total = 0;
    public void onSubmit(ActionEvent actionEvent) {
        temp=1;
//         String roomTypeName = "";
//         String roomSizeName = "";
         int price = 0;
//         int total = 0;

        booking.setCustomerId(Main.currentCustomer.getId());
        booking.setRoomType(roomTypeId);
        booking.setRoomSize(roomSizeId);
        booking.setGivenDate((Date) dateConverter.getAsObject(giveDate.getValue().toString()));
        booking.setDueDate((Date) dateConverter.getAsObject(dueDate.getValue().toString()));
        booking.setRoomNumber(Integer.parseInt(roomID.getValue().toString()));
        booking.setCardNumber(cardNumber.getText());
        booking.setCardDate(cardDate.getText());
        long days = ChronoUnit.DAYS.between(giveDate.getValue(), dueDate.getValue());
//        .setDays(Math.toIntExact(days));

        try {
            String query = "SELECT price_day from rooms WHERE id = ?";
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(roomID.getValue().toString()));

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                price = resultSet.getInt("price_day");
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        total = (int) (days * price);
        booking.setTotalPrice(Integer.valueOf(total));


        try {
            String query = "SELECT room_type FROM room_type WHERE id = ?";
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getRoomType());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                roomTypeName = resultSet.getString("room_type");
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String query = "SELECT room_size FROM room_size WHERE id = ?";
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getRoomSize());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                roomSizeName = resultSet.getString("room_size");
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        order.setRoomNameType(roomTypeName);
        order.setRoomNameSize(roomSizeName);
        order.setGivenDate((Date) dateConverter.getAsObject(giveDate.getValue().toString()));
        order.setDueDate((Date) dateConverter.getAsObject(dueDate.getValue().toString()));
        order.setRoomNumber(Integer.parseInt(roomID.getValue().toString()));
        order.setDays(Math.toIntExact(days));
        order.setRoomPriceDay(price);
        order.setTotalPrice(total);




        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/confirmtaion/confirmtaion.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tasdiqlash oynasi");
            Image icon = new Image(this.getClass().getResourceAsStream("../../photos/030-confirm.png"));
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root, 490, 490));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onCancel(ActionEvent actionEvent) {
    }
}
