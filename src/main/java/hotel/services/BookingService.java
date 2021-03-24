package hotel.services;

import hotel.models.Booking;
import hotel.services.abstracts.AbstractService;

public class BookingService extends AbstractService<Booking> {
    private static BookingService instance = null;

    private BookingService(){
    }

    public static BookingService getInstance(){
        if(instance == null)
            instance = new BookingService();
        return instance;
    }
}
