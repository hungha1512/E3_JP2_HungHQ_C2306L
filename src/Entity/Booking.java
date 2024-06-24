package Entity;

import java.time.LocalDateTime;

public class Booking {
    private String id;
    private Room room;
    private Customer customer;
    private LocalDateTime checkInDatetime;
    private LocalDateTime checkOutDatetime;

    public Booking() {
        ;
    }

    public Booking(String id, Room room, Customer customer, LocalDateTime checkInDatetime, LocalDateTime checkOutDatetime) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.checkInDatetime = checkInDatetime;
        this.checkOutDatetime = checkOutDatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomType getRoomType() {
        return room.getRoomType();
    }

    public double getRoomPricePerHour(){
        return room.getPricePerHour();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCheckInDatetime() {
        return checkInDatetime;
    }

    public void setCheckInDatetime(LocalDateTime checkInDatetime) {
        this.checkInDatetime = checkInDatetime;
    }

    public LocalDateTime getCheckOutDatetime() {
        return checkOutDatetime;
    }

    public void setCheckOutDatetime(LocalDateTime checkOutDatetime) {
        this.checkOutDatetime = checkOutDatetime;
    }

    public long getBookingDurationHours() {
        LocalDateTime checkInTime = this.checkInDatetime;
        LocalDateTime checkOutTime = this.checkOutDatetime;

        long totalHours = (checkOutTime.getYear() - checkInTime.getYear()) * 8760L
                + (checkOutTime.getDayOfYear() - checkInTime.getDayOfYear()) * 24
                + (checkOutTime.getHour() - checkInTime.getHour())
                + (checkOutTime.getMinute() - checkInTime.getMinute()) / 60
                + (checkOutTime.getSecond() - checkInTime.getSecond()) / 3600;

        return totalHours;
    }

    @Override
    public String toString() {
        return "\n Booking{" +
                "id='" + id + '\'' +
                ", room=" + room +
                ", customer=" + customer +
                ", checkInDatetime=" + checkInDatetime +
                ", checkOutDatetime=" + checkOutDatetime +
                '}';
    }
}
