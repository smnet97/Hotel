package hotel.models;

import hotel.annotations.Table;

@Table(name = "resteurant")
public class Restaurant extends Model{
    private int tableNumber;
    private String tableSize;

}
