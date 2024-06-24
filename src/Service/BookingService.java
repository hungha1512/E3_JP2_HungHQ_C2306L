package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    public static List<Booking> bookingList;
    public static List<Room> roomList;

    public BookingService() {
        ;
    }
//  Ques 3.1
    public static Booking setBooking(String id, String roomTypeInput, String cusId, int day) {
        RoomType roomType = null;
        Booking booking = new Booking();
        Customer customer = CustomerService.getCustomer(cusId);
        switch (roomTypeInput) {
            case "Single":
            case "1":
                roomType = RoomType.Single;
                break;
            case "Double":
            case "2":
                roomType = RoomType.Doulbe;
                break;
            case "Queen":
            case "3":
                roomType = RoomType.Queen;
                break;
            case "Triple":
            case "4":
                roomType = RoomType.Triple;
                break;
            case "Quad":
            case "5":
                roomType = RoomType.Quad;
                break;
            default:
                System.out.println("Invalid room type");
        }
        Room room = RoomService.getRoom(roomType);
        booking.setId(id);
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setCheckInDatetime(LocalDateTime.now());
        booking.setCheckOutDatetime(LocalDateTime.now().plusDays(day));
        bookingList.add(booking);

        return booking;
    }
//  Ques 3.2
    public static List<Booking> getBookingList(String keyword) {
        String[] keywords = keyword.split(" ");
        return bookingList.stream()
                .filter(booking -> {
                    for (String s : keywords) {
                        if (booking.getCustomer().getCusName().contains(s) || booking.getRoom().getId().contains(s) || booking.getCustomer().getCusPhone().contains(s)) {
                            return true;
                        }
                    }
                    return false;
                }).toList();
    }
//  Ques 3.3
    public static Map<RoomType, Long> getHourForEachRoomtype() {
        return bookingList.stream()
                .filter(booking -> booking.getCheckOutDatetime().getYear() == 2023)
                .collect(Collectors.groupingBy(Booking::getRoomType, Collectors.summingLong(Booking::getBookingDurationHours)));

    }

    public static Map<RoomType, Double> getIncomeForEachRoomtype() {
        Map<RoomType, Long> hourOfEachRoomtype = getHourForEachRoomtype();
        return hourOfEachRoomtype
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    double price = roomList.stream()
                            .filter(room -> room.getRoomType() == entry.getKey())
                            .findFirst()
                            .get()
                            .getPricePerHour();
                    return entry.getValue() * price;
                }));
    }
//  Ques 3.4
    public static Optional<Map.Entry<RoomType, Double>> getRoomTypeHasBestIncome() {
        Map<RoomType, Double> incomeOfEachRoomtype = getIncomeForEachRoomtype();
        return incomeOfEachRoomtype
                .entrySet()
                .stream()
                .collect(Collectors.maxBy(Map.Entry.comparingByValue()));
    }


}
