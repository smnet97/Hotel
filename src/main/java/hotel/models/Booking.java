package hotel.models;

import hotel.annotations.Column;
import hotel.annotations.Table;

import java.sql.Date;
@Table(name = "booking")
public class Booking extends Model{
    private int customerId;
    private int roomType;
    private int roomSize;
    private Date givenDate;
    private Date dueDate;
    private int roomNumber;
    private String cardNumber;
    private String cardDate;
    private int totalPrice;

    public Booking(int customerId, int roomType, int roomSize, Date givenDate, Date dueDate, int roomNumber, String cardNumber, String cardDate, int totlaPrice) {
        this.customerId = customerId;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.givenDate = givenDate;
        this.dueDate = dueDate;
        this.roomNumber = roomNumber;
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.totalPrice = totlaPrice;
    }
    public Booking(){

    }

    @Column(columnName = "customer_id", refTable = "customers", refColumn = "id")
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    @Column(columnName = "room_type_id", refTable = "room_type", refColumn = "id")
    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }
    @Column(columnName = "room_size_id", refTable = "room_size", refColumn = "id")
    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
    @Column(columnName = "give_date")
    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }
    @Column(columnName = "due_date")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    @Column(columnName = "room_number_id", refTable = "rooms", refColumn = "id")
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    @Column(columnName = "card_number")
    public String  getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Column(columnName = "card_date")
    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }
    @Column(columnName = "total_price")
    public int getTotalPrice(){
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice){
        this.totalPrice = totalPrice;
    }
}
