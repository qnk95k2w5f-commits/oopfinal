package cinema;

import cinema.DAO.*;
import cinema.algorithms.BookingSorter;
import cinema.models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static void printMovies(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }
        for (Movie m : movies) System.out.println(m);
    }

    private static void printCinemas(List<Cinema> cinemas) {
        if (cinemas == null || cinemas.isEmpty()) {
            System.out.println("No cinemas found.");
            return;
        }
        for (Cinema c : cinemas) System.out.println(c);
    }

    private static void printViewers(List<Viewer> viewers) {
        if (viewers == null || viewers.isEmpty()) {
            System.out.println("No viewers found.");
            return;
        }
        for (Viewer v : viewers) System.out.println(v);
    }

    private static void printBookings(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Booking b : bookings) System.out.println(b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MovieDAO movieDAO = new MovieDAO();
        CinemaDAO cinemaDAO = new CinemaDAO();
        ViewerDAO viewerDAO = new ViewerDAO();
        BookingDAO bookingDAO = new BookingDAO();

        int choice;
        do {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║      CINEMA BOOKING SYSTEM - CRUD MENU        ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("MOVIES:");
            System.out.println("  1) Show all movies (READ)");
            System.out.println("  2) Add movie (CREATE)");
            System.out.println("  3) Update movie rating (UPDATE)");
            System.out.println("  4) Delete movie (DELETE)");
            System.out.println("  5) Filter movies by genre");
            System.out.println("\nCINEMAS:");
            System.out.println("  6) Show all cinemas (READ)");
            System.out.println("  7) Add cinema (CREATE)");
            System.out.println("  8) Update cinema capacity (UPDATE)");
            System.out.println("  9) Delete cinema (DELETE)");
            System.out.println(" 10) Filter cinemas by type");
            System.out.println("\nVIEWERS:");
            System.out.println(" 11) Show all viewers (READ)");
            System.out.println(" 12) Add viewer (CREATE)");
            System.out.println(" 13) Update premium status (UPDATE)");
            System.out.println(" 14) Delete viewer (DELETE)");
            System.out.println(" 15) Show premium viewers");
            System.out.println("\nBOOKINGS:");
            System.out.println(" 16) Show all bookings (READ)");
            System.out.println(" 17) Create booking (CREATE)");
            System.out.println(" 18) Update payment status (UPDATE)");
            System.out.println(" 19) Delete booking (DELETE)");
            System.out.println(" 20) Show bookings by viewer ID");
            System.out.println("\nSORTING ALGORITHMS:");
            System.out.println(" 21) Sort bookings by price (Bubble Sort)");
            System.out.println(" 22) Sort bookings by price (Quick Sort)");
            System.out.println(" 23) Sort bookings by price (Merge Sort)");
            System.out.println(" 24) Compare all sorting algorithms");
            System.out.println("\n  0) Exit");
            System.out.print("\nChoice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a number: ");
                sc.nextLine();
            }
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    // MOVIES
                    case 1:
                        printMovies(movieDAO.readAll());
                        break;

                    case 2: {
                        System.out.print("Movie ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Movie name: ");
                        String name = sc.nextLine();

                        System.out.print("Genre: ");
                        String genre = sc.nextLine();

                        System.out.print("Duration (minutes): ");
                        int duration = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Rating (0-10): ");
                        double rating = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Director: ");
                        String director = sc.nextLine();

                        movieDAO.create(new Movie(id, name, genre, duration, rating, director));
                        System.out.println("✓ Movie added.");
                        break;
                    }

                    case 3: {
                        System.out.print("Movie ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New rating: ");
                        double rating = sc.nextDouble();
                        sc.nextLine();

                        boolean ok = movieDAO.updateRating(id, rating);
                        System.out.println(ok ? "✓ Movie updated." : "✗ Movie not found.");
                        break;
                    }

                    case 4: {
                        System.out.print("Movie ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = movieDAO.deleteById(id);
                        System.out.println(ok ? "✓ Movie deleted." : "✗ Movie not found.");
                        break;
                    }

                    case 5: {
                        System.out.print("Genre: ");
                        String genre = sc.nextLine();

                        printMovies(movieDAO.filterByGenre(genre));
                        break;
                    }

                    // CINEMAS
                    case 6:
                        printCinemas(cinemaDAO.readAll());
                        break;

                    case 7: {
                        System.out.print("Cinema ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Cinema name: ");
                        String name = sc.nextLine();

                        System.out.print("Address: ");
                        String address = sc.nextLine();

                        System.out.print("Capacity: ");
                        int capacity = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Type (IMAX/3D/Standard): ");
                        String type = sc.nextLine();

                        System.out.print("Screen number: ");
                        int screen = sc.nextInt();
                        sc.nextLine();

                        cinemaDAO.create(new Cinema(id, name, address, capacity, type, screen));
                        System.out.println("✓ Cinema added.");
                        break;
                    }

                    case 8: {
                        System.out.print("Cinema ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New capacity: ");
                        int capacity = sc.nextInt();
                        sc.nextLine();

                        boolean ok = cinemaDAO.updateCapacity(id, capacity);
                        System.out.println(ok ? "✓ Cinema updated." : "✗ Cinema not found.");
                        break;
                    }

                    case 9: {
                        System.out.print("Cinema ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = cinemaDAO.deleteById(id);
                        System.out.println(ok ? "✓ Cinema deleted." : "✗ Cinema not found.");
                        break;
                    }

                    case 10: {
                        System.out.print("Type (IMAX/3D/Standard): ");
                        String type = sc.nextLine();

                        printCinemas(cinemaDAO.filterByType(type));
                        break;
                    }

                    // VIEWERS
                    case 11:
                        printViewers(viewerDAO.readAll());
                        break;

                    case 12: {
                        System.out.print("Viewer ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Phone: ");
                        String phone = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();

                        System.out.print("Premium (true/false): ");
                        boolean premium = sc.nextBoolean();
                        sc.nextLine();

                        viewerDAO.create(new Viewer(id, name, email, phone, age, premium));
                        System.out.println("✓ Viewer added.");
                        break;
                    }

                    case 13: {
                        System.out.print("Viewer ID: ");
                        int id = sc.nextInt();

                        System.out.print("Premium status (true/false): ");
                        boolean premium = sc.nextBoolean();
                        sc.nextLine();

                        boolean ok = viewerDAO.updatePremiumStatus(id, premium);
                        System.out.println(ok ? "✓ Viewer updated." : "✗ Viewer not found.");
                        break;
                    }

                    case 14: {
                        System.out.print("Viewer ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = viewerDAO.deleteById(id);
                        System.out.println(ok ? "✓ Viewer deleted." : "✗ Viewer not found.");
                        break;
                    }

                    case 15:
                        printViewers(viewerDAO.filterPremium(true));
                        break;

                    // BOOKINGS
                    case 16:
                        printBookings(bookingDAO.readAll());
                        break;

                    case 17: {
                        System.out.print("Booking ID: ");
                        int id = sc.nextInt();

                        System.out.print("Viewer ID: ");
                        int viewerId = sc.nextInt();

                        System.out.print("Movie ID: ");
                        int movieId = sc.nextInt();

                        System.out.print("Cinema ID: ");
                        int cinemaId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Booking date (YYYY-MM-DD): ");
                        String date = sc.nextLine();

                        System.out.print("Show time (HH:MM): ");
                        String time = sc.nextLine();

                        System.out.print("Seat number: ");
                        int seat = sc.nextInt();

                        System.out.print("Price: ");
                        double price = sc.nextDouble();

                        System.out.print("Paid (true/false): ");
                        boolean paid = sc.nextBoolean();
                        sc.nextLine();

                        bookingDAO.create(new Booking(id, viewerId, movieId, cinemaId, date, time, seat, price, paid));
                        System.out.println("✓ Booking created.");
                        break;
                    }

                    case 18: {
                        System.out.print("Booking ID: ");
                        int id = sc.nextInt();

                        System.out.print("Paid status (true/false): ");
                        boolean paid = sc.nextBoolean();
                        sc.nextLine();

                        boolean ok = bookingDAO.updatePaymentStatus(id, paid);
                        System.out.println(ok ? "✓ Booking updated." : "✗ Booking not found.");
                        break;
                    }

                    case 19: {
                        System.out.print("Booking ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = bookingDAO.deleteById(id);
                        System.out.println(ok ? "✓ Booking deleted." : "✗ Booking not found.");
                        break;
                    }

                    case 20: {
                        System.out.print("Viewer ID: ");
                        int viewerId = sc.nextInt();
                        sc.nextLine();

                        printBookings(bookingDAO.getByViewerId(viewerId));
                        break;
                    }

                    // SORTING
                    case 21: {
                        List<Booking> bookings = bookingDAO.readAll();
                        List<Booking> sorted = BookingSorter.bubbleSort(bookings);
                        System.out.println("\n🔵 BUBBLE SORT (by price):");
                        printBookings(sorted);
                        break;
                    }

                    case 0:
                        System.out.println("👋 Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("❌ DB error: " + e.getMessage());
            }
        } while (choice != 0);

        sc.close();
    }
}
