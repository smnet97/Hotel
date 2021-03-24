package hotel.services;

import hotel.models.Rooms;
import hotel.services.abstracts.AbstractService;

public class RoomsService extends AbstractService<Rooms> {
    private static RoomsService instance = null;

    private RoomsService(){
        }

    public static RoomsService getInstance(){
        if(instance == null)
            instance = new RoomsService();
            return instance;
        }
}