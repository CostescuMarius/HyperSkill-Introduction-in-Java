package hard.cinema;

import java.util.*;

public class Cinema {
    public final static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] cinemaRoom = new char[rows][seats];
        ManageCinema.createCinemaRoom(cinemaRoom);

        int option;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            option = scanner.nextInt();

            switch(option) {
                case 1:
                    ManageCinema.showCinemaRoom(cinemaRoom);
                    break;

                case 2:
                    ManageCinema.buyTicket(cinemaRoom);
                    break;

                case 3:
                    ManageCinema.getStatistics(cinemaRoom);
                    break;

                default:
                    break;
            }
        } while(option != 0);
    }
}

class ManageCinema {
    static int noSoldTickets = 0;

    static int currentIncome = 0;
    static final int TICKET_PRICE_FRONT = 10;
    static final int TICKET_PRICE_BACK = 8;
    public static void createCinemaRoom(char[][] cinemaRoom) {
        for (char[] row : cinemaRoom) {
            Arrays.fill(row, 'S');
        }
    }

    public static void showCinemaRoom(char[][] cinemaRoom) {
        System.out.println("Cinema:");
        for(int j = 0; j <= cinemaRoom[0].length; j++) {
            if(j == 0) {
                System.out.print("  ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println();
        for(int i = 0; i < cinemaRoom.length; i++) {
            System.out.print(i + 1 + " ");
            for(int j = 0; j < cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket(char[][] cinemaRoom) {
        int ticketPrice;

        System.out.println("Enter a row number:");
        int wantedRow = Cinema.scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int wantedSeat = Cinema.scanner.nextInt();

        while(wantedRow < 1 || wantedRow > cinemaRoom.length || wantedSeat < 1 || wantedSeat > cinemaRoom.length) {
            System.out.println("Wrong input!");

            System.out.println("Enter a row number:");
            wantedRow = Cinema.scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            wantedSeat = Cinema.scanner.nextInt();
        }

        while(cinemaRoom[wantedRow - 1][wantedSeat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");

            System.out.println("Enter a row number:");
            wantedRow = Cinema.scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            wantedSeat = Cinema.scanner.nextInt();
        }
        int totalNoOfSeats = cinemaRoom.length * cinemaRoom[0].length;
        if(totalNoOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            if(wantedRow <= cinemaRoom.length / 2) {
                ticketPrice = TICKET_PRICE_FRONT;
            } else {
                ticketPrice = TICKET_PRICE_BACK;
            }
        }
        System.out.println("Ticket price: $" + ticketPrice);
        currentIncome += ticketPrice;
        cinemaRoom[wantedRow - 1][wantedSeat - 1] = 'B';
        noSoldTickets++;
    }

    public static void getStatistics(char[][] cinemaRoom) {
        System.out.printf("Number of purchased tickets: %d\n", noSoldTickets);

        int rows = cinemaRoom.length;
        int seats = cinemaRoom[0].length;
        int totalNoOfTickets = rows * seats;
        float percentage = (float) noSoldTickets / totalNoOfTickets * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);

        System.out.printf("Current income: $%d\n", currentIncome);

        int totalIncome;
        if(totalNoOfTickets <= 60) {
            totalIncome = TICKET_PRICE_FRONT * totalNoOfTickets;
        } else {
            int totalNoOfSeatsFront = (rows / 2) * seats;
            int totalNoOfSeatsBack = (rows - (rows / 2)) * seats;
            totalIncome = TICKET_PRICE_FRONT * totalNoOfSeatsFront +
                    TICKET_PRICE_BACK * totalNoOfSeatsBack;
        }
        System.out.printf("Total income: $%d\n", totalIncome);
    }
}