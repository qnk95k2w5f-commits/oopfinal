package cinema.controllers;

import cinema.DAO.ViewerDAO;
import cinema.DTO.ViewerDTO;
import cinema.models.Viewer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/viewers")
@CrossOrigin(origins = "*")
public class ViewerController {

    private final ViewerDAO viewerDAO = new ViewerDAO();

    /**
     * GET /api/viewers - Получить всех зрителей
     */
    @GetMapping
    public List<ViewerDTO> getAll() throws SQLException {
        List<Viewer> viewers = viewerDAO.readAll();
        return convertToDTO(viewers);
    }

    /**
     * GET /api/viewers/premium - Получить только Premium зрителей
     */
    @GetMapping("/premium")
    public List<ViewerDTO> getPremium() throws SQLException {
        List<Viewer> viewers = viewerDAO.filterPremium(true);
        return convertToDTO(viewers);
    }

    /**
     * POST /api/viewers - Создать нового зрителя
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ViewerDTO dto) throws SQLException {
        Viewer viewer = new Viewer(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAge(),
                dto.isPremium()
        );

        viewerDAO.create(viewer);
        return ResponseEntity.ok(dto);
    }

    /**
     * PUT /api/viewers/{id}/premium - Обновить Premium статус
     */
    @PutMapping("/{id}/premium")
    public ResponseEntity<?> updatePremiumStatus(
            @PathVariable int id,
            @RequestParam boolean isPremium) throws SQLException {

        boolean ok = viewerDAO.updatePremiumStatus(id, isPremium);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Premium status updated");
    }

    /**
     * DELETE /api/viewers/{id} - Удалить зрителя
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean ok = viewerDAO.deleteById(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // Helper method
    private List<ViewerDTO> convertToDTO(List<Viewer> viewers) {
        List<ViewerDTO> result = new ArrayList<>();
        for (Viewer v : viewers) {
            result.add(new ViewerDTO(
                    v.getId(),
                    v.getName(),
                    v.getEmail(),
                    v.getPhone(),
                    v.getAge(),
                    v.isPremium()
            ));
        }
        return result;
    }
}
