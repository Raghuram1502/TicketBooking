import java.util.*;

class User{
    String name;
    int seat;
    User(String name,int seat){
        this.name = name;
        this.seat = seat;
    }
}
class Theater{
    HashMap<Integer,User> seats = new HashMap<>();
    Theater(){
        for (int i=1;i < 50;i++){
            seats.put(i,null);
        }
    }

    synchronized int bookSeats() {
        for (int j = 1; j < seats.size(); j++) {
            if (seats.get(j) == null) {
                return j;
            }
        }
        return -1;
    }
    synchronized int selectYourSeat(String name,int seatNo){
        if (bookSeats() > 0) {
            if (seats.get(seatNo) == null) {
                seats.put(seatNo,new User(name,seatNo));
                return seatNo;
            }
            else {
                System.out.println("Seat is already booked! Book another seat");
                return -1;
            }
        }
        else{
            System.out.println("tickets are sold out");
        }
        return -1;
    }

    void availableSeats(){
        ArrayList<Integer> availableSeat = new ArrayList<>();
        for (int count = 1;count < seats.size();count++){
            if (seats.get(count) == null){
                availableSeat.add(count);
            }
        }
        System.out.println(availableSeat);
    }

    void getSeat(int id) {
        System.out.println(seats.get(id));
    }

    void cancelTicket(int id){
        if (seats.get(id) != null) {
            seats.remove(id);
        }
        System.out.println("Ticket not Found.!!");
    }
}

public class Main {
    public static void main(String[] args) {
        Theater t = new Theater();
        Scanner scan = new Scanner(System.in);
        int seat = t.bookSeats();
        System.out.println("What do you want?,Ticket-> Book (Or) Get (Or) Cancel:");
        String select = scan.nextLine().toLowerCase();
        switch (select){
            case "cancel" -> {
                System.out.println("Enter your Seat No:");
                int id = scan.nextInt();
                scan.nextLine();
                t.cancelTicket(id);
            }
            case "get" -> {
                System.out.println("Enter Seat No:");
                int id = scan.nextInt();
                scan.nextLine();
                t.getSeat(id);
            }
            case "book" ->{
                if (seat > 0) {
                    while (true) {
                        System.out.println("Enter Your name:");
                        String name = scan.nextLine();
                        System.out.println("Available Seats are:");
                        t.availableSeats();
                        System.out.println("Enter the number of tickets:");
                        int n = scan.nextInt();
                        for (int i = 0; i<n;i++) {
                            System.out.println("Enter the seat No:");
                            int seatNo = scan.nextInt();
                            scan.nextLine();
                            int ss = t.selectYourSeat(name, seatNo);
                            if (ss > 0) {
                                System.out.printf("Your seat is Booked %s : %d ", name, seatNo);
                                System.out.println();
                                System.out.println("Available Seats are:");
                                t.availableSeats();
                            }
                        }
                        System.out.println();
                        System.out.println("You want book another ticket!, Yes or No:");
                        String option = scan.nextLine().toLowerCase();
                        if (option.equals("no")) {
                            break;
                        }

                    }
                }

            }
        }

    }
}