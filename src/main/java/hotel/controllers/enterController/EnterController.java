package hotel.controllers.enterController;

import hotel.Main;
import hotel.models.Customers;
import hotel.services.CustomersService;
import hotel.services.managers.ConnectionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EnterController implements Initializable {

    public Button registrationButton;
    public Button enter;
    public TextField userName;
    public PasswordField password;
    public TextField passwordText;
    public CheckBox checkBox;
    public Label lblErrors;
    private CustomersService service = CustomersService.getInstance();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = ConnectionManager.getConnection();
        if (connection == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public void LoginController() {
        connection = ConnectionManager.getConnection();
    }

    public void onRegistration(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/registration/registration.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ro'yxatdan o'tish");
            Image icon = new Image(this.getClass().getResourceAsStream("../../photos/user.png"));
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root, 650, 800));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onEnter(ActionEvent actionEvent) {

/*        Window owner = enter.getScene().getWindow();
        if(userName.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Kirishda xatolik!",
                    "Iltmos foydanuvchi nomini kiriting.");
            return;
        }
        if(password.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formda xatolik!",
                    "Iltmos parronni kiriting.");
            return;
        }*/

        if (password.getText().equals("7777") && userName.getText().equals("Admin")){
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/adminPage/adminPage.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Admin page");
                Image icon = new Image(this.getClass().getResourceAsStream("../../photos/admin-ui.png"));
                stage.getIcons().add(icon);
                stage.setScene(new Scene(root, 990, 690));
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*String txtlogin, txtpassword;
        txtlogin = userName.getText();
        txtpassword = password.getText();

        if (txtlogin.isEmpty() || txtpassword.isEmpty()){
            setLblErrors(Color.TOMATO, "Login yoki parol kiritmadi !!!");

        }else {
            String query = "SELECT * FROM customers WHERE login = ? and password = ?";
            System.out.println(query);
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, txtlogin);
                preparedStatement.setString(2, txtpassword);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblErrors(Color.TOMATO, "Login yoki parolni to'g'ri kiriting !!!");


                }else {
                    Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/home/home.fxml"));
                        root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Hilton");
                        Image icon = new Image(this.getClass().getResourceAsStream("../../photos/home.png"));
                        stage.getIcons().add(icon);
                        stage.setScene(new Scene(root, 790, 590));
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setLblErrors(Color.GREEN, "Login, parol muvoffaqiyatli kiritildi !!!");
                }


            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                System.err.println(throwables.getMessage());

            }
        }*/
 /*       else {
            List<Customers> list = service.filter(userName.getText());

            for (Customers customers1 : list) {
                int id = customers1.getId();

                Parent root;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/home/home.fxml"));
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Hilton");
                    Image icon = new Image(this.getClass().getResourceAsStream("../../photos/home.png"));
                    stage.getIcons().add(icon);
                    stage.setScene(new Scene(root, 790, 590));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }*/

        if (logIn().equals("Success")) {
            List<Customers> customers = service.filter(userName.getText());
            Main.currentCustomer = customers.get(0);
                Parent root;
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/home/home.fxml"));
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Hilton");
                    Image icon = new Image(this.getClass().getResourceAsStream("../../photos/home.png"));
                    stage.getIcons().add(icon);
                    stage.setScene(new Scene(root, 790, 590));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


       ((Node) (actionEvent.getSource())).getScene().getWindow().hide(); //keyingi oynai o'chirish
    }

    public void onShowPassword(ActionEvent actionEvent) {
        if (checkBox.isSelected()){
            password.setVisible(false);
            passwordText.setText(password.getText());
            passwordText.setVisible(true);
        }else {
            password.setVisible(true);
            password.setText(passwordText.getText());
            passwordText.setVisible(false);
        }
    }

    private void setLblErrors(Color color, String text){
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    private String logIn(){
        String status = "Success";
        String txtlogin, txtpassword;
        txtlogin = userName.getText();
        txtpassword = password.getText();

        if (txtlogin.isEmpty() || txtpassword.isEmpty()){
            setLblErrors(Color.TOMATO, "Login yoki parol kiritmadi !!!");
            status = "Error";
        }else {
            String query = "SELECT * FROM customers WHERE login = ? and password = ?";

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, txtlogin);
                preparedStatement.setString(2, txtpassword);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblErrors(Color.TOMATO, "Login yoki parolni to'g'ri kiriting !!!");
                    status = "Error";

                }else {
                    setLblErrors(Color.GREEN, "Login, parol muvoffaqiyatli kiritildi !!!");
                }


            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                System.err.println(throwables.getMessage());
                status = "Exception";
            }
        }
        return status;
    }
}
