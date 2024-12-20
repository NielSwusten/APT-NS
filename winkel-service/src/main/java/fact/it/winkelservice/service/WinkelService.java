package fact.it.winkelservice.service;

import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.WinkelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinkelService {

    private final WinkelRepository winkelRepository;

    @PostConstruct
    public void loadData() {
        if (winkelRepository.count() <= 0) {
            List<Winkel> winkelList = List.of(
                    new Winkel(null, "Mediamarkt", List.of(1L, 2L)), // Example albums
                    new Winkel(null, "Ebay", List.of(3L))
            );
            winkelRepository.saveAll(winkelList);
        }
    }

    public List<Winkel> getWinkels() {
        return winkelRepository.findAll();
    }

    public Optional<Winkel> getWinkelById(Long id) {
        return winkelRepository.findById(id);
    }

    public Winkel addWinkel(Winkel winkel) {
        // Ensure `albumIds` is not null for new entities
        if (winkel.getAlbumIds() == null) {
            winkel.setAlbumIds(List.of());
        }
        return winkelRepository.save(winkel);
    }

    public Winkel updateWinkelAlbums(Long id, List<Long> albumIds) {
        return winkelRepository.findById(id).map(winkel -> {
            winkel.setAlbumIds(albumIds);
            return winkelRepository.save(winkel);
        }).orElseThrow(() -> new RuntimeException("Winkel not found"));
    }
}
