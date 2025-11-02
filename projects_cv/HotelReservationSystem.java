import java.util.*;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isBooked;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    void displayRoom() {
        System.out.println("Room No: " + roomNumber + " | Type: " + type + " | Price: ₹" + price + " | Booked: " + (isBooked ? "Yes" : "No"));
    }
}

class Customer {
    String name;
    String contact;
    int bookedRoomNo;

    Customer(String name, String contact, int bookedRoomNo) {
        this.name = name;
        this.contact = contact;
        this.bookedRoomNo = bookedRoomNo;
    }

    void displayCustomer() {
        System.out.println("Customer Name: " + name + " | Contact: " + contact + " | Room No: " + bookedRoomNo);
    }
}

public class HotelReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize sample rooms
        rooms.add(new Room(101, "Single", 1000));
        rooms.add(new Room(102, "Double", 1800));
        rooms.add(new Room(103, "Deluxe", 2500));
        rooms.add(new Room(104, "Suite", 4000));

        while (true) {
            System.out.println("\n🏨 --- Hotel Reservation System ---");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Booked Rooms");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    viewBookedRooms();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    System.out.println("Thank you for visiting! 👋");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    static void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (!room.isBooked) {
                room.displayRoom();
            }
        }
    }

    static void bookRoom() {
        System.out.print("\nEnter room number to book: ");
        int roomNo = sc.nextInt();
        sc.nextLine(); // consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNo) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("❌ Room not found!");
            return;
        }

        if (selectedRoom.isBooked) {
            System.out.println("❌ Room already booked!");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter contact number: ");
        String contact = sc.nextLine();

        selectedRoom.isBooked = true;
        customers.add(new Customer(name, contact, roomNo));
        System.out.println("✅ Room " + roomNo + " booked successfully for " + name + "!");
    }

    static void viewBookedRooms() {
        System.out.println("\n--- Booked Rooms ---");
        for (Customer c : customers) {
            c.displayCustomer();
        }
        if (customers.isEmpty()) {
            System.out.println("No bookings yet!");
        }
    }

    static void cancelBooking() {
        System.out.print("\nEnter room number to cancel booking: ");
        int roomNo = sc.nextInt();
        sc.nextLine(); // consume newline

        boolean found = false;

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && room.isBooked) {
                room.isBooked = false;
                found = true;
                break;
            }
        }

        if (found) {
            customers.removeIf(c -> c.bookedRoomNo == roomNo);
            System.out.println("✅ Booking for room " + roomNo + " cancelled successfully!");
        } else {
            System.out.println(" Room not booked or not found!");
        }
    }
}
