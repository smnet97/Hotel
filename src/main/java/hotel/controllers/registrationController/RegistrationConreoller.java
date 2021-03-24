package hotel.controllers.registrationController;

import hotel.Main;
import hotel.controllers.FormActionType;
import hotel.models.Customers;
import hotel.services.CustomersService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegistrationConreoller {
    public TextField name;
    public TextField surname;
    public TextField middleName;
    public TextField pasport;
    public TextField phone;
    public TextField email;
    public TextField login;
    public TextField parol;
    public Button saveButton;
    public Label email_error;

    private CustomersService service = CustomersService.getInstance();
    public FormActionType type = FormActionType.CREATE;

    public void onSave(ActionEvent actionEvent) {

//        Alert alert1 = new Alert(Alert.AlertType.ERROR);
//        Window owner = saveButton.getScene().getWindow();
//        if (name.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos ismizni kiriting.");
////            ((Stage)alert1.getDialogPane().getScene().getWindow()).getIcons().add(new Image("../../photos/user.png"));
//            return;
//        }
//        if (surname.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos familyezni kiriting.");
//            return;
//        }
//        if (middleName.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos sharifizni kiriting.");
//            return;
//        }
//        if (pasport.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos pasport serya va raqmini kiriting.");
//            return;
//        }
//        if (email.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos email adressizni kiriting.");
//            return;
//        }
//        if (login.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos shaxsiy loginizni kiriting.");
//            return;
//        }
//        if (parol.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
//                    "Iltmos shaxsiy parolizni kiriting.");
//            return;
//        }
        Customers customers = new Customers();
        customers.setName(name.getText());
        customers.setSurname(surname.getText());
        customers.setMiddleName(middleName.getText());
        customers.setPassportNumber(pasport.getText());
        customers.setPhoneNumber(phone.getText());
        customers.setEmailAddress(email.getText());
        customers.setLogin(login.getText());
        customers.setPassword(parol.getText());

        service.save(customers);

        if (type == FormActionType.CREATE) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Kiritilgan ma'lumotlar saqlansinmi ?", ButtonType.YES, ButtonType.NO);
            confirm.setHeaderText("Diqqat!!!");
            confirm.showAndWait();
            if (confirm.getResult() == ButtonType.YES) {
                if (Main.tableCostumers.add(customers)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.YES);
                    alert.setHeaderText("Foydalanuvchi ma'lumotlari saqlandi !");
                    alert.showAndWait();
                    this.resedFrom();

                    Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/enter/enter.fxml"));
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
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.YES);
            alert.setHeaderText("Mijoz ma'lumotlari o'zgardi !");
            alert.showAndWait();
//            this.resedFrom();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        }
    }

    private void resedFrom() {
        name.setText("");
        surname.setText("");
        middleName.setText("");
        pasport.setText("");
        phone.setText("");
        email.setText("");
        login.setText("");
        parol.setText("");
    }

    static boolean Handle_EmailField = false;

//    public void onEmail(Event event) {
//        email.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if (!newValue) {
//                    String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//                    if ((email.getText()).matches(EMAIL_REGEX)) {
//                        email_error.setText("valid");
//                        email_error.setTextFill(Color.GREEN);
//                    } else {
//                        email_error.setText("Must be at this form : user@domain.com");
//                        email_error.setTextFill(Color.RED);
//                        Handle_EmailField = true;
//                    }
//                }
//            }
//        });
//        if (Handle_EmailField) {
//            String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//            if ((email.getText()).matches(EMAIL_REGEX)) {
//                email_error.setText("valid");
//                email_error.setTextFill(Color.GREEN);
//            } else {
//                email_error.setText("Must be at this form : user@domain.com");
//                email_error.setTextFill(Color.RED);
//                Handle_EmailField = true;
//            }
//        }
//    }
}
