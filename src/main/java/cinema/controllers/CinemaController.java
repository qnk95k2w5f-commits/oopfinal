package cinema.controllers;

import cinema.DAO.CinemaDAO;
import cinema.DTO.CinemaDTO;
import cinema.models.Cinema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
@CrossOrigin(origins = "*")
public class CinemaController {

    private final CinemaDAO cinemaDAO = new CinemaDAO();

    /**
     * GET /api/cinemas - Получить все кинотеатры
     */
    @GetMapping
    public List<CinemaDTO> getAll() throws SQLException {
        List<Cinema> cinemas = cinemaDAO.readAll();
        return convertToDTO(cinemas);
    }

    /**
     * GET /api/cinemas/type/{type} - Фильтрация по типу
     */
    @GetMapping("/type/{type}")
    public List<CinemaDTO> filterByType(@PathVariable String type) throws SQLException {
        List<Cinema> cinemas = cinemaDAO.filterByType(type);
        return convertToDTO(cinemas);
    }

    /**
     * POST /api/cinemas - Создать новый кинотеатр
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CinemaDTO dto) throws SQLException {
        Cinema cinema = new Cinema(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getCapacity(),
                dto.getType(),
                dto.getScreenNumber()
        );

        cinemaDAO.create(cinema);
        return ResponseEntity.ok(dto);
    }

    /**
     * PUT /api/cinemas/{id}/capacity - Обновить вместимость
     */
    @PutMapping("/{id}/capacity")
    public ResponseEntity<?> updateCapacity(
            @PathVariable int id,
            @RequestParam int capacity) throws SQLException {

        boolean ok = cinemaDAO.updateCapacity(id, capacity);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Capacity updated");
    }

    /**
     * DELETE /api/cinemas/{id} - Удалить кинотеатр
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean ok = cinemaDAO.deleteById(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // Helper method
    private List<CinemaDTO> convertToDTO(List<Cinema> cinemas) {
        List<CinemaDTO> result = new ArrayList<>();
        for (Cinema c : cinemas) {
            result.add(new CinemaDTO(
                    c.getId(),
                    c.getName(),
                    c.getAddress(),
                    c.getCapacity(),
                    c.getType(),
                    c.getScreenNumber()
            ));
        }
        return result;
    }
}
