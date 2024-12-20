import fact.it.winkelservice.dto.*;
import fact.it.winkelservice.model.Winkel;
import fact.it.winkelservice.repository.WinkelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<WinkelResponse> getWinkels() {
        return winkelRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<WinkelResponse> getWinkelById(Long id) {
        return winkelRepository.findById(id)
                .map(this::mapToResponse);
    }

    public WinkelResponse addWinkel(WinkelRequest winkelRequest) {
        Winkel winkel = mapToEntity(winkelRequest);
        Winkel savedWinkel = winkelRepository.save(winkel);
        return mapToResponse(savedWinkel);
    }

    public WinkelResponse updateWinkelAlbums(Long id, List<Long> albumIds) {
        Winkel updatedWinkel = winkelRepository.findById(id)
                .map(winkel -> {
                    winkel.setAlbumIds(albumIds);
                    return winkelRepository.save(winkel);
                })
                .orElseThrow(() -> new RuntimeException("Winkel not found"));
        return mapToResponse(updatedWinkel);
    }

    // Helper to map Winkel to WinkelResponse
    private WinkelResponse mapToResponse(Winkel winkel) {
        List<AlbumResponse> albumResponses = winkel.getAlbumIds().stream()
                .map(AlbumResponse::new) // Convert album IDs to AlbumResponse
                .collect(Collectors.toList());
        return new WinkelResponse(
                winkel.getId(),
                winkel.getName(),
                albumResponses
        );
    }

    // Helper to map WinkelRequest to Winkel
    private Winkel mapToEntity(WinkelRequest winkelRequest) {
        return new Winkel(
                null, // ID will be auto-generated
                winkelRequest.getName(),
                winkelRequest.getAlbumIds() != null ? winkelRequest.getAlbumIds() : List.of()
        );
    }
}
