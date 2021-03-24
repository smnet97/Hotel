package hotel.controllers.costumersListController;

import hotel.models.Customers;
import hotel.services.CustomersService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Random;

public class CostumersListController {
    public Button delete;
    public TableView tableCustomers;

    private CustomersService service = CustomersService.getInstance();

    public void initialize(){
        TableColumn<Integer, Customers> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<String, Customers> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Customers> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<String, Customers> middleNameColumn = new TableColumn<>("MiddleName");
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<String, Customers> passportNumberColumn = new TableColumn<>("Passport number");
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        TableColumn<String, Customers> phoneColumn = new TableColumn<>("Phone number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<String, Customers> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

        TableColumn<String, Customers> loginColumn = new TableColumn<>("Login");
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        TableColumn<String, Customers> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableCustomers.getColumns().addAll(idColumn, nameColumn, surnameColumn, middleNameColumn, passportNumberColumn,
                phoneColumn, emailColumn, loginColumn, passwordColumn);


        loadTable();
    }

    private void loadTable() {
        tableCustomers.getItems().clear();
        List<Customers> list = service.getAll();

        tableCustomers.getItems().addAll(list);
    }


//    public void onAdd(ActionEvent actionEvent) {
//        Random random = new Random();
//
//        int n = random.nextInt(100);
//
//        Product product = new Product();
//        product.setName("Product"+n);
//        product.setPrice(n * 100);
//
//        service.save(product);
//        loadTable();
//    }

    public void onDelete(ActionEvent actionEvent) {
        SelectionModel<Customers> selectionModel = tableCustomers.getSelectionModel();
        if(selectionModel != null){
            Customers customers = selectionModel.getSelectedItem();
            if(customers != null){
                service.delete(customers);
                loadTable();
            }
        }

    }

//    public void onSearch(ActionEvent actionEvent) {
//        if (keyword.getText().isEmpty()){
//            loadTable();
//            return;
//        }
//
//        List<Product> list = service.filter(keyword.getText());
//        tableProduct.getItems().clear();
//        if(list != null) {
//            tableProduct.getItems().addAll(list);
//        }
//    }

}
