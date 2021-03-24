package hotel.models;

import hotel.annotations.Table;

import java.sql.Time;
import java.time.LocalDate;
@Table(name = "booking_table")
public class BookingTable{
    private int customerId;
    private LocalDate date;
    private String time;
    private String personCount;
    private int tableNumber;
    private int cardNumber;
    private String cardDate;



}
