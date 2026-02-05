package cinema.controllers;

import cinema.DAO.MovieDAO;
import cinema.DTO.MovieDTO;
import cinema.models.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieDAO movieDAO = new MovieDAO();

    /**
     * GET /api/movies - Получить все фильмы
     */
    @GetMapping
    public List<MovieDTO> getAll() throws SQLException {
        List<Movie> movies = movieDAO.readAll();
        return convertToDTO(movies);
    }

    /**
     * GET /api/movies/sorted - Получить фильмы, отсортированные по рейтингу
     */
    @GetMapping("/sorted")
    public List<MovieDTO> getAllSorted() throws SQLException {
        List<Movie> movies = movieDAO.readAllSortedByRating();
        return convertToDTO(movies);
    }

    /**
     * GET /api/movies/genre/{genre} - Фильтрация по жанру
     */
    @GetMapping("/genre/{genre}")
    public List<MovieDTO> filterByGenre(@PathVariable String genre) throws SQLException {
        List<Movie> movies = movieDAO.filterByGenre(genre);
        return convertToDTO(movies);
    }

    /**
     * POST /api/movies - Создать новый фильм
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MovieDTO dto) throws SQLException {
        Movie movie = new Movie(
                dto.getId(),
                dto.getName(),
                dto.getGenre(),
                dto.getDuration(),
                dto.getRating(),
                dto.getDirector()
        );

        movieDAO.create(movie);
        return ResponseEntity.ok(dto);
    }

    /**
     * PUT /api/movies/{id}/rating - Обновить рейтинг фильма
     */
    @PutMapping("/{id}/rating")
    public ResponseEntity<?> updateRating(
            @PathVariable int id,
            @RequestParam double rating) throws SQLException {

        boolean ok = movieDAO.updateRating(id, rating);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Rating updated");
    }

    /**
     * DELETE /api/movies/{id} - Удалить фильм
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean ok = movieDAO.deleteById(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // Helper method
    private List<MovieDTO> convertToDTO(List<Movie> movies) {
        List<MovieDTO> result = new ArrayList<>();
        for (Movie m : movies) {
            result.add(new MovieDTO(
                    m.getId(),
                    m.getName(),
                    m.getGenre(),
                    m.getDuration(),
                    m.getRating(),
                    m.getDirector()
            ));
        }
        return result;
    }
}
