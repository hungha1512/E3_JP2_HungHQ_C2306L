import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;
import Service.CustomerService;
import Service.RoomService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String flag = "n";

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("RS001", RoomType.Single, 8));
        rooms.add(new Room("RD001", RoomType.Doulbe, 12));
        rooms.add(new Room("RQ002", RoomType.Queen, 35));
        rooms.add(new Room("RT001", RoomType.Triple, 12.5));
        rooms.add(new Room("RQ001", RoomType.Quad, 20.5));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("1", "Mr. Linus Tovaldo", "84125325346457"));
        customers.add(new Customer("2", "Mr. Bill", "91124235346467"));
        customers.add(new Customer("3", "Mr. Turing", "911423534646"));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("1", rooms.get(0), customers.get(0), LocalDateTime.of(2023, 3, 15, 9, 30, 15), LocalDateTime.of(2023, 3, 16, 12, 30, 45)));
        bookings.add(new Booking("2", rooms.get(0), customers.get(1), LocalDateTime.of(2023, 6, 9, 19, 30, 25), LocalDateTime.of(2023, 6, 10, 11, 25, 15)));
        bookings.add(new Booking("3", rooms.get(1), customers.get(1), LocalDateTime.of(2023, 3, 11, 10, 10, 5), LocalDateTime.of(2023, 6, 10, 11, 5, 10)));
        bookings.add(new Booking("4", rooms.get(3), customers.get(2), LocalDateTime.of(2023, 11, 11, 11, 11, 15), LocalDateTime.of(2023, 11, 13, 11, 15, 15)));
        bookings.add(new Booking("5", rooms.get(3), customers.get(0), LocalDateTime.of(2023, 10, 25, 9, 20, 25), LocalDateTime.of(2023, 10, 26, 12, 25, 30)));
        bookings.add(new Booking("6", rooms.get(4), customers.get(0), LocalDateTime.of(2023, 8, 18, 18, 25, 35), LocalDateTime.of(2023, 8, 19, 11, 35, 20)));

        BookingService.bookingList = bookings;
        BookingService.roomList = rooms;
        RoomService.roomList = rooms;
        CustomerService.customerList = customers;
        try {
            do {
                String choice, information, bookingId, customerId, roomType;
                int day;
                System.out.println("--------MENU---------");
                System.out.println("1. Booking room");
                System.out.println("2. Search booking information");
                System.out.println("3. Statistic income by roomType");
                System.out.println("4. Display the highest income by RoomType in 2023");
                System.out.print("--------Your choice: ");
                choice = br.readLine();
                switch (choice) {
                    case "1":
                        System.out.print("Enter booking id: ");
                        bookingId = br.readLine();
                        System.out.print("Enter customer id: ");
                        customerId = br.readLine();
                        System.out.println("Select RoomType you want: ");
                        System.out.println("1. Single - 8$/hour");
                        System.out.println("2. Double - 12$/hour");
                        System.out.println("3. Queen - 35$/hour");
                        System.out.println("4. Triple - 12.5$/hour");
                        System.out.println("5. Quad - 20.5$/hour");
                        System.out.print("---Your choice:");
                        roomType = br.readLine();
                        System.out.println("How long do you stay?");
                        day = Integer.parseInt(br.readLine());
                        System.out.println(BookingService.setBooking(bookingId, roomType, customerId, day));
                        break;
                    case "2":
                        System.out.print("Enter your information to search (Your name or Your phone or Your room id): ");
                        information = br.readLine();
                        System.out.println("Your results: " + BookingService.getBookingList(information));
                        break;
                    case "3":
                        System.out.println(BookingService.getIncomeForEachRoomtype());
                        break;
                    case "4":
                        System.out.println("The RoomType has the best income in 2024.");
                        System.out.println(BookingService.getRoomTypeHasBestIncome());
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                }
                System.out.println("Do you want continue? Y to continue, N to close!");
                System.out.print("Enter your choice: ");
                flag = br.readLine();

            } while (flag.equalsIgnoreCase("y"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}