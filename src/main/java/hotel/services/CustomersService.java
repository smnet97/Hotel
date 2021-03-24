package hotel.services;

import hotel.models.Customers;
import hotel.services.abstracts.AbstractService;

public class CustomersService extends AbstractService<Customers> {
    private static CustomersService instance = null;

    private CustomersService(){
    }

    public static CustomersService getInstance(){
        if(instance == null)
            instance = new CustomersService();
        return instance;
    }
}
