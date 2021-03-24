package hotel.models;

import java.sql.Date;

public class Orders extends Model{
    private String roomNameType;
    private String roomNameSize;
    private Date givenDate;
    private Date dueDate;
    private int roomNumber;
    private int roomPriceDay;
    private int days;
    private int totalPrice;

    public String getRoomNameType() {
        return roomNameType;
    }

    public void setRoomNameType(String roomNameType) {
        this.roomNameType = roomNameType;
    }

    public String getRoomNameSize() {
        return roomNameSize;
    }

    public void setRoomNameSize(String roomNameSize) {
        this.roomNameSize = roomNameSize;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomPriceDay() {
        return roomPriceDay;
    }

    public void setRoomPriceDay(int roomPriceDay) {
        this.roomPriceDay = roomPriceDay;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
