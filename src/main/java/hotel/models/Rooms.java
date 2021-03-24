package hotel.models;

import hotel.annotations.Column;
import hotel.annotations.SearchField;
import hotel.annotations.Table;
import hotel.enums.SearchType;

import java.math.BigDecimal;

@Table(name = "rooms")
public class Rooms extends Model{
   private int roomNumber;
   private int roomTypeId;
   private int roomSizeId;
   private int priceDay;
   private int  roomFloor;

   @Column(columnName = "room_number")
   public int getRoomNumber() {
      return roomNumber;
   }

   public void setRoomNumber(int roomNumber) {
      this.roomNumber = roomNumber;
   }

   @Column(columnName = "room_type_id", refTable = "room_type", refColumn = "id")
   @SearchField(type = SearchType.EQUALS)
   public Integer getRoomTypeID() {
      return roomTypeId;
   }

   public void setRoomTypeId(Integer roomTypeId) {
      this.roomTypeId = roomTypeId;
   }

   @Column(columnName = "room_size_id", refTable = "room_size", refColumn = "id")
   public int getRoomSizeId() {
      return roomSizeId;
   }

   public void setRoomSizeId(int roomSizeId) {
      this.roomSizeId = roomSizeId;
   }

   @Column(columnName = "price_day")
   public int getPriceDay() {
      return priceDay;
   }

   public void setPriceDay(int priceDay) {
      this.priceDay = priceDay;
   }

   @Column(columnName = "room_floor")
   public int getRoomFloor() {
      return roomFloor;
   }

   public void setRoomFloor(int  roomFloor) {
      this.roomFloor = roomFloor;
   }
}
