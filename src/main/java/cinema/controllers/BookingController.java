package cinema.controllers;
import java.util.stream.Collectors;

import cinema.DAO.BookingDAO;
import cinema.DTO.BookingDTO;
import cinema.algorithms.BookingSorter;
import cinema.models.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

// ...

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        // dto.setViewerId(booking.getViewerId());
        // ...
        return dto;
    }

    private List<BookingDTO> convertToDTO(List<Booking> bookings) {
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private final BookingDAO bookingDAO = new BookingDAO();

    /**
     * GET /api/bookings - Получить все бронирования
     */
    @GetMapping
    public List<BookingDTO> getAll() throws SQLException {
        List<Booking> bookings = bookingDAO.readAll();
        return convertToDTO(bookings);
    }

    /**
     * GET /api/bookings/viewer/{viewerId} - Бронирования конкретного зрителя
     */
    @GetMapping("/viewer/{viewerId}")
    public List<BookingDTO> getByViewer(@PathVariable int viewerId) throws SQLException {
        List<Booking> bookings = bookingDAO.getByViewerId(viewerId);
        return convertToDTO(bookings);
    }

    /**
     * GET /api/bookings/filter - Фильтрация по максимальной цене
     */
    @GetMapping("/filter")
    public List<BookingDTO> filterByPrice(@RequestParam double maxPrice) throws SQLException {
        List<Booking> bookings = bookingDAO.filterByPrice(maxPrice);
        return convertToDTO(bookings);
    }

    /**
     * POST /api/bookings - Создать новое бронирование
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookingDTO dto) throws SQLException {
        Booking booking = new Booking(
                dto.getId(),
                dto.getViewerId(),
                dto.getMovieId(),
                dto.getCinemaId(),
                dto.getBookingDate(),
                dto.getShowTime(),
                dto.getSeatNumber(),
                dto.getPrice(),
                dto.isPaid()
        );

        bookingDAO.create(booking);
        return ResponseEntity.ok(dto);
    }

    /**
     * PUT /api/bookings/{id}/payment - Обновить статус оплаты
     */
    @PutMapping("/{id}/payment")
    public ResponseEntity<?> updatePayment(
            @PathVariable int id,
            @RequestParam boolean isPaid) throws SQLException {

        boolean ok = bookingDAO.updatePaymentStatus(id, isPaid);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Payment status updated");
    }

    /**
     * DELETE /api/bookings/{id} - Удалить бронирование
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean ok = bookingDAO.deleteById(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // ==================== SORTING ALGORITHMS ====================

    /**
     * GET /api/bookings/sort/bubble - Bubble Sort по цене
     */
    @GetMapping("/sort/bubble")
    public List<BookingDTO> sortByBubble() throws SQLException {
        List<Booking> bookings = bookingDAO.readAll();
        List<Booking> sorted = BookingSorter.bubbleSort(bookings);
        return convertToDTO(sorted);
    }

    /**
     * GET /api/bookings/sort/quick - Quick Sort по цене
     */

}
