package cinema.algorithms;

import cinema.models.Booking;
import java.util.ArrayList;
import java.util.List;


public class BookingSorter {


    public static List<Booking> bubbleSort(List<Booking> bookings) {
        List<Booking> sorted = new ArrayList<>(bookings);
        int n = sorted.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (sorted.get(j).getPrice() > sorted.get(j + 1).getPrice()) {
                    // Swap
                    Booking temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return sorted;
    }
}